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
        return DSL.using(this.configuration()).selectFrom(this.getTable())
                .where(com.hometask.moneytransfer.model.db.tables.Transfer.TRANSFER.SENDER_ID.equal(walletId))
                .or(com.hometask.moneytransfer.model.db.tables.Transfer.TRANSFER.RECIPIENT_ID.equal(walletId))
                .fetch(this.mapper());
    }

    public Transfer insertWithResult(Transfer transfer) {
        return this.mapper().map(DSL.using(this.configuration()).insertInto(this.getTable())
                .values(transfer)
                .returning(this.getTable().asterisk())
                .fetchOne());
    }
}
