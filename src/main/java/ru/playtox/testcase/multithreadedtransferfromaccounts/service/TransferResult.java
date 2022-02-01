package ru.playtox.testcase.multithreadedtransferfromaccounts.service;

import java.time.LocalDateTime;

public class TransferResult {

    private final String from;

    private final long fromCurrentBalance;

    private final String to;

    private final long toCurrentBalance;

    private final LocalDateTime started;

    private final LocalDateTime finished;

    public TransferResult(String from, long fromCurrentBalance, String to, long toCurrentBalance, LocalDateTime started, LocalDateTime finished) {
        this.from = from;
        this.fromCurrentBalance = fromCurrentBalance;
        this.to = to;
        this.toCurrentBalance = toCurrentBalance;
        this.started = started;
        this.finished = finished;
    }

    public String getFrom() {
        return from;
    }

    public long getFromCurrentBalance() {
        return fromCurrentBalance;
    }

    public String getTo() {
        return to;
    }

    public long getToCurrentBalance() {
        return toCurrentBalance;
    }

    public LocalDateTime getStarted() {
        return started;
    }

    public LocalDateTime getFinished() {
        return finished;
    }

    @Override
    public String toString() {
        return "TransferResult{" +
                "from='" + from + '\'' +
                ", fromCurrentBalance=" + fromCurrentBalance +
                ", to='" + to + '\'' +
                ", toCurrentBalance=" + toCurrentBalance +
                ", started=" + started +
                ", finished=" + finished +
                '}';
    }
}
