package com.test.account.service.impl;

import com.test.account.entity.Banks;
import com.test.account.entity.Customer;
import com.test.account.exception.ResourceNotFoundException;
import com.test.account.repository.BankRepository;
import com.test.account.service.BankService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BankServiceImpl implements BankService {

    @Autowired
    BankRepository bankRepository;

    @Override
    public List<Banks> getAllBanksByCustomerId(long customerId) {
        List<Banks> banksDB = this.bankRepository.findAll();

        List<Banks> bankFilter = banksDB.stream().filter(xx -> xx.getCustomerID() == customerId).collect(Collectors.toList());

        return bankFilter;

    }
}
