package com.hometask.moneytransfer.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.hometask.moneytransfer.exception.AccountAlreadyExistException;
import com.hometask.moneytransfer.exception.AccountNotFoundException;
import com.hometask.moneytransfer.model.db.tables.daos.AccountDao;
import com.hometask.moneytransfer.model.db.tables.pojos.Account;
import org.jooq.Configuration;
import org.jooq.exception.DataAccessException;

@Singleton
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    @Inject
    public AccountServiceImpl(@Named("DataBaseConfiguration") Configuration configuration) {
        this.accountDao = new AccountDao(configuration);
    }

    @Override
    public Account createAccount(String name) throws AccountAlreadyExistException {
        Account account = new Account();
        account.setName(name);
        try {
            accountDao.insert(account);
        } catch (DataAccessException e) {
            throw new AccountAlreadyExistException();
        }

        return accountDao.fetchOneByName(name);
    }

    @Override
    public Account getAccount(String name) throws AccountNotFoundException {
        Account account = accountDao.fetchOneByName(name);
        if (account != null) {
            return account;
        } else throw new AccountNotFoundException();
    }

    @Override
    public void deleteAccount(Long accountId) {
        accountDao.deleteById(accountId);
    }

}
