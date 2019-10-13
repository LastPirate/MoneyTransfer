/*
 * This file is generated by jOOQ.
 */
package com.hometask.moneytransfer.model.db.tables.pojos;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Transfer implements Serializable {

    private static final long serialVersionUID = 717737678;

    private Long          id;
    private BigDecimal    quantity;
    private LocalDateTime moment;
    private Double        exchangeRate;
    private String        description;
    private Long          senderId;
    private Long          recipientId;

    public Transfer() {}

    public Transfer(Transfer value) {
        this.id = value.id;
        this.quantity = value.quantity;
        this.moment = value.moment;
        this.exchangeRate = value.exchangeRate;
        this.description = value.description;
        this.senderId = value.senderId;
        this.recipientId = value.recipientId;
    }

    public Transfer(
        Long          id,
        BigDecimal    quantity,
        LocalDateTime moment,
        Double        exchangeRate,
        String        description,
        Long          senderId,
        Long          recipientId
    ) {
        this.id = id;
        this.quantity = quantity;
        this.moment = moment;
        this.exchangeRate = exchangeRate;
        this.description = description;
        this.senderId = senderId;
        this.recipientId = recipientId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getQuantity() {
        return this.quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getMoment() {
        return this.moment;
    }

    public void setMoment(LocalDateTime moment) {
        this.moment = moment;
    }

    public Double getExchangeRate() {
        return this.exchangeRate;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getSenderId() {
        return this.senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getRecipientId() {
        return this.recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Transfer (");

        sb.append(id);
        sb.append(", ").append(quantity);
        sb.append(", ").append(moment);
        sb.append(", ").append(exchangeRate);
        sb.append(", ").append(description);
        sb.append(", ").append(senderId);
        sb.append(", ").append(recipientId);

        sb.append(")");
        return sb.toString();
    }
}
