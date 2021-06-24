package com.springboot.project.eventsourcingcqrs.controller.impl;

import com.springboot.project.eventsourcingcqrs.controller.PaymentQueryController;
import com.springboot.project.eventsourcingcqrs.entity.AccountQueryEntity;
import com.springboot.project.eventsourcingcqrs.service.PaymentQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/payments")
public class PaymentQueryControllerImpl implements PaymentQueryController {

    @Autowired
    PaymentQueryService paymentQueryService;

    @Override
    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountQueryEntity> getAccount(@NotNull @NotBlank @PathVariable (value = "accountNumber") String accountNumber) {
        return new ResponseEntity<>(paymentQueryService.getAccount(accountNumber), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{accountNumber}/events")
    public ResponseEntity<List<Object>> getEventsForAccount(@NotNull @NotBlank @PathVariable(value = "accountNumber") String accountNumber){
        return new ResponseEntity<>(paymentQueryService.getEventsForAccount(accountNumber), HttpStatus.OK);
    }
}
