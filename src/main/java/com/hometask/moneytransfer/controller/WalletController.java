package com.hometask.moneytransfer.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.hometask.moneytransfer.service.WalletService;

import static com.hometask.moneytransfer.util.JsonUtil.json;
import static spark.Spark.get;
import static spark.Spark.post;

@Singleton
public class WalletController {

    private WalletService walletService;

    @Inject
    public WalletController(WalletService walletService) {
        this.walletService = walletService;

        //Initializing endpoints
        createWallet();
        getWallet();
        getWalletsByAccountId();
        deleteWallet();
    }

    private void createWallet() {
        post("/createWallet", (request, response) ->
                walletService.createWallet(
                        Long.parseLong(request.queryParams("accountId")),
                        request.queryParams("currency")
                ), json()
        );
    }

    private void getWallet() {
        get("/getWallet", (request, response) ->
                walletService.getWallet(
                        Long.parseLong(request.queryParams("accountId")),
                        request.queryParams("currency")
                ), json()
        );
    }

    private void getWalletsByAccountId() {
        get("/getWalletsByAccountId", (request, response) ->
                walletService.getWalletsByAccountId(
                        Long.parseLong(request.queryParams("accountId"))
                ), json()
        );
    }

    private void deleteWallet() {
        post("/deleteWallet", (request, response) -> {
                    walletService.deleteWallet(Long.parseLong(request.queryParams("id")));
                    return "{\"result\":\"wallet_deleted\"}";
                }
        );
    }

}
