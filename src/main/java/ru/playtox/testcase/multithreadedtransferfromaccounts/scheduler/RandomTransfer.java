package ru.playtox.testcase.multithreadedtransferfromaccounts.scheduler;

import ru.playtox.testcase.multithreadedtransferfromaccounts.controller.AccountController;
import ru.playtox.testcase.multithreadedtransferfromaccounts.dao.Account;

import java.util.Objects;

public class RandomTransfer {

    private final AccountController controller;

    public RandomTransfer(AccountController controller) {
        this.controller = controller;
    }

    public void run(long transferLimit) {
        Account from = this.controller.getRandomAccount();
        Account to;
        do {
            to = this.controller.getRandomAccount();
        } while (Objects.equals(from, to));
        System.out.println(this.controller.transferFromTo(from, to, (long) (Math.random() * transferLimit)));
    }
}
