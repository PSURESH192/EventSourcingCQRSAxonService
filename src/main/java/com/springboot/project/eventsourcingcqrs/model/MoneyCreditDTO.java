package com.springboot.project.eventsourcingcqrs.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoneyCreditDTO {

    private double creditAmount;
    private String currency;

}
