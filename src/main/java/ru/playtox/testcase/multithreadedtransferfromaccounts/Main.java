package ru.playtox.testcase.multithreadedtransferfromaccounts;


import ru.playtox.testcase.multithreadedtransferfromaccounts.controller.AccountController;
import ru.playtox.testcase.multithreadedtransferfromaccounts.dao.Account;
import ru.playtox.testcase.multithreadedtransferfromaccounts.dao.AccountRepository;
import ru.playtox.testcase.multithreadedtransferfromaccounts.scheduler.RandomTransfer;
import ru.playtox.testcase.multithreadedtransferfromaccounts.scheduler.RandomTransferManager;
import ru.playtox.testcase.multithreadedtransferfromaccounts.service.AccountService;
import ru.playtox.testcase.multithreadedtransferfromaccounts.service.LoggingProxyAccountService;

public class Main {
    public static void main(String[] args) {
        long startBalance = 10000;
        long accountCount = 10;

        int threadsCount = 4;
        long minSleepTime = 1000;
        long maxSleepTime = 2000;

        int repeats = 30;

        AccountRepository repository = new AccountRepository();
        AccountService service = new LoggingProxyAccountService(repository);
        AccountController controller = new AccountController(service);

        for (int i = 0; i < accountCount; i++) {
            controller.addAccount(startBalance);
        }

        long sumMoneyBefore = service.getAll().stream()
                .mapToLong(Account::getMoney)
                .sum();

        RandomTransfer randomTransfer = new RandomTransfer(controller);

        RandomTransferManager manager = new RandomTransferManager(randomTransfer, startBalance);

        manager.run(repeats, threadsCount, minSleepTime, maxSleepTime);

        long sumMoneyAfter = service.getAll().stream()
                .mapToLong(Account::getMoney)
                .sum();

        System.out.printf("sumBalanceBefore=%s ; sumBalanceAfter=%s; diff=%s\n", sumMoneyBefore, sumMoneyAfter, sumMoneyBefore - sumMoneyAfter);
    }
}
