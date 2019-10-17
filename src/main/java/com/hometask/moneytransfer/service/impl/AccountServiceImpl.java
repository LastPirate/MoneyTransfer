package com.hometask.moneytransfer.service.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.hometask.moneytransfer.exception.AccountAlreadyExistException;
import com.hometask.moneytransfer.exception.AccountNotFoundException;
import com.hometask.moneytransfer.exception.NotEnoughBalanceException;
import com.hometask.moneytransfer.model.AccountCustomDao;
import com.hometask.moneytransfer.model.db.tables.pojos.Account;
import com.hometask.moneytransfer.service.AccountService;
import org.jooq.*;
import org.jooq.exception.DataAccessException;

import java.math.BigDecimal;
import java.util.Optional;

@Singleton
public class AccountServiceImpl implements AccountService {

    private AccountCustomDao accountCustomDao;

    @Inject
    public AccountServiceImpl(@Named("DataBaseConfiguration") Configuration configuration) {
        this.accountCustomDao = new AccountCustomDao(configuration);
    }

    @Override
    public Account createAccount(String name) throws AccountAlreadyExistException {
        try {
            return accountCustomDao.insertWithResult(name);
        } catch (DataAccessException e) {
            throw new AccountAlreadyExistException();
        }
    }

    @Override
    public Account getAccount(String name) throws AccountNotFoundException {
        Account account = accountCustomDao.fetchOneByName(name);
        if (account != null) {
            return account;
        } else throw new AccountNotFoundException();
    }

    @Override
    public void deleteAccount(Long accountId) {
        accountCustomDao.deleteById(accountId);
    }

    @Override
    public boolean refillAccount(Long accountId, BigDecimal quantity) throws AccountNotFoundException, NotEnoughBalanceException {
        //Simulation of receipt of money in the transfer system
        Account main = accountCustomDao.findById(1L);
        main.setBalance(quantity);
        accountCustomDao.update(main);

        return transferBetweenAccounts(main.getId(), accountId, quantity);
    }

    @Override
    public boolean withdrawFromAccount(Long accountId, BigDecimal quantity) throws AccountNotFoundException, NotEnoughBalanceException {
        // Modeling of withdrawal of money from the transfer system
        Account main = accountCustomDao.findById(1L);
        main.setBalance(new BigDecimal(-1 * quantity.doubleValue()));
        accountCustomDao.update(main);

        return transferBetweenAccounts(accountId, main.getId(), quantity);
    }

    @Override
    public boolean transferBetweenAccounts(Long senderId, Long recipientId, BigDecimal quantity)
            throws AccountNotFoundException, NotEnoughBalanceException {
        Account sender = Optional.ofNullable(accountCustomDao.findById(senderId)).orElseThrow(AccountNotFoundException::new);
        if (sender.getBalance().doubleValue() < quantity.doubleValue()) throw new NotEnoughBalanceException();
        Account recipient = Optional.ofNullable(accountCustomDao.findById(recipientId)).orElseThrow(AccountNotFoundException::new);

        sender.setBalance(sender.getBalance().subtract(quantity));
        recipient.setBalance(recipient.getBalance().add(quantity));

        try {
            accountCustomDao.update(sender, recipient);
        } catch (Exception e) {
            return false;
        }

        return true;
    }


}
