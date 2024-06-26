package com.test.account.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class Customer {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "ID")
    private long id;

    @Column(name = "CUSTOMERNAME")
    private String customerName;

    @Column(name = "CUSTOMERMOBILE")
    private String customerMobile;

    @Column(name = "CUSTOMEREMAIL")
    private String customerEmail;

    @Column(name = "ADDRESS1")
    private String address1;

    @Column(name = "ADDRESS2")
    private String address2;

}
