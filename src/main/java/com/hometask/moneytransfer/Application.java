package com.hometask.moneytransfer;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;
import com.hometask.moneytransfer.controller.AccountController;
import com.hometask.moneytransfer.exception.MoneyTransferException;
import com.hometask.moneytransfer.model.db.tables.Account;
import com.hometask.moneytransfer.service.*;
import com.hometask.moneytransfer.service.impl.AccountServiceImpl;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

import java.sql.DriverManager;

import static org.jooq.impl.DSL.constraint;
import static spark.Spark.afterAfter;
import static spark.Spark.exception;

public class Application extends AbstractModule {

    private static final String USER_NAME = "sa";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:h2:mem:money_transfer";

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new Application());
        injector.getInstance(AccountController.class);

        exception(MoneyTransferException.class, (exception, request, response) -> {
            response.status(exception.getCode());
            response.body(exception.getMessage());
        });

        afterAfter((request, response) -> {
            response.type("application/json");
        });
    }

    @Override
    protected void configure() {
        try {
            DSLContext dslContext = DSL.using(DriverManager.getConnection(URL, USER_NAME, PASSWORD), SQLDialect.H2);

            dslContext.createTable(Account.ACCOUNT)
                    .column(Account.ACCOUNT.ID, SQLDataType.BIGINT.nullable(false).identity(true))
                    .column(Account.ACCOUNT.NAME, SQLDataType.VARCHAR(20).nullable(false))
                    .column(Account.ACCOUNT.BALANCE, SQLDataType.DECIMAL(20, 2).nullable(false).defaultValue(DSL.field("0", SQLDataType.DECIMAL)))
                    .constraints(
                            constraint().primaryKey(Account.ACCOUNT.ID),
                            constraint().unique(Account.ACCOUNT.NAME)
                    ).execute();

            dslContext.insertInto(Account.ACCOUNT).columns(Account.ACCOUNT.NAME).values("MONEY_SYSTEM").execute();

            bind(Configuration.class)
                    .annotatedWith(Names.named("DataBaseConfiguration"))
                    .toInstance(dslContext.configuration());
        } catch (Exception e) {
            e.printStackTrace();
        }

        bind(AccountService.class).to(AccountServiceImpl.class);
    }
}
