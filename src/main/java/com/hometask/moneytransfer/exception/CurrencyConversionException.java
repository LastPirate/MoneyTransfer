package com.hometask.moneytransfer.exception;

public class CurrencyConversionException extends Exception {

    private final int code = 449;
    private final double rateOffer;

    public CurrencyConversionException(double rateOffer) {
        this.rateOffer = rateOffer;
    }

    public double getRateOffer() {
        return rateOffer;
    }
}
