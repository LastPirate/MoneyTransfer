package service

import com.google.inject.Inject
import com.google.inject.name.Named
import com.hometask.moneytransfer.Application
import com.hometask.moneytransfer.exception.AccountNotFoundException
import com.hometask.moneytransfer.exception.CurrencyConversionException
import com.hometask.moneytransfer.exception.NotEnoughBalanceException
import com.hometask.moneytransfer.exception.TransferDescriptionTooLongException
import com.hometask.moneytransfer.service.AccountService
import com.hometask.moneytransfer.service.TransferService
import com.hometask.moneytransfer.service.WalletService
import org.jooq.Configuration
import spock.guice.UseModules
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
@UseModules(Application)
class TransferServiceTest extends Specification {

    @Shared
    @Inject
    @Named("DataBaseConfiguration")
    Configuration configuration

    @Inject
    @Shared
    TransferService transferService

    @Inject
    @Shared
    WalletService walletService

    @Inject
    @Shared
    AccountService accountService

    def final USD_CURRENCY = "USD"
    def final EUR_CURRENCY = "EUR"
    def final TOO_LONG_DESCRIPTION =
            "This line shows what will happen if you had not read enough books" +
                    " and don’t know how to briefly express your thoughts" +
                    " and you are writing very and VERY many different, " +
                    "but very useless things in the description of the money transfer. " +
                    "Or if you just forgot about existence of SMS."

    def senderAccount
    def recipientAccount

    def usdSenderWallet
    def usdRecipientWallet
    def eurRecipientWallet

    def setup() {
        senderAccount = accountService.createAccount("sender")
        recipientAccount = accountService.createAccount("recipient")

        usdSenderWallet = walletService.createWallet(senderAccount.id, USD_CURRENCY)
        usdRecipientWallet = walletService.createWallet(recipientAccount.id, USD_CURRENCY)
        eurRecipientWallet = walletService.createWallet(recipientAccount.id, EUR_CURRENCY)
    }

    def cleanup() {
        accountService.deleteAccount(senderAccount.id)
        accountService.deleteAccount(recipientAccount.id)
    }

    def cleanupSpec() {
        configuration.connectionProvider().acquire().close()
    }

    def "make refill transfer"() {
        when:
        transferService.refillTransfer(usdSenderWallet.id, new BigDecimal(10))

        then:
        walletService.getWallet(senderAccount.id, USD_CURRENCY).balance == 10
        transferService.getWalletTransferBook(usdSenderWallet.id).size() == 1
    }

    def "make payout transfer"() {
        when:
        transferService.refillTransfer(usdSenderWallet.id, new BigDecimal(10))
        transferService.payoutTransfer(usdSenderWallet.id, new BigDecimal(10))

        then:
        walletService.getWallet(senderAccount.id, USD_CURRENCY).balance == 0
        transferService.getWalletTransferBook(usdSenderWallet.id).size() == 2
    }

    def "make transfer with too long exception"() {
        when:
        transferService.customerTransfer(
                usdSenderWallet.id,
                usdRecipientWallet.id,
                new BigDecimal(10),
                TOO_LONG_DESCRIPTION,
                null
        )

        then:
        thrown(TransferDescriptionTooLongException)
    }

    def "make transfer with doesn't exist sender"() {
        when:
        transferService.customerTransfer(
                999,
                usdRecipientWallet.id,
                new BigDecimal(10),
                "",
                null
        )

        then:
        thrown(AccountNotFoundException)
    }

    def "make transfer with doesn't exist recipient"() {
        when:
        transferService.refillTransfer(usdSenderWallet.id, new BigDecimal(10))
        transferService.customerTransfer(
                usdSenderWallet.id,
                999,
                new BigDecimal(10),
                "",
                null
        )

        then:
        thrown(AccountNotFoundException)
    }

    def "make transfer with not enough balance"() {
        when:
        transferService.customerTransfer(
                usdSenderWallet.id,
                usdRecipientWallet.id,
                new BigDecimal(10),
                "",
                null
        )

        then:
        thrown(NotEnoughBalanceException)
    }

    def "make transfer with not accepted currency conversion"() {
        when:
        transferService.refillTransfer(usdSenderWallet.id, new BigDecimal(10))
        transferService.customerTransfer(
                usdSenderWallet.id,
                eurRecipientWallet.id,
                new BigDecimal(10),
                "",
                null
        )

        then:
        thrown(CurrencyConversionException)
    }

    def "make customer transfer"() {
        when:
        transferService.refillTransfer(usdSenderWallet.id, new BigDecimal(10))
        transferService.customerTransfer(
                usdSenderWallet.id,
                usdRecipientWallet.id,
                new BigDecimal(10),
                "",
                null
        )

        then:
        walletService.getWallet(senderAccount.id, USD_CURRENCY).balance == 0
        walletService.getWallet(recipientAccount.id, USD_CURRENCY).balance == 10
        transferService.getWalletTransferBook(usdSenderWallet.id).size() == 2
    }

    def "make customer transfer with currency conversion"() {
        when:
        def quantity = 10
        transferService.refillTransfer(usdSenderWallet.id, new BigDecimal(quantity))
        transferService.customerTransfer(
                usdSenderWallet.id,
                eurRecipientWallet.id,
                new BigDecimal(quantity),
                "",
                1.15
        )

        then:
        walletService.getWallet(senderAccount.id, USD_CURRENCY).balance == 0
        walletService.getWallet(recipientAccount.id, EUR_CURRENCY).balance == quantity * 1.15
    }

    def "get general transfer book"() {
        when:
        transferService.refillTransfer(usdSenderWallet.id, new BigDecimal(10))
        transferService.customerTransfer(
                usdSenderWallet.id,
                usdRecipientWallet.id,
                new BigDecimal(10),
                "",
                null
        )
        transferService.refillTransfer(usdRecipientWallet.id, new BigDecimal(10))

        then:
        transferService.getTransferBook().size() == 3
    }

    def "get wallet's transfer book"() {
        when:
        transferService.refillTransfer(usdSenderWallet.id, new BigDecimal(10))
        transferService.customerTransfer(
                usdSenderWallet.id,
                usdRecipientWallet.id,
                new BigDecimal(10),
                "",
                null
        )

        then:
        transferService.getWalletTransferBook(usdSenderWallet.id).size() == 2
    }

}
