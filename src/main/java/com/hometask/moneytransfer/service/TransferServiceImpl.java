package com.hometask.moneytransfer.service;

import com.google.inject.Singleton;
import com.hometask.moneytransfer.model.Account;
import com.hometask.moneytransfer.model.Transaction;
import com.hometask.moneytransfer.model.Wallet;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class TransferServiceImpl implements TransferService {

    public Account createAccount(String name) {
        return new Account();
    }

    public Wallet createWallet() {
        return new Wallet();
    }

    public List<Transaction> getTransferBook() {
        return new ArrayList<>();
    }

    public void makeTransfer() {

    }

    private byte getExchangeRate() {
        return 1;
    }

    public void putMoney() {

    }

    public void withdrawMoney() {

    }

}