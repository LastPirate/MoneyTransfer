package com.hometask.moneytransfer.exception;

public class WalletNotFoundException extends MoneyTransferException {

    public WalletNotFoundException() {
        super(404, "WALLET_NOT_FOUND");
    }
}
