package com.hometask.moneytransfer;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;
import com.hometask.moneytransfer.controller.TransferController;
import com.hometask.moneytransfer.service.TransferService;
import com.hometask.moneytransfer.service.TransferServiceImpl;
import org.jooq.Configuration;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;

public class Application extends AbstractModule {

    private static final String USER_NAME = "sa";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:h2:file:C:\\Users\\First\\Desktop\\money_transfer";

    public static void main(String[] args) {
        try {
            Injector injector = Guice.createInjector(new Application());
            injector.getInstance(TransferController.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void configure() {
        bind(TransferService.class).to(TransferServiceImpl.class);
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
            bind(Configuration.class).annotatedWith(Names.named("DataBaseConfiguration")).toInstance(DSL.using(connection, SQLDialect.H2).configuration());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
