package com.hometask.moneytransfer.service;

import com.hometask.moneytransfer.exception.AccountAlreadyExistException;
import com.hometask.moneytransfer.exception.AccountNotFoundException;
import com.hometask.moneytransfer.model.db.tables.pojos.Account;

public interface AccountService {

    Account createAccount(String name) throws AccountAlreadyExistException;

    Account getAccount(String name) throws AccountNotFoundException;

    void deleteAccount(Long accountId);

}
