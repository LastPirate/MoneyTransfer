package com.hometask.moneytransfer.service;

import com.google.inject.Singleton;
import com.hometask.moneytransfer.model.db.tables.*;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class TransferServiceImpl implements TransferService {

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
    public void makeTransfer() {

    }

    private byte getExchangeRate() {
        return 1;
    }

}
