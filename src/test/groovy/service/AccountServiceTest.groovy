package service

import com.google.inject.Inject
import com.google.inject.name.Named
import com.hometask.moneytransfer.Application
import com.hometask.moneytransfer.exception.AccountAlreadyExistException
import com.hometask.moneytransfer.exception.AccountNotFoundException
import com.hometask.moneytransfer.service.AccountService
import org.jooq.Configuration
import spock.guice.UseModules
import spock.lang.Shared
import spock.lang.Specification

@UseModules(Application)
class AccountServiceTest extends Specification {

    @Inject
    @Named("DataBaseConfiguration")
    Configuration configuration

    @Inject
    @Shared
    AccountService accountService

    def final ACCOUNT_NAME = "account"

    def account

    def setup() {
        account = accountService.createAccount(ACCOUNT_NAME)
    }

    def cleanup() {
        accountService.deleteAccount(account.id)
    }

    def "create new account"() {
        expect:
        account != null
        account.name == ACCOUNT_NAME
    }

    def "create exist account"() {
        when:
        accountService.createAccount(ACCOUNT_NAME)

        then:
        thrown(AccountAlreadyExistException)
    }

    def "get exist account"() {
        when:
        def gotAccount = accountService.getAccount(ACCOUNT_NAME)

        then:
        gotAccount != null
        account.id == gotAccount.id && account.name == gotAccount.name
    }

    def "get uncreated account"() {
        when:
        accountService.deleteAccount(account.id)
        accountService.getAccount(ACCOUNT_NAME)

        then:
        thrown(AccountNotFoundException)
    }

    def "delete account"() {
        when:
        accountService.deleteAccount(account.id)
        accountService.getAccount(ACCOUNT_NAME)

        then:
        thrown(AccountNotFoundException)
    }
}
