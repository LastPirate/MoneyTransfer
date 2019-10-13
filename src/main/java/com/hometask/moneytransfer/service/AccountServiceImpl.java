package com.hometask.moneytransfer.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.hometask.moneytransfer.model.db.tables.daos.AccountDao;
import com.hometask.moneytransfer.model.db.tables.pojos.Account;
import org.jooq.Configuration;

@Singleton
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    @Inject
    public AccountServiceImpl(@Named("DataBaseConfiguration") Configuration configuration) {
        this.accountDao = new AccountDao(configuration);
    }

    @Override
    public Account createAccount(String name) {
        Account account = new Account();
        account.setName(name);
        accountDao.insert(account);

        return accountDao.fetchOneByName(name);
    }

    @Override
    public Account getAccount(String name) {
        return accountDao.fetchOneByName(name);
    }

    @Override
    public void deleteAccount(Long accountId) {
        accountDao.deleteById(accountId);
    }

}
