package com.springboot.project.eventsourcingcqrs.service;

import com.springboot.project.eventsourcingcqrs.model.AccountCreateDTO;
import com.springboot.project.eventsourcingcqrs.model.MoneyCreditDTO;
import com.springboot.project.eventsourcingcqrs.model.MoneyDebitDTO;
import com.springboot.project.eventsourcingcqrs.service.impl.PaymentCommandServiceImpl;
import org.axonframework.commandhandling.gateway.CommandGateway;
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

import java.util.concurrent.CompletableFuture;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentCommandServiceTest {

    @InjectMocks
    PaymentCommandServiceImpl paymentCommandService;

    @Mock
    CommandGateway commandGateway;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateAccount(){
        AccountCreateDTO accountCreateDTO = new AccountCreateDTO();
        accountCreateDTO.setAccountBalance(25000L);
        accountCreateDTO.setCurrency("INR");
        Mockito.when(commandGateway.send(Mockito.any())).thenReturn(CompletableFuture.anyOf());
        CompletableFuture<String> account = paymentCommandService.createAccount(accountCreateDTO);
        Assert.assertNotNull(account);
    }

    @Test
    public void testCreditMoneyToAccount(){
        MoneyCreditDTO moneyCreditDTO = new MoneyCreditDTO();
        moneyCreditDTO.setCreditAmount(25000L);
        moneyCreditDTO.setCurrency("INR");
        Mockito.when(commandGateway.send(Mockito.any())).thenReturn(CompletableFuture.anyOf());
        CompletableFuture<String> account = paymentCommandService.creditMoneyToAccount("d842631ab9804bdd88f4fd00024c6ec3",moneyCreditDTO);
        Assert.assertNotNull(account);
    }

    @Test
    public void testDebitMoneyFromAccount(){
        MoneyDebitDTO moneyDebitDTO = new MoneyDebitDTO();
        moneyDebitDTO.setDebitAmount(25000L);
        moneyDebitDTO.setCurrency("INR");
        Mockito.when(commandGateway.send(Mockito.any())).thenReturn(CompletableFuture.anyOf());
        CompletableFuture<String> account = paymentCommandService.debitMoneyFromAccount("d842631ab9804bdd88f4fd00024c6ec3",moneyDebitDTO);
        Assert.assertNotNull(account);
    }
}
