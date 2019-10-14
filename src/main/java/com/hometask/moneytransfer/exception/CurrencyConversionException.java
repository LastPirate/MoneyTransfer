package com.hometask.moneytransfer.exception;

import com.google.gson.annotations.Expose;

public class CurrencyConversionException extends MoneyTransferException {

    @Expose
    private final double rateOffer;

    public CurrencyConversionException(double rateOffer) {
        super(449, "CURRENCY_CONVERSION");
        this.rateOffer = rateOffer;
    }

}
