package service

import com.hometask.moneytransfer.exception.AccountAlreadyExistException
import com.hometask.moneytransfer.exception.AccountNotFoundException
import com.hometask.moneytransfer.exception.NotEnoughBalanceException
import com.hometask.moneytransfer.model.AccountCustomDao
import com.hometask.moneytransfer.model.db.tables.pojos.Account
import com.hometask.moneytransfer.service.AccountService
import com.hometask.moneytransfer.service.impl.AccountServiceImpl
import org.jooq.exception.DataAccessException
import spock.lang.Shared
import spock.lang.Specification

class AccountServiceUnitTest extends Specification {

    def final ACCOUNT_NAME = "unit"

    @Shared
    AccountService accountService = new AccountServiceImpl()

    def "create new account"() {
        given:
        accountService.accountCustomDao = Stub(AccountCustomDao) {
            insertWithResult(ACCOUNT_NAME) >> {
                Account account = new Account()
                account.setId(1)
                account.setName(ACCOUNT_NAME)
                account.setBalance(0)
                return account
            }
        }

        when:
        def result = accountService.createAccount(ACCOUNT_NAME)

        then:
        result != null
        result.id == 1
        result.name == ACCOUNT_NAME
        result.balance == 0
    }

    def "create exist account"() {
        given:
        accountService.accountCustomDao = Stub(AccountCustomDao) {
            insertWithResult(ACCOUNT_NAME) >> {
                throw new DataAccessException("account exist")
            }
        }

        when:
        accountService.createAccount(ACCOUNT_NAME)

        then:
        thrown(AccountAlreadyExistException)
    }

    def "get exist account"() {
        given:
        accountService.accountCustomDao = Stub(AccountCustomDao) {
            fetchOneByName(ACCOUNT_NAME) >> {
                Account account = new Account()
                account.setId(1)
                account.setName(ACCOUNT_NAME)
                account.setBalance(0)
                return account
            }
        }

        when:
        def result = accountService.getAccount(ACCOUNT_NAME)

        then:
        result != null
        result.id == 1
        result.name == ACCOUNT_NAME
        result.balance == 0
    }

    def "get uncreated account"() {
        given:
        accountService.accountCustomDao = Stub(AccountCustomDao) {
            fetchOneByName(ACCOUNT_NAME) >> null
        }

        when:
        accountService.getAccount(ACCOUNT_NAME)

        then:
        thrown(AccountNotFoundException)
    }

    def "delete account"() {
        given:
        Account account = new Account()
        accountService.accountCustomDao = Stub(AccountCustomDao) {
            deleteById(1L) >> {
                account = null
            }
        }

        when:
        accountService.deleteAccount(1L)

        then:
        account == null
    }

    def "make refill transfer"() {
        given:
        Account main = new Account()
        main.setId(1)
        main.setName("main")
        main.setBalance(0)

        Account customer = new Account()
        customer.setId(2)
        customer.setName("customer")
        customer.setBalance(0)

        accountService.accountCustomDao = Stub(AccountCustomDao) {
            findById(1L) >> {
                return main
            }

            findById(2L) >> {
                return customer
            }
        }

        when:
        def status = accountService.refillAccount(2, 10)

        then:
        status
        main.balance == 0
        customer.balance == 10
    }

    def "make withdraw transfer"() {
        given:
        Account main = new Account()
        main.setId(1)
        main.setName("main")
        main.setBalance(0)

        Account customer = new Account()
        customer.setId(2)
        customer.setName("customer")
        customer.setBalance(10)

        accountService.accountCustomDao = Stub(AccountCustomDao) {
            findById(1L) >> {
                return main
            }

            findById(2L) >> {
                return customer
            }
        }

        when:
        def status = accountService.withdrawFromAccount(2, 10)

        then:
        status
        main.balance == 0
        customer.balance == 0
    }

    def "make transfer with doesn't exist sender"() {
        given:
        accountService.accountCustomDao = Stub(AccountCustomDao) {
            findById(2L) >> {
                return null
            }
        }

        when:
        accountService.transferBetweenAccounts(2, 3, 10)

        then:
        thrown(AccountNotFoundException)
    }

    def "make transfer with doesn't exist recipient"() {
        given:
        accountService.accountCustomDao = Stub(AccountCustomDao) {
            findById(2L) >> {
                Account sender = new Account()
                sender.setId(2)
                sender.setName("sender")
                sender.setBalance(10)
                return sender
            }

            findById(3L) >> {
                return null
            }
        }

        when:
        accountService.transferBetweenAccounts(2, 3, 10)

        then:
        thrown(AccountNotFoundException)
    }

    def "make transfer with not enough balance"() {
        given:
        accountService.accountCustomDao = Stub(AccountCustomDao) {
            findById(2L) >> {
                Account sender = new Account()
                sender.setId(2)
                sender.setName("sender")
                sender.setBalance(0)
                return sender
            }
        }

        when:
        accountService.transferBetweenAccounts(2, 3, 10)

        then:
        thrown(NotEnoughBalanceException)
    }

    def "make customer transfer"() {
        given:
        Account sender = new Account()
        sender.setId(2)
        sender.setName("sender")
        sender.setBalance(10)

        Account recipient = new Account()
        recipient.setId(3)
        recipient.setName("recipient")
        recipient.setBalance(0)

        accountService.accountCustomDao = Stub(AccountCustomDao) {
            findById(2L) >> {
                return sender
            }

            findById(3L) >> {
                return recipient
            }
        }

        when:
        def status = accountService.transferBetweenAccounts(2, 3, 10)

        then:
        status
        sender.balance == 0
        recipient.balance == 10
    }

    def "make customer unsuccessful transfer"() {
        given:
        Account sender = new Account()
        sender.setId(2)
        sender.setName("sender")
        sender.setBalance(10)

        Account recipient = new Account()
        recipient.setId(3)
        recipient.setName("recipient")
        recipient.setBalance(0)

        accountService.accountCustomDao = Stub(AccountCustomDao) {
            findById(2L) >> {
                return sender
            }

            findById(3L) >> {
                return recipient
            }

            update(sender, recipient) >> {
                throw new Exception()
            }
        }

        when:
        def status = accountService.transferBetweenAccounts(2, 3, 10)

        then:
        status == false
    }

}
