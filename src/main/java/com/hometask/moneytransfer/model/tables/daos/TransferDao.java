/*
 * This file is generated by jOOQ.
 */
package com.hometask.moneytransfer.model.tables.daos;


import com.hometask.moneytransfer.model.tables.Transfer;
import com.hometask.moneytransfer.model.tables.records.TransferRecord;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


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
public class TransferDao extends DAOImpl<TransferRecord, com.hometask.moneytransfer.model.tables.pojos.Transfer, Long> {

    /**
     * Create a new TransferDao without any configuration
     */
    public TransferDao() {
        super(Transfer.TRANSFER, com.hometask.moneytransfer.model.tables.pojos.Transfer.class);
    }

    /**
     * Create a new TransferDao with an attached configuration
     */
    public TransferDao(Configuration configuration) {
        super(Transfer.TRANSFER, com.hometask.moneytransfer.model.tables.pojos.Transfer.class, configuration);
    }

    @Override
    public Long getId(com.hometask.moneytransfer.model.tables.pojos.Transfer object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>ID BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hometask.moneytransfer.model.tables.pojos.Transfer> fetchRangeOfId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Transfer.TRANSFER.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>ID IN (values)</code>
     */
    public List<com.hometask.moneytransfer.model.tables.pojos.Transfer> fetchById(Long... values) {
        return fetch(Transfer.TRANSFER.ID, values);
    }

    /**
     * Fetch a unique record that has <code>ID = value</code>
     */
    public com.hometask.moneytransfer.model.tables.pojos.Transfer fetchOneById(Long value) {
        return fetchOne(Transfer.TRANSFER.ID, value);
    }

    /**
     * Fetch records that have <code>QUANTITY BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hometask.moneytransfer.model.tables.pojos.Transfer> fetchRangeOfQuantity(BigDecimal lowerInclusive, BigDecimal upperInclusive) {
        return fetchRange(Transfer.TRANSFER.QUANTITY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>QUANTITY IN (values)</code>
     */
    public List<com.hometask.moneytransfer.model.tables.pojos.Transfer> fetchByQuantity(BigDecimal... values) {
        return fetch(Transfer.TRANSFER.QUANTITY, values);
    }

    /**
     * Fetch records that have <code>EXCHANGE_RATE BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hometask.moneytransfer.model.tables.pojos.Transfer> fetchRangeOfExchangeRate(Byte lowerInclusive, Byte upperInclusive) {
        return fetchRange(Transfer.TRANSFER.EXCHANGE_RATE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>EXCHANGE_RATE IN (values)</code>
     */
    public List<com.hometask.moneytransfer.model.tables.pojos.Transfer> fetchByExchangeRate(Byte... values) {
        return fetch(Transfer.TRANSFER.EXCHANGE_RATE, values);
    }

    /**
     * Fetch records that have <code>DESCRIPTION BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hometask.moneytransfer.model.tables.pojos.Transfer> fetchRangeOfDescription(String lowerInclusive, String upperInclusive) {
        return fetchRange(Transfer.TRANSFER.DESCRIPTION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>DESCRIPTION IN (values)</code>
     */
    public List<com.hometask.moneytransfer.model.tables.pojos.Transfer> fetchByDescription(String... values) {
        return fetch(Transfer.TRANSFER.DESCRIPTION, values);
    }

    /**
     * Fetch records that have <code>SENDER_ID BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hometask.moneytransfer.model.tables.pojos.Transfer> fetchRangeOfSenderId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Transfer.TRANSFER.SENDER_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>SENDER_ID IN (values)</code>
     */
    public List<com.hometask.moneytransfer.model.tables.pojos.Transfer> fetchBySenderId(Long... values) {
        return fetch(Transfer.TRANSFER.SENDER_ID, values);
    }

    /**
     * Fetch records that have <code>RECIPIENT_ID BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hometask.moneytransfer.model.tables.pojos.Transfer> fetchRangeOfRecipientId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Transfer.TRANSFER.RECIPIENT_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>RECIPIENT_ID IN (values)</code>
     */
    public List<com.hometask.moneytransfer.model.tables.pojos.Transfer> fetchByRecipientId(Long... values) {
        return fetch(Transfer.TRANSFER.RECIPIENT_ID, values);
    }
}
