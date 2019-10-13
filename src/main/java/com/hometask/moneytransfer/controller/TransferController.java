package com.hometask.moneytransfer.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.hometask.moneytransfer.service.TransferService;

import java.math.BigDecimal;

import static spark.Spark.get;
import static spark.Spark.post;

@Singleton
public class TransferController {

    private TransferService transferService;

    @Inject
    public TransferController(TransferService transferService) {
        this.transferService = transferService;

        //Initializing endpoints
        refillTransfer();
        payoutTransfer();
        customerTransfer();
        getTransferBook();
        getWalletTransferBook();
    }

    private void refillTransfer() {
        post("/refill", (request, response) ->
                transferService.refillTransfer(
                        Long.parseLong(request.queryParams("walletId")),
                        new BigDecimal(request.queryParams("quantity"))
                )
        );
    }

    private void payoutTransfer() {
        post("/payout", (request, response) ->
                transferService.payoutTransfer(
                        Long.parseLong(request.queryParams("walletId")),
                        new BigDecimal(request.queryParams("quantity"))
                )
        );
    }

    private void customerTransfer() {
        post("/transfer", (request, response) ->
                transferService.customerTransfer(
                        Long.parseLong(request.queryParams("senderId")),
                        Long.parseLong(request.queryParams("recipientId")),
                        new BigDecimal(request.queryParams("quantity")),
                        request.queryParams("description"),
                        request.params().containsKey("exchangeRate") ? Byte.parseByte(request.queryParams("exchangeRate")) : null
                )
        );
    }

    private void getTransferBook() {
        get("/getTransferBook", (request, response) ->
                transferService.getTransferBook()
        );
    }

    private void getWalletTransferBook() {
        get("/getWalletTransferBook", (request, response) ->
                transferService.getWalletTransferBook(
                        Long.parseLong(request.queryParams("walletId"))
                )
        );
    }

}
