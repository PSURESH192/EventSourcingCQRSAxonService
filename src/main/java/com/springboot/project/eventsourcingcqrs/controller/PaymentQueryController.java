package com.springboot.project.eventsourcingcqrs.controller;

import com.springboot.project.eventsourcingcqrs.entity.AccountQueryEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Api(value = "Payment Query Controller")
public interface PaymentQueryController {

    @ApiOperation(value = "Get Account Details",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AccountQueryEntity> getAccount(@NotNull @NotBlank @PathVariable(value = "accountNumber") String accountNumber);

    @ApiOperation(value = "Get Events for Account",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Object>> getEventsForAccount(@NotNull @NotBlank @PathVariable(value = "accountNumber") String accountNumber);
}
