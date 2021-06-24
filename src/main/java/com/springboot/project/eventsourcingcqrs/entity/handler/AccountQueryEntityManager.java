package com.springboot.project.eventsourcingcqrs.entity.handler;

import com.springboot.project.eventsourcingcqrs.aggregates.PaymentAggregate;
import com.springboot.project.eventsourcingcqrs.entity.AccountQueryEntity;
import com.springboot.project.eventsourcingcqrs.events.Event;
import com.springboot.project.eventsourcingcqrs.repository.AccountRepository;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountQueryEntityManager {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    @Qualifier("paymentAggregateEventSourcingRepository")
    EventSourcingRepository<PaymentAggregate> paymentAggregateEventSourcingRepository;

    @EventSourcingHandler
    public void on(Event event) {
        persistAccount(buildQueryAccount(getAccountFromEvent(event)));
    }

    private PaymentAggregate getAccountFromEvent(Event event) {
        return paymentAggregateEventSourcingRepository.load(event.transactionId.toString()).getWrappedAggregate().getAggregateRoot();
    }

    private AccountQueryEntity buildQueryAccount(PaymentAggregate paymentAggregate) {
        Optional<AccountQueryEntity> accountQueryEntityOptional = accountRepository.findById(paymentAggregate.getTransactionId());
        AccountQueryEntity accountQueryEntity = accountQueryEntityOptional.isPresent() ? accountQueryEntityOptional.get() : new AccountQueryEntity();
        accountQueryEntity.setTransactionId(paymentAggregate.getTransactionId());
        accountQueryEntity.setAccountBalance(paymentAggregate.getAccountBalance());
        accountQueryEntity.setCurrency(paymentAggregate.getCurrency());
        accountQueryEntity.setStatus(paymentAggregate.getStatus());
        return accountQueryEntity;
    }

    private void persistAccount(AccountQueryEntity accountQueryEntity) {
        accountRepository.save(accountQueryEntity);
    }
}
