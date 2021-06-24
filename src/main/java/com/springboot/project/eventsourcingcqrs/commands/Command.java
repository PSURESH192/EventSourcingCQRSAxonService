package com.springboot.project.eventsourcingcqrs.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class Command<T> {

    @TargetAggregateIdentifier
    public final T transactionId;

    public Command(T transactionId) {
        this.transactionId = transactionId;
    }
}
