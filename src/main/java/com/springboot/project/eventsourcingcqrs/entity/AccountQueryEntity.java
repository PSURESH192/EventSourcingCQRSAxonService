package com.springboot.project.eventsourcingcqrs.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "account")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountQueryEntity {
    @Id
    private String transactionId;
    private double accountBalance;
    private String currency;
    private String status;
}
