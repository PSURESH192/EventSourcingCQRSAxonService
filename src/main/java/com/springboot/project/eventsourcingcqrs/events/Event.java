package com.springboot.project.eventsourcingcqrs.events;

public class Event<T>{

    public final T transactionId;

    public Event(T transactionId) {
        this.transactionId = transactionId;
    }
}
