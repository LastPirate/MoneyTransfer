package com.hometask.moneytransfer.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.hometask.moneytransfer.model.TransferCustomDao;
import com.hometask.moneytransfer.model.db.tables.daos.AccountDao;
import com.hometask.moneytransfer.model.db.tables.daos.WalletDao;
import com.hometask.moneytransfer.model.db.tables.pojos.*;
import org.jooq.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Singleton
public class TransferSystemServiceImpl implements TransferSystemService {

    private AccountDao accountDao;
    private WalletDao walletDao;
    private TransferCustomDao transferCustomDao;

    @Inject
    public TransferSystemServiceImpl(@Named("DataBaseConfiguration") Configuration configuration) {
        this.accountDao = new AccountDao(configuration);
        this.walletDao = new WalletDao(configuration);
        this.transferCustomDao = new TransferCustomDao(configuration);
    }

    @Override
    public void createAccount(String name) {
        Account account = new Account();
        account.setName(name);
        accountDao.insert(account);
    }

    @Override
    public void createWallet(Long ownerId, String currency) {
        Wallet wallet = new Wallet();
        wallet.setAccountId(ownerId);
        wallet.setCurrency(currency);
        wallet.setAddress(ownerId.toString() + System.currentTimeMillis());
        walletDao.insert(wallet);
    }

    @Override
    public void putMoney(Long walletId, BigDecimal quantity) {
        Wallet wallet = walletDao.findById(walletId);
        wallet.setBalance(wallet.getBalance().add(quantity));
        walletDao.update(wallet);
    }

    @Override
    public void withdrawMoney(Long walletId, BigDecimal quantity) {
        Wallet wallet = walletDao.findById(walletId);
        wallet.setBalance(wallet.getBalance().subtract(quantity));
        walletDao.update(wallet);
    }

    @Override
    public List<Transfer> getTransferBook() {
        return transferCustomDao.findAll();
    }

    @Override
    public List<Transfer> getWalletTransferBook(Long walletId) {
        return transferCustomDao.fetchByWalletId(walletId);
    }

    @Override
    public void makeTransfer(Long senderId, Long recipientId, BigDecimal quantity, String description, Byte exchangeRate) {
        Wallet sender = walletDao.findById(senderId);

        if (sender.getBalance().doubleValue() < quantity.doubleValue()) {
            //TODO exception
        }

        Wallet recipient = walletDao.findById(recipientId);

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
                getExchangeRate();
                //TODO exception with rate
            }
        } else {
            transfer.setExchangeRate((byte) 1);
        }

        transferCustomDao.insert(transfer);

        sender.setBalance(sender.getBalance().subtract(transfer.getQuantity()));
        recipient.setBalance(recipient.getBalance().add(quantity));
        walletDao.update(sender, recipient);
    }

    private byte getExchangeRate() {
        return (byte) (Math.random() * 2);
    }

}
