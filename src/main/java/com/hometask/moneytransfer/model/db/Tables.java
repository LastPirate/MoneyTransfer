/*
 * This file is generated by jOOQ.
 */
package com.hometask.moneytransfer.model.db;


import com.hometask.moneytransfer.model.db.tables.Account;
import com.hometask.moneytransfer.model.db.tables.Transfer;
import com.hometask.moneytransfer.model.db.tables.Wallet;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in PUBLIC
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>PUBLIC.ACCOUNT</code>.
     */
    public static final Account ACCOUNT = Account.ACCOUNT;

    /**
     * The table <code>PUBLIC.TRANSFER</code>.
     */
    public static final Transfer TRANSFER = Transfer.TRANSFER;

    /**
     * The table <code>PUBLIC.WALLET</code>.
     */
    public static final Wallet WALLET = Wallet.WALLET;
}
