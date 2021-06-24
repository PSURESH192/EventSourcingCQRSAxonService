package com.springboot.project.eventsourcingcqrs.events;

public class MoneyDebitedEvent extends Event<String>{

    public final double debitAmount;
    public final String currency;

    public MoneyDebitedEvent(String transactionId, double debitAmount, String currency) {
        super(transactionId);
        this.debitAmount = debitAmount;
        this.currency = currency;
    }
}
