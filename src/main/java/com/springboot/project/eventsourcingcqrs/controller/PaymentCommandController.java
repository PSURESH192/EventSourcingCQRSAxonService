package com.springboot.project.eventsourcingcqrs.controller;

import com.springboot.project.eventsourcingcqrs.model.AccountCreateDTO;
import com.springboot.project.eventsourcingcqrs.model.MoneyCreditDTO;
import com.springboot.project.eventsourcingcqrs.model.MoneyDebitDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.concurrent.CompletableFuture;

@RestController
@Api(value = "Payment Command Controller")
public interface PaymentCommandController {

    @ApiOperation(value = "Create Account", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CompletableFuture<String>> createAccount(@NotNull @RequestBody AccountCreateDTO accountCreateDTO);

    @ApiOperation(value = "Credit Money", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CompletableFuture<String>> creditMoneyToAccount(@NotNull @NotBlank @PathVariable(value = "accountNumber") String accountNumber,
                                                                   @NotNull @RequestBody MoneyCreditDTO moneyCreditDTO);

    @ApiOperation(value = "Debit Money", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CompletableFuture<String>> debitMoneyFromAccount(@NotNull @NotBlank @PathVariable(value = "accountNumber") String accountNumber,
                                                                    @NotNull @RequestBody MoneyDebitDTO moneyDebitDTO);

}
