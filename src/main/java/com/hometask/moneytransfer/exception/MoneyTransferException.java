package com.hometask.moneytransfer.exception;

public abstract class MoneyTransferException extends Exception {

    private final int code;
    private final String message;

    MoneyTransferException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
