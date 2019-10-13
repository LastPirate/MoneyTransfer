package com.hometask.moneytransfer;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;
import com.hometask.moneytransfer.controller.TransferSystemController;
import com.hometask.moneytransfer.service.TransferSystemService;
import com.hometask.moneytransfer.service.TransferSystemServiceImpl;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.DriverManager;

public class Application extends AbstractModule {

    private static final String USER_NAME = "sa";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:h2:file:C:\\Users\\First\\Desktop\\money_transfer";

    public static void main(String[] args) {
        try {
            Injector injector = Guice.createInjector(new Application());
            injector.getInstance(TransferSystemController.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void configure() {
        try {
            DSLContext dslContext = DSL.using(DriverManager.getConnection(URL, USER_NAME, PASSWORD), SQLDialect.H2);

            bind(Configuration.class)
                    .annotatedWith(Names.named("DataBaseConfiguration"))
                    .toInstance(dslContext.configuration());
        } catch (Exception e) {
            e.printStackTrace();
        }

        bind(TransferSystemService.class).to(TransferSystemServiceImpl.class);
    }
}
