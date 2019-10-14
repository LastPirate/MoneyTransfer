package com.hometask.moneytransfer.model;

import com.hometask.moneytransfer.model.db.tables.daos.AccountDao;
import com.hometask.moneytransfer.model.db.tables.pojos.Account;
import org.jooq.Configuration;
import org.jooq.impl.DSL;

public class AccountCustomDao extends AccountDao {

    public AccountCustomDao(Configuration configuration) {
        super(configuration);
    }

    public Account insertWithResult(String name) {
        return this.mapper().map(DSL.using(this.configuration()).insertInto(this.getTable())
                .columns(com.hometask.moneytransfer.model.db.tables.Account.ACCOUNT.NAME)
                .values(name)
                .returning(this.getTable().asterisk())
                .fetchOne());
    }
}
