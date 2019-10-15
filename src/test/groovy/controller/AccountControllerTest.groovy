package controller

import com.hometask.moneytransfer.controller.AccountController
import com.hometask.moneytransfer.service.AccountService
import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Specification


class AccountControllerTest extends Specification {

    @Shared
    def client = new RESTClient( "http://0.0.0.0:4567")

    @Shared
    AccountController accountController = new AccountController()

    def "Create account endpoint"() {
        given:
        def accountServiceMock = Mock(AccountService)
        accountController.accountService = accountServiceMock

        when:
        def response = client.post( uri : client.uri.toString() + '/createAccount?name=test')

        then:
        1 * accountServiceMock.createAccount("test")
        response.status == 200
    }

    def "Get account endpoint"() {
        given:
        def accountServiceMock = Mock(AccountService)
        accountController.accountService = accountServiceMock

        when:
        def response = client.get( uri : client.uri.toString() + '/getAccount?name=test')

        then:
        1 * accountServiceMock.getAccount("test")
        response.status == 200
    }

    def "Delete account endpoint"() {
        given:
        def accountServiceMock = Mock(AccountService)
        accountController.accountService = accountServiceMock

        when:
        def response = client.post( uri : client.uri.toString() + '/deleteAccount?id=0')

        then:
        1 * accountServiceMock.deleteAccount(0)
        response.status == 200
    }

}
