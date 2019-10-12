package com.hometask.moneytransfer;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.hometask.moneytransfer.controller.TransferController;
import com.hometask.moneytransfer.service.TransferService;
import com.hometask.moneytransfer.service.TransferServiceImpl;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.codegen.GenerationTool;
import org.jooq.impl.DSL;

import java.sql.DriverManager;

public class Application extends AbstractModule {

    public static void main(String[] args) {
        String userName = "sa";
        String password = "";
        String url = "jdbc:h2:file:C:\\Users\\First\\Desktop\\money_transfer";

        try (DSLContext dataBase = DSL.using(DriverManager.getConnection(url, userName, password), SQLDialect.H2)) {

            Injector injector = Guice.createInjector(new Application());
            injector.getInstance(TransferController.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void configure() {
        bind(TransferService.class).to(TransferServiceImpl.class);
    }
}
