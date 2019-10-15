package controller

import com.hometask.moneytransfer.controller.WalletController
import com.hometask.moneytransfer.service.WalletService
import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Specification

class WalletControllerTest extends Specification {

    @Shared
    def client = new RESTClient("http://0.0.0.0:4567")

    @Shared
    WalletController walletController = new WalletController()

    def "Create wallet endpoint"() {
        given:
        def walletServiceMock = Mock(WalletService)
        walletController.walletService = walletServiceMock

        when:
        def response = client.post( uri : client.uri.toString() + '/createWallet?accountId=0&currency=test')

        then:
        1 * walletServiceMock.createWallet(0,"test")
        response.status == 200
    }

    def "Get wallet endpoint"() {
        given:
        def walletServiceMock = Mock(WalletService)
        walletController.walletService = walletServiceMock

        when:
        def response = client.get( uri : client.uri.toString() + '/getWallet?accountId=0&currency=test')

        then:
        1 * walletServiceMock.getWallet(0,"test")
        response.status == 200
    }

    def "Get account wallets endpoint"() {
        given:
        def walletServiceMock = Mock(WalletService)
        walletController.walletService = walletServiceMock

        when:
        def response = client.get( uri : client.uri.toString() + '/getWalletsByAccountId?accountId=0')

        then:
        1 * walletServiceMock.getWalletsByAccountId(0)
        response.status == 200
    }

    def "Delete wallet endpoint"() {
        given:
        def walletServiceMock = Mock(WalletService)
        walletController.walletService = walletServiceMock

        when:
        def response = client.post( uri : client.uri.toString() + '/deleteWallet?id=0')

        then:
        1 * walletServiceMock.deleteWallet(0)
        response.status == 200
    }

}
