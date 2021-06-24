package com.springboot.project.eventsourcingcqrs.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoneyDebitDTO {

    private double debitAmount;
    private String currency;

}
