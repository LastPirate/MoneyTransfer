package service

import com.google.inject.Inject
import com.google.inject.name.Named
import com.hometask.moneytransfer.Application
import com.hometask.moneytransfer.exception.AccountAlreadyExistException
import com.hometask.moneytransfer.exception.AccountNotFoundException
import com.hometask.moneytransfer.exception.NotEnoughBalanceException
import com.hometask.moneytransfer.service.AccountService
import org.jooq.Configuration
import spock.guice.UseModules
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
@UseModules(Application)
class AccountServiceTest extends Specification {

    @Shared
    @Inject
    @Named("DataBaseConfiguration")
    Configuration configuration

    @Inject
    @Shared
    AccountService accountService

    def final FIRST_NAME = "sender"

    def firstAccount
    def secondAccount

    def setup() {
        firstAccount = accountService.createAccount(FIRST_NAME)
    }

    def cleanup() {
        accountService.deleteAccount(firstAccount.id)
        if (secondAccount != null) accountService.deleteAccount(secondAccount.id)
    }

    def cleanupSpec() {
        configuration.connectionProvider().acquire().close()
    }

    def "create new account"() {
        expect:
        firstAccount != null
        firstAccount.name == FIRST_NAME
    }

    def "create exist account"() {
        when:
        accountService.createAccount(FIRST_NAME)

        then:
        thrown(AccountAlreadyExistException)
    }

    def "get exist account"() {
        when:
        def gotAccount = accountService.getAccount(FIRST_NAME)

        then:
        gotAccount != null
        firstAccount.id == gotAccount.id && firstAccount.name == gotAccount.name
    }

    def "get uncreated account"() {
        when:
        accountService.deleteAccount(firstAccount.id)
        accountService.getAccount(FIRST_NAME)

        then:
        thrown(AccountNotFoundException)
    }

    def "delete account"() {
        when:
        accountService.deleteAccount(firstAccount.id)
        accountService.getAccount(FIRST_NAME)

        then:
        thrown(AccountNotFoundException)
    }

    def "make refill transfer"() {
        when:
        accountService.refillAccount(firstAccount.id, new BigDecimal(10))

        then:
        accountService.getAccount(FIRST_NAME).balance == 10
    }

    def "make withdraw transfer"() {
        expect:
        accountService.refillAccount(firstAccount.id, new BigDecimal(10))
        accountService.getAccount(FIRST_NAME).balance == 10

        accountService.withdrawFromAccount(firstAccount.id, new BigDecimal(10))
        accountService.getAccount(FIRST_NAME).balance == 0
    }

    def "make transfer with doesn't exist sender"() {
        when:
        accountService.transferBetweenAccounts(999, firstAccount.id, new BigDecimal(10))

        then:
        thrown(AccountNotFoundException)
    }

    def "make transfer with doesn't exist recipient"() {
        when:
        accountService.refillAccount(firstAccount.id, new BigDecimal(10))
        accountService.transferBetweenAccounts(firstAccount.id, 999, new BigDecimal(10))

        then:
        thrown(AccountNotFoundException)
    }

    def "make transfer with not enough balance"() {
        when:
        secondAccount = accountService.createAccount("second")
        accountService.transferBetweenAccounts(firstAccount.id, secondAccount.id, new BigDecimal(10))

        then:
        thrown(NotEnoughBalanceException)
    }

    def "make customer transfer"() {
        when:
        accountService.refillAccount(firstAccount.id, new BigDecimal(10))
        secondAccount = accountService.createAccount("second")
        accountService.transferBetweenAccounts(firstAccount.id, secondAccount.id, new BigDecimal(10))

        then:
        accountService.getAccount(FIRST_NAME).balance == 0
        accountService.getAccount("second").balance == 10
    }
}
