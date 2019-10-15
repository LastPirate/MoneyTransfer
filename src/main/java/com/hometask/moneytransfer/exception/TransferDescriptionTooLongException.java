package com.hometask.moneytransfer.exception;

public class TransferDescriptionTooLongException extends MoneyTransferException {

    public TransferDescriptionTooLongException() {
        super(409, "TRANSFER_DESCRIPTION_TOO_LONG");
    }
}
