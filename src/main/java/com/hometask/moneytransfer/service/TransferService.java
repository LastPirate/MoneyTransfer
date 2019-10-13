package com.hometask.moneytransfer.service;

import com.hometask.moneytransfer.model.tables.pojos.*;

import java.util.List;

public interface TransferService {

    Account createAccount(String name);

    Wallet createWallet();

    void putMoney();

    void withdrawMoney();

    List<Transfer> getTransferBook();

    Transfer makeTransfer();
}
