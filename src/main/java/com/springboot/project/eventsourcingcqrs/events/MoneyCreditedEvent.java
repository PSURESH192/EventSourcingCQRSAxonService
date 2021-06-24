package com.springboot.project.eventsourcingcqrs.events;

public class MoneyCreditedEvent extends Event<String >{

    public final double creditAmount;
    public final String currency;

    public MoneyCreditedEvent(String transactionId, double creditAmount, String currency) {
        super(transactionId);
        this.creditAmount = creditAmount;
        this.currency = currency;
    }
}
