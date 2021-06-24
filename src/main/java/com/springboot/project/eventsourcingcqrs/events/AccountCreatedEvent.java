package com.springboot.project.eventsourcingcqrs.events;

public class AccountCreatedEvent extends Event<String>{

    public final double accountBalance;
    public final String currency;

    public AccountCreatedEvent(String transactionId, double accountBalance, String currency) {
        super(transactionId);
        this.accountBalance = accountBalance;
        this.currency = currency;
    }
}
