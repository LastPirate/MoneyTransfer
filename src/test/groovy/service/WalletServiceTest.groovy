package service

import com.google.inject.Inject
import com.google.inject.name.Named
import com.hometask.moneytransfer.Application
import com.hometask.moneytransfer.exception.AccountNotFoundException
import com.hometask.moneytransfer.exception.CurrencyTickerTooLongException
import com.hometask.moneytransfer.exception.WalletAlreadyExistException
import com.hometask.moneytransfer.exception.WalletNotFoundException
import com.hometask.moneytransfer.model.db.tables.pojos.Wallet
import com.hometask.moneytransfer.service.AccountService
import com.hometask.moneytransfer.service.WalletService
import org.jooq.Configuration
import spock.guice.UseModules
import spock.lang.Shared
import spock.lang.Specification

@UseModules(Application)
class WalletServiceTest extends Specification {

    @Inject
    @Named("DataBaseConfiguration")
    Configuration configuration

    @Inject
    @Shared
    WalletService walletService

    @Inject
    @Shared
    AccountService accountService

    def final ACCOUNT_NAME = "account"
    def final USD_CURRENCY = "USD"
    def final EUR_CURRENCY = "EUR"

    def account
    def wallet

    def setup() {
        account = accountService.createAccount(ACCOUNT_NAME)
        wallet = walletService.createWallet(account.id, USD_CURRENCY)
    }

    def cleanup() {
        accountService.deleteAccount(account.id)
    }

    def "create new wallet"() {
        expect:
        wallet != null
        wallet.accountId == account.id
    }

    def "create used currency wallet"() {
        when:
        walletService.createWallet(account.id, USD_CURRENCY)

        then:
        thrown(WalletAlreadyExistException)
    }

    def "create wallet for uncreated account"() {
        when:
        accountService.deleteAccount(account.id)
        walletService.createWallet(account.id, USD_CURRENCY)

        then:
        thrown(AccountNotFoundException)
    }

    def "create long ticker currency wallet"() {
        when:
        walletService.createWallet(account.id, "LONG")

        then:
        thrown(CurrencyTickerTooLongException)
    }

    def "get exist wallet"() {
        when:
        def existWallet = walletService.getWallet(account.id, USD_CURRENCY)

        then:
        existWallet.id == wallet.id
    }

    def "get wallet for uncreated account"() {
        when:
        accountService.deleteAccount(account.id)
        walletService.getWallet(account.id, EUR_CURRENCY)

        then:
        thrown(WalletNotFoundException)
    }

    def "get wallet for unregistered currency"() {
        when:
        walletService.getWallet(account.id, EUR_CURRENCY)

        then:
        thrown(WalletNotFoundException)
    }

    def "get all wallets for account"() {
        when:
        walletService.createWallet(account.id, EUR_CURRENCY)
        List<Wallet> wallets = walletService.getWalletsByAccountId(account.id)

        then:
        wallets.size() == 2
    }

    def "delete wallet"() {
        when:
        walletService.deleteWallet(wallet.id)

        then:
        List<Wallet> wallets = walletService.getWalletsByAccountId(account.id)
        wallets.size() == 0
    }

}
