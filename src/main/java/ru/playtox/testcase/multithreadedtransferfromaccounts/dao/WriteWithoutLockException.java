package ru.playtox.testcase.multithreadedtransferfromaccounts.dao;

public class WriteWithoutLockException extends RuntimeException {

    public WriteWithoutLockException() {
    }

    public WriteWithoutLockException(String message) {
        super(message);
    }

    public WriteWithoutLockException(String message, Throwable cause) {
        super(message, cause);
    }

    public WriteWithoutLockException(Throwable cause) {
        super(cause);
    }

    public WriteWithoutLockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
