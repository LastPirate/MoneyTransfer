package com.hometask.moneytransfer.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.hometask.moneytransfer.model.tables.daos.AccountDao;
import com.hometask.moneytransfer.model.tables.daos.TransferDao;
import com.hometask.moneytransfer.model.tables.daos.WalletDao;
import com.hometask.moneytransfer.model.tables.pojos.*;
import org.jooq.Configuration;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class TransferServiceImpl implements TransferService {

    private AccountDao accountDao;
    private WalletDao walletDao;
    private TransferDao transferDao;

    @Inject
    public TransferServiceImpl(@Named("DataBaseConfiguration") Configuration configuration) {
        this.accountDao = new AccountDao(configuration);
        this.walletDao = new WalletDao(configuration);
        this.transferDao = new TransferDao(configuration);
    }

    public Account createAccount(String name) {
        return new Account();
    }

    @Override
    public Wallet createWallet() {
        return new Wallet();
    }

    @Override
    public void putMoney() {

    }

    @Override
    public void withdrawMoney() {

    }

    @Override
    public List<Transfer> getTransferBook() {
        return new ArrayList<>();
    }

    @Override
    public Transfer makeTransfer() {
        return new Transfer();
    }

    private byte getExchangeRate() {
        return 1;
    }

}
