package com.springboot.project.eventsourcingcqrs.service.impl;

import com.springboot.project.eventsourcingcqrs.entity.AccountQueryEntity;
import com.springboot.project.eventsourcingcqrs.repository.AccountRepository;
import com.springboot.project.eventsourcingcqrs.service.PaymentQueryService;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentQueryServiceImpl implements PaymentQueryService {

    @Autowired
    EventStore eventStore;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<Object> getEventsForAccount(String accountNumber) {
        return eventStore.readEvents(accountNumber)
                .asStream()
                .map(Message::getPayload)
                .collect(Collectors.toList());
    }

    @Override
    public AccountQueryEntity getAccount(String accountNumber) {
        return accountRepository.findById(accountNumber).get();
    }
}
