package com.hometask.moneytransfer.model;

import com.hometask.moneytransfer.model.db.tables.daos.WalletDao;
import com.hometask.moneytransfer.model.db.tables.pojos.Wallet;
import org.jooq.Configuration;
import org.jooq.impl.DSL;

public class WalletCustomDao extends WalletDao {

    public WalletCustomDao(Configuration configuration) {
        super(configuration);
    }

    public Wallet fetchWalletByAccountIdAndCurrency(Long accountId, String currency) {
        return DSL.using(this.configuration()).selectFrom(this.getTable())
                .where(com.hometask.moneytransfer.model.db.tables.Wallet.WALLET.ID.equal(accountId))
                .and(com.hometask.moneytransfer.model.db.tables.Wallet.WALLET.CURRENCY.equal(currency))
                .fetchOne(this.mapper());
    }

}
