/*
 * This file is generated by jOOQ.
 */
package com.hometask.moneytransfer.model.db.tables;


import com.hometask.moneytransfer.model.db.Indexes;
import com.hometask.moneytransfer.model.db.Keys;
import com.hometask.moneytransfer.model.db.Public;
import com.hometask.moneytransfer.model.db.tables.records.TransferRecord;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row6;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class Transfer extends TableImpl<TransferRecord> {

    private static final long serialVersionUID = 1659332662;

    /**
     * The reference instance of <code>PUBLIC.TRANSFER</code>
     */
    public static final Transfer TRANSFER = new Transfer();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TransferRecord> getRecordType() {
        return TransferRecord.class;
    }

    /**
     * The column <code>PUBLIC.TRANSFER.ID</code>.
     */
    public final TableField<TransferRecord, Long> ID = createField(DSL.name("ID"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>PUBLIC.TRANSFER.QUANTITY</code>.
     */
    public final TableField<TransferRecord, BigDecimal> QUANTITY = createField(DSL.name("QUANTITY"), org.jooq.impl.SQLDataType.DECIMAL(20, 2).nullable(false), this, "");

    /**
     * The column <code>PUBLIC.TRANSFER.EXCHANGE_RATE</code>.
     */
    public final TableField<TransferRecord, Byte> EXCHANGE_RATE = createField(DSL.name("EXCHANGE_RATE"), org.jooq.impl.SQLDataType.TINYINT.nullable(false), this, "");

    /**
     * The column <code>PUBLIC.TRANSFER.DESCRIPTION</code>.
     */
    public final TableField<TransferRecord, String> DESCRIPTION = createField(DSL.name("DESCRIPTION"), org.jooq.impl.SQLDataType.VARCHAR(200).nullable(false), this, "");

    /**
     * The column <code>PUBLIC.TRANSFER.SENDER_ID</code>.
     */
    public final TableField<TransferRecord, Long> SENDER_ID = createField(DSL.name("SENDER_ID"), org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>PUBLIC.TRANSFER.RECIPIENT_ID</code>.
     */
    public final TableField<TransferRecord, Long> RECIPIENT_ID = createField(DSL.name("RECIPIENT_ID"), org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * Create a <code>PUBLIC.TRANSFER</code> table reference
     */
    public Transfer() {
        this(DSL.name("TRANSFER"), null);
    }

    /**
     * Create an aliased <code>PUBLIC.TRANSFER</code> table reference
     */
    public Transfer(String alias) {
        this(DSL.name(alias), TRANSFER);
    }

    /**
     * Create an aliased <code>PUBLIC.TRANSFER</code> table reference
     */
    public Transfer(Name alias) {
        this(alias, TRANSFER);
    }

    private Transfer(Name alias, Table<TransferRecord> aliased) {
        this(alias, aliased, null);
    }

    private Transfer(Name alias, Table<TransferRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Transfer(Table<O> child, ForeignKey<O, TransferRecord> key) {
        super(child, key, TRANSFER);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.CONSTRAINT_INDEX_7, Indexes.CONSTRAINT_INDEX_7A, Indexes.PRIMARY_KEY_7);
    }

    @Override
    public Identity<TransferRecord, Long> getIdentity() {
        return Keys.IDENTITY_TRANSFER;
    }

    @Override
    public UniqueKey<TransferRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_7;
    }

    @Override
    public List<UniqueKey<TransferRecord>> getKeys() {
        return Arrays.<UniqueKey<TransferRecord>>asList(Keys.CONSTRAINT_7);
    }

    @Override
    public List<ForeignKey<TransferRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<TransferRecord, ?>>asList(Keys.CONSTRAINT_7A, Keys.CONSTRAINT_7AF);
    }

    public Wallet constraint_7a() {
        return new Wallet(this, Keys.CONSTRAINT_7A);
    }

    public Wallet constraint_7af() {
        return new Wallet(this, Keys.CONSTRAINT_7AF);
    }

    @Override
    public Transfer as(String alias) {
        return new Transfer(DSL.name(alias), this);
    }

    @Override
    public Transfer as(Name alias) {
        return new Transfer(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Transfer rename(String name) {
        return new Transfer(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Transfer rename(Name name) {
        return new Transfer(name, null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row6<Long, BigDecimal, Byte, String, Long, Long> fieldsRow() {
        return (Row6) super.fieldsRow();
    }
}
