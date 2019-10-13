package com.hometask.moneytransfer.service;

import com.hometask.moneytransfer.model.db.tables.pojos.Account;

public interface AccountService {

    Account createAccount(String name);

    Account getAccount(String name);

    void deleteAccount(Long accountId);

}
