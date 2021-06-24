package com.springboot.project.eventsourcingcqrs.service;

import com.springboot.project.eventsourcingcqrs.entity.AccountQueryEntity;

import java.util.List;

public interface PaymentQueryService {

    List<Object> getEventsForAccount(String accountNumber);
    AccountQueryEntity getAccount(String accountNumber);
}
