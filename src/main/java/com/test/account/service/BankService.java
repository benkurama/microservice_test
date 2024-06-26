package com.test.account.service;

import com.test.account.entity.Banks;

import java.util.List;

public interface BankService {
    List<Banks> getAllBanksByCustomerId(long customerId);
}
