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
                .columns(
                        com.hometask.moneytransfer.model.db.tables.Transfer.TRANSFER.QUANTITY,
                        com.hometask.moneytransfer.model.db.tables.Transfer.TRANSFER.EXCHANGE_RATE,
                        com.hometask.moneytransfer.model.db.tables.Transfer.TRANSFER.DESCRIPTION,
                        com.hometask.moneytransfer.model.db.tables.Transfer.TRANSFER.SENDER_ID,
                        com.hometask.moneytransfer.model.db.tables.Transfer.TRANSFER.RECIPIENT_ID
                )
                .values(transfer.getQuantity(), transfer.getExchangeRate(), transfer.getDescription(), transfer.getSenderId(), transfer.getRecipientId())
                .returning(this.getTable().asterisk())
                .fetchOne());
    }
}
