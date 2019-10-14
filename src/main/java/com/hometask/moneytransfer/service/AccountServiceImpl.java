package com.hometask.moneytransfer.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.hometask.moneytransfer.exception.AccountAlreadyExistException;
import com.hometask.moneytransfer.exception.AccountNotFoundException;
import com.hometask.moneytransfer.model.AccountCustomDao;
import com.hometask.moneytransfer.model.db.tables.pojos.Account;
import org.jooq.*;
import org.jooq.exception.DataAccessException;

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

}
