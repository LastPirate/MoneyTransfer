package com.hometask.moneytransfer.exception;

public class AccountNotFoundException extends MoneyTransferException {

    public AccountNotFoundException() {
        super(404, "ACCOUNT_NOT_FOUND");
    }
}
