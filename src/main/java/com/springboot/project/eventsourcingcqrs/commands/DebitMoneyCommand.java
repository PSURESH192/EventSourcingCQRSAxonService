package com.springboot.project.eventsourcingcqrs.commands;

public class DebitMoneyCommand extends Command<String>{

    public final double debitAmount;
    public final String currency;

    public DebitMoneyCommand(String transactionId, double debitAmount, String currency) {
        super(transactionId);
        this.debitAmount = debitAmount;
        this.currency = currency;
    }
}
