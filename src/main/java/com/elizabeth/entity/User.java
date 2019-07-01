package com.elizabeth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
public class User {

    @Id
    private Long userId;

    private String firstName;

    private BigDecimal accountBalance;

}
