package com.hometask.moneytransfer.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.hometask.moneytransfer.service.TransferService;

import static spark.Spark.get;
import static spark.Spark.post;

@Singleton
public class TransferController {

    private TransferService transferService;

    @Inject
    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    public void initEndpoints() {
        createAccount();
        createWallet();
        getTransferBook();
        makeTransfer();
        putMoney();
        withdrawMoney();
    }

    private void createAccount() {
        post("/createAccount", (request, response) -> {
            transferService.createAccount("");
            return "Hello, World!";
        });
    }

    private void createWallet() {
        post("/createWallet", (request, response) -> {
            transferService.createWallet();
            return "Hello, World!";
        });
    }

    private void getTransferBook() {
        get("/getTransferBook", (request, response) -> {
            transferService.getTransferBook();
            return "Hello, World!";
        });
    }

    private void makeTransfer() {
        post("/makeTransfer", (request, response) -> {
            transferService.makeTransfer();
            return "Hello, World!";
        });
    }

    private void putMoney() {
        post("/putMoney", (request, response) -> {
            transferService.putMoney();
            return "Hello, World!";
        });
    }

    private void withdrawMoney() {
        post("/withdrawMoney", (request, response) -> {
            transferService.withdrawMoney();
            return "Hello, World!";
        });
    }

}
