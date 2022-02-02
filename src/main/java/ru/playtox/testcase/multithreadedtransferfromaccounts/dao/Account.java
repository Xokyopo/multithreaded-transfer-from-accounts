package ru.playtox.testcase.multithreadedtransferfromaccounts.dao;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Account extends ReentrantReadWriteLock {

    private final String id;

    private long money;

    public Account(String id, long money) {
        this.id = id;
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public long getMoney() {
        return money;
    }

    public long makeADeposit(long money) {
        throwExceptionIfCannotModified();
        return this.money += money;
    }

    public long makeAWithdrawal(long money) {
        throwExceptionIfCannotModified();
        return this.money -= money;
    }

    private void throwExceptionIfCannotModified() {
        if (!this.isWriteLockedByCurrentThread())
            throw new WriteWithoutLockException("Cannot modified data without lock");
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", money=" + money +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return getId().equals(account.getId());
    }
}
