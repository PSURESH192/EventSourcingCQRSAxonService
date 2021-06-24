package com.springboot.project.eventsourcingcqrs.commands;

public class CreateAccountCommand extends Command<String>{

    public final double accountBalance;
    public final String currency;

    public CreateAccountCommand(String transactionId, double accountBalance, String currency) {
        super(transactionId);
        this.accountBalance = accountBalance;
        this.currency = currency;
    }
}
