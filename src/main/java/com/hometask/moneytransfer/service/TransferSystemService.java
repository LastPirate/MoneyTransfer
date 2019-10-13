package com.hometask.moneytransfer.service;

import com.hometask.moneytransfer.model.db.tables.pojos.*;

import java.math.BigDecimal;
import java.util.List;

public interface TransferSystemService {

    void createAccount(String name);

    void createWallet(Long ownerId, String currency);

    void putMoney(Long walletId, BigDecimal quantity);

    void withdrawMoney(Long walletId, BigDecimal quantity);

    List<Transfer> getTransferBook();

    List<Transfer> getWalletTransferBook(Long walletId);

    void makeTransfer(Long senderId, Long recipientId, BigDecimal quantity, String description, Byte exchangeRate);
}
