package com.hometask.moneytransfer.service;

import com.hometask.moneytransfer.exception.AccountNotFoundException;
import com.hometask.moneytransfer.exception.WalletAlreadyExistException;
import com.hometask.moneytransfer.exception.WalletNotFoundException;
import com.hometask.moneytransfer.model.db.tables.pojos.Wallet;

import java.util.List;

public interface WalletService {

    Wallet createWallet(Long accountId, String currency) throws WalletAlreadyExistException, AccountNotFoundException;

    Wallet getWallet(Long accountId, String currency) throws WalletNotFoundException;

    List<Wallet> getWalletsByAccountId(Long accountId);

    void deleteWallet(Long walletId);

}
