package com.springboot.project.eventsourcingcqrs.controller.impl;

import com.springboot.project.eventsourcingcqrs.controller.PaymentCommandController;
import com.springboot.project.eventsourcingcqrs.model.AccountCreateDTO;
import com.springboot.project.eventsourcingcqrs.model.MoneyCreditDTO;
import com.springboot.project.eventsourcingcqrs.model.MoneyDebitDTO;
import com.springboot.project.eventsourcingcqrs.service.PaymentCommandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping(value = "/payments")
public class PaymentCommandControllerImpl implements PaymentCommandController {

    @Autowired
    PaymentCommandService paymentCommandService;

    @Override
    @PostMapping("/account")
    public ResponseEntity<CompletableFuture<String>> createAccount(@NotNull @RequestBody AccountCreateDTO accountCreateDTO){
        return new ResponseEntity<>(paymentCommandService.createAccount(accountCreateDTO), HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/credits/{accountNumber}")
    public ResponseEntity<CompletableFuture<String>> creditMoneyToAccount(@NotNull @NotBlank @PathVariable(value = "accountNumber") String accountNumber,
                                                                          @NotNull @RequestBody MoneyCreditDTO moneyCreditDTO){
        return new ResponseEntity<>(paymentCommandService.creditMoneyToAccount(accountNumber,moneyCreditDTO), HttpStatus.OK);
    }

    @Override
    @PutMapping("/debits/{accountNumber}")
    public ResponseEntity<CompletableFuture<String>> debitMoneyFromAccount(@NotNull @NotBlank @PathVariable(value = "accountNumber") String accountNumber,
                                                                           @NotNull @RequestBody MoneyDebitDTO moneyDebitDTO){
        return new ResponseEntity<>(paymentCommandService.debitMoneyFromAccount(accountNumber,moneyDebitDTO), HttpStatus.OK);
    }
}
