package com.hometask.moneytransfer.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.hometask.moneytransfer.service.AccountService;

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
                accountService.createAccount(request.queryParams("name"))
        );
    }

    private void getAccount() {
        get("/getAccount", (request, response) ->
                accountService.getAccount(request.queryParams("name"))
        );
    }

    private void deleteAccount() {
        post("/deleteAccount", (request, response) -> {
                    accountService.deleteAccount(Long.parseLong(request.queryParams("id")));
                    return "account_deleted";
                }
        );
    }
}
