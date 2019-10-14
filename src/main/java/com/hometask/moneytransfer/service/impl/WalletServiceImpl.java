package com.hometask.moneytransfer.service.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.hometask.moneytransfer.exception.AccountNotFoundException;
import com.hometask.moneytransfer.exception.WalletAlreadyExistException;
import com.hometask.moneytransfer.exception.WalletNotFoundException;
import com.hometask.moneytransfer.model.WalletCustomDao;
import com.hometask.moneytransfer.model.db.tables.Account;
import com.hometask.moneytransfer.model.db.tables.pojos.Wallet;
import com.hometask.moneytransfer.model.db.tables.records.WalletRecord;
import com.hometask.moneytransfer.service.WalletService;
import org.jooq.*;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DAOImpl;
import org.jooq.impl.UpdatableRecordImpl;

import java.util.List;
import java.util.Optional;

@Singleton
public class WalletServiceImpl implements WalletService {

    private WalletCustomDao walletCustomDao;

    @Inject
    public WalletServiceImpl(@Named("DataBaseConfiguration") Configuration configuration) {
        this.walletCustomDao = new WalletCustomDao(configuration);
    }

    @Override
    public Wallet createWallet(Long accountId, String currency) throws WalletAlreadyExistException, AccountNotFoundException {
        try {
            return walletCustomDao.insertWithResult(accountId, currency, accountId.toString() + System.currentTimeMillis());
        } catch (DataAccessException e) {
            String message = e.getMessage();
            if (message.contains("CONSTRAINT_982")) throw new AccountNotFoundException();
            if (message.contains("CONSTRAINT_INDEX_982")) throw new WalletAlreadyExistException();
            throw e;
        }
    }

    @Override
    public Wallet getWallet(Long accountId, String currency) throws WalletNotFoundException {
        Wallet wallet = walletCustomDao.fetchWalletByAccountIdAndCurrency(accountId, currency);
        if (wallet != null) {
            return wallet;
        } else throw new WalletNotFoundException();
    }

    @Override
    public List<Wallet> getWalletsByAccountId(Long accountId) {
        return walletCustomDao.fetchByAccountId(accountId);
    }

    @Override
    public void deleteWallet(Long walletId) {
        walletCustomDao.deleteById(walletId);
    }

}
