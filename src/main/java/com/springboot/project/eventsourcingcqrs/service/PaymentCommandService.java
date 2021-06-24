package com.springboot.project.eventsourcingcqrs.service;

import com.springboot.project.eventsourcingcqrs.model.AccountCreateDTO;
import com.springboot.project.eventsourcingcqrs.model.MoneyCreditDTO;
import com.springboot.project.eventsourcingcqrs.model.MoneyDebitDTO;

import java.util.concurrent.CompletableFuture;

public interface PaymentCommandService {

    CompletableFuture<String> createAccount(AccountCreateDTO accountCreateDTO);

    CompletableFuture<String> creditMoneyToAccount(String accountNumber, MoneyCreditDTO moneyCreditDTO);

    CompletableFuture<String> debitMoneyFromAccount(String accountNumber, MoneyDebitDTO moneyDebitDTO);
}
