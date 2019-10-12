package com.hometask.moneytransfer.model;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private long id;
    private String name;
    private List<Wallet> wallets = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Wallet> getWallets() {
        return wallets;
    }

    public void setWallets(List<Wallet> wallets) {
        this.wallets = wallets;
    }
}
