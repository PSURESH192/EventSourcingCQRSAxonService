package com.springboot.project.eventsourcingcqrs.service.impl;

import com.springboot.project.eventsourcingcqrs.commands.CreateAccountCommand;
import com.springboot.project.eventsourcingcqrs.commands.CreditMoneyCommand;
import com.springboot.project.eventsourcingcqrs.commands.DebitMoneyCommand;
import com.springboot.project.eventsourcingcqrs.model.AccountCreateDTO;
import com.springboot.project.eventsourcingcqrs.model.MoneyCreditDTO;
import com.springboot.project.eventsourcingcqrs.model.MoneyDebitDTO;
import com.springboot.project.eventsourcingcqrs.service.PaymentCommandService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class PaymentCommandServiceImpl implements PaymentCommandService {

    @Autowired
    CommandGateway commandGateway;

    @Override
    public CompletableFuture<String> createAccount(AccountCreateDTO accountCreateDTO) {
        String transactionId = UUID.randomUUID().toString().replace("-", "");
        return commandGateway.send(new CreateAccountCommand("ACC:" + transactionId, accountCreateDTO.getAccountBalance(), accountCreateDTO.getCurrency()));
    }

    @Override
    public CompletableFuture<String> creditMoneyToAccount(String accountNumber, MoneyCreditDTO moneyCreditDTO) {
        return commandGateway.send(new CreditMoneyCommand(accountNumber, moneyCreditDTO.getCreditAmount(), moneyCreditDTO.getCurrency()));
    }

    @Override
    public CompletableFuture<String> debitMoneyFromAccount(String accountNumber, MoneyDebitDTO moneyDebitDTO) {
        return commandGateway.send(new DebitMoneyCommand(accountNumber, moneyDebitDTO.getDebitAmount(), moneyDebitDTO.getCurrency()));
    }
}
