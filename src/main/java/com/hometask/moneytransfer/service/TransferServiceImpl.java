package com.hometask.moneytransfer.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.hometask.moneytransfer.model.TransferCustomDao;
import com.hometask.moneytransfer.model.WalletCustomDao;
import com.hometask.moneytransfer.model.db.tables.pojos.*;
import org.jooq.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Singleton
public class TransferServiceImpl implements TransferService {

    private TransferCustomDao transferCustomDao;
    private WalletCustomDao walletCustomDao;

    @Inject
    public TransferServiceImpl(@Named("DataBaseConfiguration") Configuration configuration) {
        this.transferCustomDao = new TransferCustomDao(configuration);
        this.walletCustomDao = new WalletCustomDao(configuration);
    }

    @Override
    public Transfer refillTransfer(Long walletId, BigDecimal quantity) {
        //Simulation of receipt of money in the transfer system
        Wallet main = walletCustomDao.findById(0L);
        main.setBalance(quantity);
        walletCustomDao.update(main);

        return customerTransfer(main.getId(), walletId, quantity, "Refill Wallet", 1.0);
    }

    @Override
    public Transfer payoutTransfer(Long walletId, BigDecimal quantity) {
        // Modeling of withdrawal of money from the transfer system
        Wallet main = walletCustomDao.findById(0L);
        main.setBalance(new BigDecimal(-1 * quantity.doubleValue()));
        walletCustomDao.update(main);

        return customerTransfer(walletId, main.getId(), quantity, "Refill Wallet", 1.0);
    }

    @Override
    public Transfer customerTransfer(Long senderId, Long recipientId, BigDecimal quantity, String description, Double exchangeRate) {
        Wallet sender = walletCustomDao.findById(senderId);

        if (sender.getBalance().doubleValue() < quantity.doubleValue()) {
            //TODO exception
        }

        Wallet recipient = walletCustomDao.findById(recipientId);

        Transfer transfer = new Transfer();
        transfer.setSenderId(senderId);
        transfer.setRecipientId(recipientId);
        transfer.setQuantity(quantity);
        transfer.setDescription(description);

        if (!sender.getCurrency().equals(recipient.getCurrency())) {
            if (exchangeRate != null) {
                transfer.setExchangeRate(exchangeRate);
                quantity = quantity.multiply(new BigDecimal(exchangeRate));
            } else {
                double rate = Math.random() * 2;
                //TODO exception with rate
            }
        }

        Transfer result = transferCustomDao.insertWithResult(transfer);

        sender.setBalance(sender.getBalance().subtract(transfer.getQuantity()));
        recipient.setBalance(recipient.getBalance().add(quantity));
        walletCustomDao.update(sender, recipient);

        return result;
    }

    @Override
    public List<Transfer> getTransferBook() {
        return transferCustomDao.findAll();
    }

    @Override
    public List<Transfer> getWalletTransferBook(Long walletId) {
        return transferCustomDao.fetchByWalletId(walletId);
    }

}
