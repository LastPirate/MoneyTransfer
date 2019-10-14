package com.hometask.moneytransfer.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.hometask.moneytransfer.service.AccountService;

import static com.hometask.moneytransfer.util.JsonUtil.json;
import static spark.Spark.get;
import static spark.Spark.post;

@Singleton
public class AccountController {

    private AccountService accountService;

    @Inject
    public AccountController(AccountService accountService) {
        this.accountService = accountService;

        //Initializing endpoints
        createAccount();
        getAccount();
        deleteAccount();
    }

    private void createAccount() {
        post("/createAccount", (request, response) ->
                accountService.createAccount(request.queryParams("name")),
                json()
        );
    }

    private void getAccount() {
        get("/getAccount", (request, response) ->
                accountService.getAccount(request.queryParams("name")),
                json()
        );
    }

    private void deleteAccount() {
        post("/deleteAccount", (request, response) -> {
                    accountService.deleteAccount(Long.parseLong(request.queryParams("id")));
                    return "{\"result\":\"account_deleted\"}";
                }
        );
    }
}
