package com.springboot.project.eventsourcingcqrs;

import com.springboot.project.eventsourcingcqrs.aggregates.PaymentAggregate;
import com.springboot.project.eventsourcingcqrs.entity.AccountQueryEntity;
import com.springboot.project.eventsourcingcqrs.entity.handler.AccountQueryEntityManager;
import com.springboot.project.eventsourcingcqrs.events.Event;
import com.springboot.project.eventsourcingcqrs.repository.AccountRepository;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountQueryEntityManagerTest {

    @InjectMocks
    AccountQueryEntityManager accountQueryEntityManager;

    @Mock
    AccountRepository accountRepository;

    @Mock
    EventSourcingRepository<PaymentAggregate> paymentAggregateEventSourcingRepository;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = Exception.class)
    public void testOn(){
        Event<String> event = new Event<>("d842631ab9804bdd88f4fd00024c6ec3");
        PaymentAggregate paymentAggregate = new PaymentAggregate();
        paymentAggregate.setTransactionId("d842631ab9804bdd88f4fd00024c6ec3");
        Mockito.when(accountQueryEntityManager.getAccountFromEvent(event)).thenReturn(paymentAggregate);
        accountQueryEntityManager.on(event);
        Assert.assertNotNull(event);
    }

    @Test
    public void testBuildQueryAccount(){
        PaymentAggregate paymentAggregate = new PaymentAggregate();
        paymentAggregate.setTransactionId("d842631ab9804bdd88f4fd00024c6ec3");
        paymentAggregate.setAccountBalance(25000L);
        paymentAggregate.setCurrency("INR");
        paymentAggregate.setStatus("SUCCESSFUL");
        AccountQueryEntity accountQueryEntity = new AccountQueryEntity();
        accountQueryEntity.setTransactionId(paymentAggregate.getTransactionId());
        Mockito.when(accountRepository.findById(paymentAggregate.getTransactionId())).thenReturn(Optional.of(accountQueryEntity));
        accountQueryEntity = accountQueryEntityManager.buildQueryAccount(paymentAggregate);
        Assert.assertEquals(paymentAggregate.getTransactionId(),accountQueryEntity.getTransactionId());
    }

    @Test
    public void testPersistAccount(){
        AccountQueryEntity accountQueryEntity = new AccountQueryEntity();
        accountQueryEntity.setTransactionId("d842631ab9804bdd88f4fd00024c6ec3");
        accountQueryEntity.setAccountBalance(25000L);
        accountQueryEntity.setCurrency("INR");
        accountQueryEntity.setStatus("SUCCESSFUL");
        Mockito.when(accountRepository.save(accountQueryEntity)).thenReturn(accountQueryEntity);
        accountQueryEntityManager.persistAccount(accountQueryEntity);
        Assert.assertNotNull(accountQueryEntity.getTransactionId());
    }
}
