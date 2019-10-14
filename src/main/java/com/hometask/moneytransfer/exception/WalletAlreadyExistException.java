package com.hometask.moneytransfer.exception;

public class WalletAlreadyExistException extends MoneyTransferException {

    public WalletAlreadyExistException() {
        super(409, "WALLET_ALREADY_EXIST");
    }
}
