package ru.playtox.testcase.multithreadedtransferfromaccounts.service;

public class NotMoneyForTransferException extends RuntimeException {

    public NotMoneyForTransferException() {
    }

    public NotMoneyForTransferException(String message) {
        super(message);
    }

    public NotMoneyForTransferException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotMoneyForTransferException(Throwable cause) {
        super(cause);
    }

    public NotMoneyForTransferException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
