package com.hometask.moneytransfer.exception;

public class NotEnoughBalanceException extends MoneyTransferException {
    public NotEnoughBalanceException() {
        super(409, "NOT_ENOUGH_BALANCE");
    }
}
