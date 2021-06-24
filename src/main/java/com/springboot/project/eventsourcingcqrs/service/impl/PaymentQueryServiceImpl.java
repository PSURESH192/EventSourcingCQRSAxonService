package com.springboot.project.eventsourcingcqrs.service.impl;

import com.springboot.project.eventsourcingcqrs.entity.AccountQueryEntity;
import com.springboot.project.eventsourcingcqrs.repository.AccountRepository;
import com.springboot.project.eventsourcingcqrs.service.PaymentQueryService;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
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
        Optional<AccountQueryEntity> accountQueryEntityOptional = accountRepository.findById(accountNumber);
        return accountQueryEntityOptional.isPresent() ? accountQueryEntityOptional.get() : new AccountQueryEntity();
    }
}
