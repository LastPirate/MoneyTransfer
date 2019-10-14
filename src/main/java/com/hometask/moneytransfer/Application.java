package com.hometask.moneytransfer;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;
import com.hometask.moneytransfer.controller.AccountController;
import com.hometask.moneytransfer.controller.TransferController;
import com.hometask.moneytransfer.controller.WalletController;
import com.hometask.moneytransfer.exception.MoneyTransferException;
import com.hometask.moneytransfer.model.db.tables.Account;
import com.hometask.moneytransfer.model.db.tables.Transfer;
import com.hometask.moneytransfer.model.db.tables.Wallet;
import com.hometask.moneytransfer.service.*;
import com.hometask.moneytransfer.service.impl.AccountServiceImpl;
import com.hometask.moneytransfer.service.impl.TransferServiceImpl;
import com.hometask.moneytransfer.service.impl.WalletServiceImpl;
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
        injector.getInstance(WalletController.class);
        injector.getInstance(TransferController.class);

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
                    .constraints(
                            constraint().primaryKey(Account.ACCOUNT.ID),
                            constraint().unique(Account.ACCOUNT.NAME)
                    ).execute();

            dslContext.insertInto(Account.ACCOUNT).columns(Account.ACCOUNT.NAME).values("MONEY_SYSTEM").execute();

            dslContext.createTable(Wallet.WALLET)
                    .column(Wallet.WALLET.ID, SQLDataType.BIGINT.nullable(false).identity(true))
                    .column(Wallet.WALLET.ADDRESS, SQLDataType.VARCHAR(64).nullable(false))
                    .column(Wallet.WALLET.CURRENCY, SQLDataType.VARCHAR(3).nullable(false))
                    .column(Wallet.WALLET.BALANCE, SQLDataType.DECIMAL(20, 2).nullable(false).defaultValue(DSL.field("0", SQLDataType.DECIMAL)))
                    .column(Wallet.WALLET.ACCOUNT_ID, SQLDataType.BIGINT.nullable(false))
                    .constraints(
                            constraint().primaryKey(Wallet.WALLET.ID),
                            constraint().unique(Wallet.WALLET.ADDRESS),
                            constraint().foreignKey(Wallet.WALLET.ACCOUNT_ID).references(Account.ACCOUNT, Account.ACCOUNT.ID).onDeleteCascade(),
                            constraint().unique(Wallet.WALLET.ACCOUNT_ID, Wallet.WALLET.CURRENCY)
                    ).execute();

            dslContext.insertInto(Wallet.WALLET).columns(Wallet.WALLET.ADDRESS, Wallet.WALLET.CURRENCY, Wallet.WALLET.ACCOUNT_ID).values("MAIN", "ANY", 1L).execute();

            dslContext.createTable(Transfer.TRANSFER)
                    .column(Transfer.TRANSFER.ID, SQLDataType.BIGINT.nullable(false).identity(true))
                    .column(Transfer.TRANSFER.QUANTITY, SQLDataType.DECIMAL(20, 2).nullable(false))
                    .column(Transfer.TRANSFER.MOMENT, SQLDataType.LOCALDATETIME.nullable(false).defaultValue(DSL.field("CURRENT_TIMESTAMP", SQLDataType.LOCALDATETIME)))
                    .column(Transfer.TRANSFER.EXCHANGE_RATE, SQLDataType.DOUBLE.nullable(false).defaultValue(DSL.field("1", SQLDataType.DOUBLE)))
                    .column(Transfer.TRANSFER.DESCRIPTION, SQLDataType.VARCHAR(200).nullable(true))
                    .column(Transfer.TRANSFER.SENDER_ID, SQLDataType.BIGINT.nullable(false))
                    .column(Transfer.TRANSFER.RECIPIENT_ID, SQLDataType.BIGINT.nullable(false))
                    .constraints(
                            constraint().primaryKey(Transfer.TRANSFER.ID),
                            constraint().foreignKey(Transfer.TRANSFER.SENDER_ID).references(Wallet.WALLET, Wallet.WALLET.ID).onDeleteCascade(),
                            constraint().foreignKey(Transfer.TRANSFER.RECIPIENT_ID).references(Wallet.WALLET, Wallet.WALLET.ID).onDeleteCascade()
                    ).execute();

            bind(Configuration.class)
                    .annotatedWith(Names.named("DataBaseConfiguration"))
                    .toInstance(dslContext.configuration());
        } catch (Exception e) {
            e.printStackTrace();
        }

        bind(AccountService.class).to(AccountServiceImpl.class);
        bind(WalletService.class).to(WalletServiceImpl.class);
        bind(TransferService.class).to(TransferServiceImpl.class);
    }
}
