package com.hometask.moneytransfer.service;

import com.hometask.moneytransfer.exception.AccountNotFoundException;
import com.hometask.moneytransfer.exception.CurrencyConversionException;
import com.hometask.moneytransfer.exception.NotEnoughBalanceException;
import com.hometask.moneytransfer.model.db.tables.pojos.*;

import java.math.BigDecimal;
import java.util.List;

public interface TransferService {

    Transfer refillTransfer(Long walletId, BigDecimal quantity) throws AccountNotFoundException, NotEnoughBalanceException, CurrencyConversionException;

    Transfer payoutTransfer(Long walletId, BigDecimal quantity) throws AccountNotFoundException, NotEnoughBalanceException, CurrencyConversionException;

    Transfer customerTransfer(Long senderId, Long recipientId, BigDecimal quantity, String description, Double exchangeRate) throws AccountNotFoundException, NotEnoughBalanceException, CurrencyConversionException;

    List<Transfer> getTransferBook();

    List<Transfer> getWalletTransferBook(Long walletId);

}
