package com.hometask.moneytransfer.exception;


public class AccountAlreadyExistException extends MoneyTransferException {

    public AccountAlreadyExistException() {
        super(409, "ACCOUNT_ALREADY_EXIST");
    }
}
