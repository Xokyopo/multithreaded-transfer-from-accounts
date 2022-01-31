package ru.playtox.testcase.multithreadedtransferfromaccounts.service;

import ru.playtox.testcase.multithreadedtransferfromaccounts.dao.Account;
import ru.playtox.testcase.multithreadedtransferfromaccounts.dao.AccountRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class AccountService {

    private final Random RANDOM = new Random();

    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public TransferResult transferFromTo(Account from, Account to, long sum) {
        LocalDateTime start = LocalDateTime.now();

        WriteLock writeLock = from.writeLock();
        writeLock.lock();

        try {
            if (from.getMoney() < sum) {
                writeLock.unlock();

                throw new NotMoneyForTransferException(
                        String.format(
                                "Account with id = %s there are not enough funds to write off the amount = %s",
                                from.getId(),
                                sum
                        )
                );
            } else {
                long fromCurrentBalance = from.makeAWithdrawal(sum);
                writeLock.unlock();

                writeLock = to.writeLock();
                writeLock.lock();

                long toCurrentBalance = to.makeADeposit(sum);

                writeLock.unlock();
                LocalDateTime finished = LocalDateTime.now();

                return new TransferResult(from.getId(), fromCurrentBalance, to.getId(), toCurrentBalance, start, finished);
            }
        } finally {
            if (writeLock.isHeldByCurrentThread()) {
                writeLock.unlock();
            }
        }
    }

    public List<Account> getAll() {
        return this.repository.getAll();
    }

    public Account getRandomAccount() {
        List<Account> accounts = this.getAll();
        return accounts.get(RANDOM.nextInt(accounts.size()));
    }

    public Account createNewAccount(long money) {
        Account account = new Account(UUID.randomUUID().toString(), money);
        this.repository.put(account);
        return account;
    }
}
