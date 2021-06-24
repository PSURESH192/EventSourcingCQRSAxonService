package com.springboot.project.eventsourcingcqrs.model;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreateDTO {

    private double accountBalance;
    private String currency;

}
