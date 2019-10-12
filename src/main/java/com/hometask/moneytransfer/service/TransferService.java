package com.hometask.moneytransfer.service;

import com.hometask.moneytransfer.model.Account;
import com.hometask.moneytransfer.model.Transaction;
import com.hometask.moneytransfer.model.Wallet;

import java.util.List;

public interface TransferService {

    Account createAccount(String name);

    Wallet createWallet();

    List<Transaction> getTransferBook();

    void makeTransfer();

    void putMoney();

    void withdrawMoney();

}
