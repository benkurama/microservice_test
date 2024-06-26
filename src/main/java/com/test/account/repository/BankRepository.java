package com.test.account.repository;

import com.test.account.entity.Banks;
import com.test.account.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Banks, Long> {

}
