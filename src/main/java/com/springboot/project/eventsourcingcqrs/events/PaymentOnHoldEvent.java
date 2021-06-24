package com.springboot.project.eventsourcingcqrs.events;

import com.springboot.project.eventsourcingcqrs.aggregates.PaymentStatus;

public class PaymentOnHoldEvent extends Event<String>{

    public final PaymentStatus status;

    public PaymentOnHoldEvent(String transactionId, PaymentStatus status) {
        super(transactionId);
        this.status = status;
    }
}
