package com.hometask.moneytransfer.service;

import com.hometask.moneytransfer.exception.AccountAlreadyExistException;
import com.hometask.moneytransfer.exception.AccountNotFoundException;
import com.hometask.moneytransfer.exception.NotEnoughBalanceException;
import com.hometask.moneytransfer.model.db.tables.pojos.Account;

import java.math.BigDecimal;

public interface AccountService {

    Account createAccount(String name) throws AccountAlreadyExistException;

    Account getAccount(String name) throws AccountNotFoundException;

    void deleteAccount(Long accountId);

    boolean refillAccount(Long accountId, BigDecimal quantity) throws AccountNotFoundException, NotEnoughBalanceException;

    boolean withdrawFromAccount(Long accountId, BigDecimal quantity) throws AccountNotFoundException, NotEnoughBalanceException;

    boolean transferBetweenAccounts(Long senderId, Long recipientId, BigDecimal quantity) throws AccountNotFoundException, NotEnoughBalanceException;

}
