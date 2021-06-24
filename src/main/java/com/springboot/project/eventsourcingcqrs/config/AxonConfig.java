package com.springboot.project.eventsourcingcqrs.config;

import com.springboot.project.eventsourcingcqrs.aggregates.PaymentAggregate;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Bean
    EventSourcingRepository<PaymentAggregate> paymentAggregateEventSourcingRepository(EventStore eventStore){
        EventSourcingRepository<PaymentAggregate> repository = EventSourcingRepository.builder(PaymentAggregate.class).eventStore(eventStore).build();
        return repository;
    }
}
