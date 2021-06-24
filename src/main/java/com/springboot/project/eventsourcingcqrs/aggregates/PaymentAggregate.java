package com.springboot.project.eventsourcingcqrs.aggregates;

import com.springboot.project.eventsourcingcqrs.commands.CreateAccountCommand;
import com.springboot.project.eventsourcingcqrs.commands.CreditMoneyCommand;
import com.springboot.project.eventsourcingcqrs.commands.DebitMoneyCommand;
import com.springboot.project.eventsourcingcqrs.events.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Data
@NoArgsConstructor
public class PaymentAggregate {

    @AggregateIdentifier
    private String transactionId;
    private double accountBalance;
    private String currency;
    private String status;

    @CommandHandler
    public PaymentAggregate(final CreateAccountCommand createAccountCommand) {
        AggregateLifecycle.apply(new AccountCreatedEvent(createAccountCommand.transactionId, createAccountCommand.accountBalance, createAccountCommand.currency));
    }

    @EventSourcingHandler
    protected void on(final AccountCreatedEvent accountCreatedEvent) {
        this.transactionId = accountCreatedEvent.transactionId;
        this.accountBalance = accountCreatedEvent.accountBalance;
        this.currency = accountCreatedEvent.currency;
        this.status = String.valueOf(PaymentStatus.INITIATED);

        AggregateLifecycle.apply(new PaymentSuccessEvent(this.transactionId, PaymentStatus.SUCCESSFUL));
    }

    @EventSourcingHandler
    protected void on(final PaymentSuccessEvent paymentSuccessEvent) {
        this.status = String.valueOf(paymentSuccessEvent.status);
    }

    @CommandHandler
    protected void handle(final CreditMoneyCommand creditMoneyCommand) {
        AggregateLifecycle.apply(new MoneyCreditedEvent(creditMoneyCommand.transactionId, creditMoneyCommand.creditAmount, creditMoneyCommand.currency));
    }

    @EventSourcingHandler
    protected void on(final MoneyCreditedEvent moneyCreditedEvent) {

        if (this.accountBalance < 0 && (this.accountBalance + moneyCreditedEvent.creditAmount) >= 0) {
            AggregateLifecycle.apply(new PaymentSuccessEvent(this.transactionId, PaymentStatus.SUCCESSFUL));
        }

        this.accountBalance += moneyCreditedEvent.creditAmount;
    }

    @CommandHandler
    protected void handle(final DebitMoneyCommand debitMoneyCommand) {
        AggregateLifecycle.apply(new MoneyDebitedEvent(debitMoneyCommand.transactionId, debitMoneyCommand.debitAmount, debitMoneyCommand.currency));
    }

    @EventSourcingHandler
    protected void on(final MoneyDebitedEvent moneyDebitedEvent) {

        if (this.accountBalance >= 0 && (this.accountBalance - moneyDebitedEvent.debitAmount) < 0) {
            AggregateLifecycle.apply(new PaymentOnHoldEvent(this.transactionId, PaymentStatus.ONHOLD));
        }
        this.accountBalance -= moneyDebitedEvent.debitAmount;
    }

    @EventSourcingHandler
    protected void on(final PaymentOnHoldEvent accountHeldEvent) {
        this.status = String.valueOf(accountHeldEvent.status);
    }

}
