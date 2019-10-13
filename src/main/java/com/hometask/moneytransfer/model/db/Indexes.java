/*
 * This file is generated by jOOQ.
 */
package com.hometask.moneytransfer.model.db;


import com.hometask.moneytransfer.model.db.tables.Account;
import com.hometask.moneytransfer.model.db.tables.Transfer;
import com.hometask.moneytransfer.model.db.tables.Wallet;

import javax.annotation.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables of the <code>PUBLIC</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index CONSTRAINT_INDEX_E = Indexes0.CONSTRAINT_INDEX_E;
    public static final Index PRIMARY_KEY_E = Indexes0.PRIMARY_KEY_E;
    public static final Index CONSTRAINT_INDEX_7 = Indexes0.CONSTRAINT_INDEX_7;
    public static final Index CONSTRAINT_INDEX_7A = Indexes0.CONSTRAINT_INDEX_7A;
    public static final Index PRIMARY_KEY_7 = Indexes0.PRIMARY_KEY_7;
    public static final Index CONSTRAINT_INDEX_9 = Indexes0.CONSTRAINT_INDEX_9;
    public static final Index PRIMARY_KEY_9 = Indexes0.PRIMARY_KEY_9;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index CONSTRAINT_INDEX_E = Internal.createIndex("CONSTRAINT_INDEX_E", Account.ACCOUNT, new OrderField[] { Account.ACCOUNT.NAME }, true);
        public static Index PRIMARY_KEY_E = Internal.createIndex("PRIMARY_KEY_E", Account.ACCOUNT, new OrderField[] { Account.ACCOUNT.ID }, true);
        public static Index CONSTRAINT_INDEX_7 = Internal.createIndex("CONSTRAINT_INDEX_7", Transfer.TRANSFER, new OrderField[] { Transfer.TRANSFER.SENDER_ID }, false);
        public static Index CONSTRAINT_INDEX_7A = Internal.createIndex("CONSTRAINT_INDEX_7A", Transfer.TRANSFER, new OrderField[] { Transfer.TRANSFER.RECIPIENT_ID }, false);
        public static Index PRIMARY_KEY_7 = Internal.createIndex("PRIMARY_KEY_7", Transfer.TRANSFER, new OrderField[] { Transfer.TRANSFER.ID }, true);
        public static Index CONSTRAINT_INDEX_9 = Internal.createIndex("CONSTRAINT_INDEX_9", Wallet.WALLET, new OrderField[] { Wallet.WALLET.ACCOUNT_ID }, false);
        public static Index PRIMARY_KEY_9 = Internal.createIndex("PRIMARY_KEY_9", Wallet.WALLET, new OrderField[] { Wallet.WALLET.ID }, true);
    }
}