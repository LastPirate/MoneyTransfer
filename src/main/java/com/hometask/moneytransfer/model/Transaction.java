package com.hometask.moneytransfer.model;

public class Transaction {

    private long id;
    private float quantity;
    private byte exchangeRate;
    private String description;

    private Wallet sender;
    private Wallet recipient;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public byte getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(byte exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Wallet getSender() {
        return sender;
    }

    public void setSender(Wallet sender) {
        this.sender = sender;
    }

    public Wallet getRecipient() {
        return recipient;
    }

    public void setRecipient(Wallet recipient) {
        this.recipient = recipient;
    }
}
