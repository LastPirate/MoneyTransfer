package com.hometask.moneytransfer;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.hometask.moneytransfer.controller.TransferController;
import com.hometask.moneytransfer.service.TransferService;
import com.hometask.moneytransfer.service.TransferServiceImpl;

public class Application extends AbstractModule {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new Application());

        TransferController transferController = injector.getInstance(TransferController.class);
        transferController.initEndpoints();
    }

    @Override
    protected void configure() {
        bind(TransferService.class).to(TransferServiceImpl.class);
    }

}
