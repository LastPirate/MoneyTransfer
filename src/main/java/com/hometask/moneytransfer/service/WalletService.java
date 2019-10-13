package com.hometask.moneytransfer.service;

import com.hometask.moneytransfer.model.db.tables.pojos.Wallet;

import java.util.List;

public interface WalletService {

    Wallet createWallet(Long accountId, String currency);

    Wallet getWallet(Long accountId, String currency);

    List<Wallet> getWalletsByAccountId(Long accountId);

    void deleteWallet(Long walletId);

}
