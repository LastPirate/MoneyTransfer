package com.hometask.moneytransfer.model;

import com.hometask.moneytransfer.model.db.tables.daos.TransferDao;
import com.hometask.moneytransfer.model.db.tables.pojos.Transfer;
import org.jooq.Configuration;
import org.jooq.impl.DSL;

import java.util.List;

public class TransferCustomDao extends TransferDao {

    public TransferCustomDao(Configuration configuration) {
        super(configuration);
    }

    public List<Transfer> fetchByWalletId(Long walletId) {
        return DSL.using(this.configuration()).selectFrom(this.getTable()).where(com.hometask.moneytransfer.model.db.tables.Transfer.TRANSFER.SENDER_ID.in(walletId)).or(com.hometask.moneytransfer.model.db.tables.Transfer.TRANSFER.RECIPIENT_ID.in(walletId)).fetch(this.mapper());
    }
}
