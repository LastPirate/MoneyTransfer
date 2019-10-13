package com.hometask.moneytransfer.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.hometask.moneytransfer.service.TransferSystemService;

import java.math.BigDecimal;

import static spark.Spark.get;
import static spark.Spark.post;

@Singleton
public class TransferSystemController {

    private TransferSystemService transferSystemService;

    @Inject
    public TransferSystemController(TransferSystemService transferSystemService) {
        this.transferSystemService = transferSystemService;

        //Initializing endpoints
        createAccount();
        createWallet();
        getTransferBook();
        getWalletTransferBook();
        makeTransfer();
        putMoney();
        withdrawMoney();
    }

    private void createAccount() {
        post("/createAccount", (request, response) -> {
            transferSystemService.createAccount(
                    request.queryParams("name")
            );
            return "Hello, World!";
        });
    }

    private void createWallet() {
        post("/createWallet", (request, response) -> {
            transferSystemService.createWallet(
                    Long.parseLong(request.queryParams("ownerId")),
                    request.queryParams("currency")
            );
            return "Hello, World!";
        });
    }

    private void getTransferBook() {
        get("/getTransferBook", (request, response) -> {
            transferSystemService.getTransferBook();
            return "Hello, World!";
        });
    }

    private void getWalletTransferBook() {
        get("/getWalletTransferBook", (request, response) -> {
            transferSystemService.getWalletTransferBook(
                    Long.parseLong(request.queryParams("walletId"))
            );
            return "Hello, World!";
        });
    }

    private void makeTransfer() {
        post("/makeTransfer", (request, response) -> {
            transferSystemService.makeTransfer(
                    Long.parseLong(request.queryParams("senderId")),
                    Long.parseLong(request.queryParams("recipientId")),
                    new BigDecimal(request.queryParams("quantity")),
                    request.queryParams("description"),
                    request.params().containsKey("exchangeRate") ? Byte.parseByte(request.queryParams("exchangeRate")) : null
            );
            return "Hello, World!";
        });
    }

    private void putMoney() {
        post("/putMoney", (request, response) -> {
            transferSystemService.putMoney(
                    Long.parseLong(request.queryParams("walletId")),
                    new BigDecimal(request.queryParams("quantity"))
            );
            return "Hello, World!";
        });
    }

    private void withdrawMoney() {
        post("/withdrawMoney", (request, response) -> {
            transferSystemService.withdrawMoney(
                    Long.parseLong(request.queryParams("walletId")),
                    new BigDecimal(request.queryParams("quantity"))
            );
            return "Hello, World!";
        });
    }

}
