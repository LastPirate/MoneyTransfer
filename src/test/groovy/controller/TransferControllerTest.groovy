package controller

import com.hometask.moneytransfer.controller.TransferController
import com.hometask.moneytransfer.service.TransferService
import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Specification

class TransferControllerTest extends Specification {

    @Shared
    def client = new RESTClient("http://0.0.0.0:4567")

    @Shared
    TransferController transferController = new TransferController()

    def "Refill wallet transfer endpoint"() {
        given:
        def transferServiceMock = Mock(TransferService)
        transferController.transferService = transferServiceMock

        when:
        def response = client.post(uri: client.uri.toString() + '/refill?walletId=0&quantity=0')

        then:
        1 * transferServiceMock.refillTransfer(0, 0.0)
        response.status == 200
    }

    def "Payout wallet transfer endpoint"() {
        given:
        def transferServiceMock = Mock(TransferService)
        transferController.transferService = transferServiceMock

        when:
        def response = client.post(uri: client.uri.toString() + '/payout?walletId=0&quantity=0')

        then:
        1 * transferServiceMock.payoutTransfer(0, 0.0)
        response.status == 200
    }

    def "Customer transfer endpoint"() {
        given:
        def transferServiceMock = Mock(TransferService)
        transferController.transferService = transferServiceMock

        when:
        def response = client.post(uri: client.uri.toString() + '/transfer?senderId=0&recipientId=1&quantity=2&description=test&exchangeRate=1')

        then:
        1 * transferServiceMock.customerTransfer(0, 1, 2, "test", 1.0)
        response.status == 200
    }

    def "Get all transfers endpoint"() {
        given:
        def transferServiceMock = Mock(TransferService)
        transferController.transferService = transferServiceMock

        when:
        def response = client.get(uri: client.uri.toString() + '/getTransferBook')

        then:
        1 * transferServiceMock.getTransferBook()
        response.status == 200
    }

    def "Get wallet transfers endpoint"() {
        given:
        def transferServiceMock = Mock(TransferService)
        transferController.transferService = transferServiceMock

        when:
        def response = client.get(uri: client.uri.toString() + '/getWalletTransferBook?walletId=0')

        then:
        1 * transferServiceMock.getWalletTransferBook(0)
        response.status == 200
    }

}