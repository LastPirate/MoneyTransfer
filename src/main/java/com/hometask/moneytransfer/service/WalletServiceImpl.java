package com.hometask.moneytransfer.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.hometask.moneytransfer.model.WalletCustomDao;
import com.hometask.moneytransfer.model.db.tables.pojos.Wallet;
import org.jooq.Configuration;

import java.util.List;

@Singleton
public class WalletServiceImpl implements WalletService {

    private WalletCustomDao walletCustomDao;

    @Inject
    public WalletServiceImpl(@Named("DataBaseConfiguration") Configuration configuration) {
        this.walletCustomDao = new WalletCustomDao(configuration);
    }

    @Override
    public Wallet createWallet(Long accountId, String currency) {
        Wallet wallet = new Wallet();
        wallet.setAccountId(accountId);
        wallet.setCurrency(currency);
        wallet.setAddress(accountId.toString() + System.currentTimeMillis());
        walletCustomDao.insert(wallet);

        return walletCustomDao.fetchWalletByAccountIdAndCurrency(accountId, currency);
    }

    @Override
    public Wallet getWallet(Long accountId, String currency) {
        return walletCustomDao.fetchWalletByAccountIdAndCurrency(accountId, currency);
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
