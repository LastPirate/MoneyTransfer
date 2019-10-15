package com.hometask.moneytransfer.exception;

public class CurrencyTickerTooLongException extends MoneyTransferException {

    public CurrencyTickerTooLongException() {
        super(409, "CURRENCY_TICKER_TOO_LONG");
    }
}
