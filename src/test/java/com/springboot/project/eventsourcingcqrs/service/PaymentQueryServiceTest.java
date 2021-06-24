package com.springboot.project.eventsourcingcqrs.service;

import com.springboot.project.eventsourcingcqrs.entity.AccountQueryEntity;
import com.springboot.project.eventsourcingcqrs.repository.AccountRepository;
import com.springboot.project.eventsourcingcqrs.service.impl.PaymentQueryServiceImpl;
import org.axonframework.eventsourcing.eventstore.DomainEventStream;
import org.axonframework.eventsourcing.eventstore.EventStore;
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

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentQueryServiceTest {

    @InjectMocks
    PaymentQueryServiceImpl paymentQueryService;

    @Mock
    EventStore eventStore;

    @Mock
    AccountRepository accountRepository;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetEventsForAccount(){
        Mockito.when(eventStore.readEvents(Mockito.anyString())).thenReturn(DomainEventStream.empty());
        List<Object> eventsForAccount = paymentQueryService.getEventsForAccount("d842631ab9804bdd88f4fd00024c6ec3");
        Assert.assertNotNull(eventsForAccount);
    }

    @Test
    public void testGetAccount(){
        AccountQueryEntity accountQueryEntity = new AccountQueryEntity();
        accountQueryEntity.setTransactionId("d842631ab9804bdd88f4fd00024c6ec3");
        accountQueryEntity.setAccountBalance(25000L);
        accountQueryEntity.setCurrency("INR");
        accountQueryEntity.setStatus("SUCCESSFUL");
        Mockito.when(accountRepository.findById("d842631ab9804bdd88f4fd00024c6ec3")).thenReturn(Optional.of(accountQueryEntity));
        accountQueryEntity = paymentQueryService.getAccount("d842631ab9804bdd88f4fd00024c6ec3");
        Assert.assertEquals("d842631ab9804bdd88f4fd00024c6ec3", accountQueryEntity.getTransactionId());
    }

}
