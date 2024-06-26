package com.test.account.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "banks")
@Getter
@Setter
public class Banks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ACCOUNTNUMBER")
    private String accountNumber;

    @Column(name = "ACCOUNTTYPE")
    private String accountType;

    @Column(name = "ACCOUNTBALANCE")
    private int accountBalance;

    @Column(name = "CUSTOMERID")
    private int customerID;
}
