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
    AccountService accountServiceImpl

    @Shared
    def account

    def setupSpec() {
        account = accountServiceImpl.createAccount("account")
    }

    def "create new account"() {
        expect:
        account != null
        account.name == "account"
    }

    def "create exist account"() {
        when:
        accountServiceImpl.createAccount("account")

        then:
        thrown(AccountAlreadyExistException)
    }

    def "get exist account"() {
        when:
        def gotAccount = accountServiceImpl.getAccount("account")

        then:
        gotAccount != null
        account.id == gotAccount.id && account.name == gotAccount.name
    }

    def "delete account"() {
        expect:
        accountServiceImpl.deleteAccount(account.id)
    }

    def "get uncreated account"() {
        when:
        accountServiceImpl.getAccount("account")

        then:
        thrown(AccountNotFoundException)
    }
}
