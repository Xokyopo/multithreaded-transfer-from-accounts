package ru.playtox.testcase.multithreadedtransferfromaccounts.controller;

import ru.playtox.testcase.multithreadedtransferfromaccounts.dao.Account;
import ru.playtox.testcase.multithreadedtransferfromaccounts.service.AccountService;
import ru.playtox.testcase.multithreadedtransferfromaccounts.service.NotMoneyForTransferException;
import ru.playtox.testcase.multithreadedtransferfromaccounts.service.TransferResult;

public class AccountController {

    private AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    public String transferFromTo(Account from, Account to, long sum) {
        try {
            TransferResult result = this.service.transferFromTo(from, to, sum);
            return String.format(
                    "the transfer from account=%s to account=%s in the amount of=%s$ was completed successfully.",
                    result.getFrom(),
                    result.getTo(),
                    sum
            );
        } catch (NotMoneyForTransferException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Internal error. Transfer was unsuccessfully.";
        }
    }

    public Account addAccount(long money) {
        return this.service.createNewAccount(money);
    }

    public Account getRandomAccount() {
        return this.service.getRandomAccount();
    }
}
