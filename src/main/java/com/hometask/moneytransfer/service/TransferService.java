package com.hometask.moneytransfer.service;

import com.hometask.moneytransfer.model.db.tables.pojos.*;

import java.math.BigDecimal;
import java.util.List;

public interface TransferService {

    Transfer refillTransfer(Long walletId, BigDecimal quantity);

    Transfer payoutTransfer(Long walletId, BigDecimal quantity);

    Transfer customerTransfer(Long senderId, Long recipientId, BigDecimal quantity, String description, Double exchangeRate);

    List<Transfer> getTransferBook();

    List<Transfer> getWalletTransferBook(Long walletId);

}
