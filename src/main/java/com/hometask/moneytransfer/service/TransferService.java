package com.hometask.moneytransfer.service;

import com.hometask.moneytransfer.model.db.tables.*;

import java.util.List;

public interface TransferService {

    Account createAccount(String name);

    Wallet createWallet();

    List<Transfer> getTransferBook();

    void makeTransfer();

    void putMoney();

    void withdrawMoney();
}
