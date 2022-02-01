package ru.playtox.testcase.multithreadedtransferfromaccounts.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.playtox.testcase.multithreadedtransferfromaccounts.dao.Account;
import ru.playtox.testcase.multithreadedtransferfromaccounts.dao.AccountRepository;
import ru.playtox.testcase.multithreadedtransferfromaccounts.dao.WriteWithoutLockException;

public class LoggingProxyAccountService extends AccountService {

    private static final Logger LOG = LogManager.getLogger(AccountService.class.getSimpleName());

    public LoggingProxyAccountService(AccountRepository repository) {
        super(repository);
    }

    @Override
    public TransferResult transferFromTo(Account from, Account to, long sum) {
        try {
            TransferResult transferResult = super.transferFromTo(from, to, sum);
            LOG.info("the transfer from account={} to account={} in the amount of={}$ was completed successfully.\n" +
                            "Account {} has {}$ on the balance;\n" +
                            "Account {} has {}$ on the balance;\n",
                    from.getId(),
                    to.getId(),
                    sum,
                    transferResult.getFrom(),
                    transferResult.getFromCurrentBalance(),
                    transferResult.getTo(),
                    transferResult.getToCurrentBalance()
            );
            return transferResult;
        } catch (NotMoneyForTransferException e) {
            LOG.info("The transfer from account={} to account={} in the amount of={}$ was completed unsuccessfully.",
                    from.getId(),
                    to.getId(),
                    sum
            );
            LOG.warn(e.getMessage());
            throw e;
        } catch (WriteWithoutLockException e) {
            LOG.fatal(e.getMessage());
            throw e;
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw e;
        }
    }
}
