package ru.playtox.testcase.multithreadedtransferfromaccounts.scheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;

public class RandomTransferManager {

    private static final Logger LOG = LogManager.getLogger(RandomTransferManager.class.getSimpleName());

    private long transferMoneyLimit;
    private RandomTransfer randomTransfer;

    public RandomTransferManager(RandomTransfer randomTransfer, long transferMoneyLimit) {
        LOG.trace("create");
        this.transferMoneyLimit = transferMoneyLimit;
        this.randomTransfer = randomTransfer;
    }

    public void run(int repeats, int maxThread, long minSleepTime, long maxSleepTime) {
        LOG.trace("execute run");
        AtomicInteger maxRepeats = new AtomicInteger(repeats);
        Thread[] threads = new Thread[maxThread];

        //run all threads;
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                while (maxRepeats.decrementAndGet() >= 0) {
                    this.executeTransfer(minSleepTime, maxSleepTime);
                }
            });
            LOG.trace("Thread - {} created", i + 1);
            threads[i].start();
        }

        //wait finished all threads
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            LOG.trace("Thread - {} finished", i + 1);
        }

        LOG.trace("all thread finished");
    }

    private void executeTransfer(long minSleepTime, long maxSleepTime) {
        try {
            Thread.sleep(minSleepTime + (long) (Math.random() * maxSleepTime));
            this.randomTransfer.run(this.transferMoneyLimit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
