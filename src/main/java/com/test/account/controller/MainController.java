package com.test.account.controller;

import com.test.account.entity.Banks;
import com.test.account.entity.Customer;
import com.test.account.service.BankService;
import com.test.account.service.CustomerService;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class MainController {

    @Autowired
    CustomerService customerService;

    @Autowired
    BankService bankService;

    @GetMapping("/show")
    public String mainFunction(){
        return "Welcome to my World";
    }

    @PostMapping("/account")
    public ResponseEntity<Map> createProduct(@RequestBody Customer customer) {

        boolean isSuccess = true;

        String errorContainer = "";

        if (customer.getCustomerName().isEmpty()) {
            errorContainer = "Name is required field";
            isSuccess = false;
        }
        if (customer.getCustomerEmail().isEmpty()) {
            errorContainer = "Email is required field";
            isSuccess = false;
        }
        if (customer.getCustomerMobile().isEmpty()) {
            errorContainer = "Mobile is required field";
            isSuccess = false;
        }
        // ---------------------------------------------
        Customer customerRes = null;

        if (isSuccess) {
            try {
                customerRes = customerService.createCustomer(customer);
            } catch (Exception e) {
                isSuccess = false;
                errorContainer = "inserting into database error:" + e.getMessage();
            }
        }

        if(isSuccess){
            Map map = new HashMap();
            map.put("customerNumber", customerRes.getId());
            map.put("transactionStatusCode", 201);
            map.put("transactionStatusDescription", "Customer account created");

            return  ResponseEntity.status(HttpStatus.CREATED).body(map);
        } else {
            Map map = new HashMap();
            map.put("transactionStatusCode", 400);
            map.put("transactionStatusDescription", errorContainer);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }

    }

    @GetMapping("/account/{id}")
    public ResponseEntity<Map> queryCustomer(@PathVariable long id){

        boolean isSuccess = true;

        Customer customer = null;
        try {
            customer = customerService.getCustomerById(id);
        } catch (Exception e) {
            isSuccess = false;
        }

        if(isSuccess){
            Map map = new HashMap();

            map.put("customerNumber", customer.getId());
            map.put("customerName", customer.getCustomerName());
            map.put("customerMobile", customer.getCustomerMobile());
            map.put("customerEmail", customer.getCustomerEmail());
            map.put("address1", customer.getAddress1());
            map.put("address2", customer.getAddress2());

            map.put("transactionStatusCode", 302);
            map.put("transactionStatusDescription","Customer Account Found");

            /*List<Map> mapList = new ArrayList<>();
            mapList.add(Map.of("accountNumber ",10001, "accountType","Savings","availableBalance",500));
            map.put("Savings", mapList);*/

            //List<Banks> bankList = bankService.getAllBanksByCustomerId(customer.getId());
            List<Banks> bankList = bankService.getAllBanksByCustomerId(customer.getId());
            map.put("Savings", bankList);

            return ResponseEntity.status(HttpStatus.FOUND).body(map);
        } else {
            Map map = new HashMap();

            map.put("transactionStatusCode", 404);
            map.put("transactionStatusDescription","Customer Not Found");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
    }
}
