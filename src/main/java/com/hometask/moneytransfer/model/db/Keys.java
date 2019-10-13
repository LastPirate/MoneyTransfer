/*
 * This file is generated by jOOQ.
 */
package com.hometask.moneytransfer.model.db;


import com.hometask.moneytransfer.model.db.tables.Account;
import com.hometask.moneytransfer.model.db.tables.Transfer;
import com.hometask.moneytransfer.model.db.tables.Wallet;
import com.hometask.moneytransfer.model.db.tables.records.AccountRecord;
import com.hometask.moneytransfer.model.db.tables.records.TransferRecord;
import com.hometask.moneytransfer.model.db.tables.records.WalletRecord;

import javax.annotation.Generated;

import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>PUBLIC</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<AccountRecord, Long> IDENTITY_ACCOUNT = Identities0.IDENTITY_ACCOUNT;
    public static final Identity<TransferRecord, Long> IDENTITY_TRANSFER = Identities0.IDENTITY_TRANSFER;
    public static final Identity<WalletRecord, Long> IDENTITY_WALLET = Identities0.IDENTITY_WALLET;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<AccountRecord> CONSTRAINT_E = UniqueKeys0.CONSTRAINT_E;
    public static final UniqueKey<AccountRecord> CONSTRAINT_E4 = UniqueKeys0.CONSTRAINT_E4;
    public static final UniqueKey<TransferRecord> CONSTRAINT_7 = UniqueKeys0.CONSTRAINT_7;
    public static final UniqueKey<WalletRecord> CONSTRAINT_9 = UniqueKeys0.CONSTRAINT_9;
    public static final UniqueKey<WalletRecord> CONSTRAINT_98 = UniqueKeys0.CONSTRAINT_98;
    public static final UniqueKey<WalletRecord> CONSTRAINT_982D = UniqueKeys0.CONSTRAINT_982D;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<TransferRecord, WalletRecord> CONSTRAINT_7A = ForeignKeys0.CONSTRAINT_7A;
    public static final ForeignKey<TransferRecord, WalletRecord> CONSTRAINT_7AF = ForeignKeys0.CONSTRAINT_7AF;
    public static final ForeignKey<WalletRecord, AccountRecord> CONSTRAINT_982 = ForeignKeys0.CONSTRAINT_982;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<AccountRecord, Long> IDENTITY_ACCOUNT = Internal.createIdentity(Account.ACCOUNT, Account.ACCOUNT.ID);
        public static Identity<TransferRecord, Long> IDENTITY_TRANSFER = Internal.createIdentity(Transfer.TRANSFER, Transfer.TRANSFER.ID);
        public static Identity<WalletRecord, Long> IDENTITY_WALLET = Internal.createIdentity(Wallet.WALLET, Wallet.WALLET.ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<AccountRecord> CONSTRAINT_E = Internal.createUniqueKey(Account.ACCOUNT, "CONSTRAINT_E", Account.ACCOUNT.ID);
        public static final UniqueKey<AccountRecord> CONSTRAINT_E4 = Internal.createUniqueKey(Account.ACCOUNT, "CONSTRAINT_E4", Account.ACCOUNT.NAME);
        public static final UniqueKey<TransferRecord> CONSTRAINT_7 = Internal.createUniqueKey(Transfer.TRANSFER, "CONSTRAINT_7", Transfer.TRANSFER.ID);
        public static final UniqueKey<WalletRecord> CONSTRAINT_9 = Internal.createUniqueKey(Wallet.WALLET, "CONSTRAINT_9", Wallet.WALLET.ID);
        public static final UniqueKey<WalletRecord> CONSTRAINT_98 = Internal.createUniqueKey(Wallet.WALLET, "CONSTRAINT_98", Wallet.WALLET.ADDRESS);
        public static final UniqueKey<WalletRecord> CONSTRAINT_982D = Internal.createUniqueKey(Wallet.WALLET, "CONSTRAINT_982D", Wallet.WALLET.ADDRESS, Wallet.WALLET.CURRENCY);
    }

    private static class ForeignKeys0 {
        public static final ForeignKey<TransferRecord, WalletRecord> CONSTRAINT_7A = Internal.createForeignKey(com.hometask.moneytransfer.model.db.Keys.CONSTRAINT_9, Transfer.TRANSFER, "CONSTRAINT_7A", Transfer.TRANSFER.SENDER_ID);
        public static final ForeignKey<TransferRecord, WalletRecord> CONSTRAINT_7AF = Internal.createForeignKey(com.hometask.moneytransfer.model.db.Keys.CONSTRAINT_9, Transfer.TRANSFER, "CONSTRAINT_7AF", Transfer.TRANSFER.RECIPIENT_ID);
        public static final ForeignKey<WalletRecord, AccountRecord> CONSTRAINT_982 = Internal.createForeignKey(com.hometask.moneytransfer.model.db.Keys.CONSTRAINT_E, Wallet.WALLET, "CONSTRAINT_982", Wallet.WALLET.ACCOUNT_ID);
    }
}
