package com.springboot.project.eventsourcingcqrs.events;

import com.springboot.project.eventsourcingcqrs.aggregates.PaymentStatus;

public class PaymentSuccessEvent extends Event<String>{

    public final PaymentStatus status;

    public PaymentSuccessEvent(String transactionId, PaymentStatus status) {
        super(transactionId);
        this.status = status;
    }
}
