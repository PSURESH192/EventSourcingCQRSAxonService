package com.springboot.project.eventsourcingcqrs.commands;

public class CreditMoneyCommand extends Command<String>{

    public final double creditAmount;
    public final String currency;

    public CreditMoneyCommand(String transactionId, double creditAmount, String currency) {
        super(transactionId);
        this.creditAmount = creditAmount;
        this.currency = currency;
    }
}
