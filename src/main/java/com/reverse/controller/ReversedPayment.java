package com.reverse.controller;


import com.reverse.entity.Payment;
import com.reverse.service.ReversedPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReversedPayment {

    @Autowired
    private ReversedPaymentService reversedPaymentService;


    @GetMapping("/")
    public ResponseEntity<List<Payment>> recivedPayment(){
        return new ResponseEntity<>(reversedPaymentService.fetchEmployeePayment(), HttpStatus.OK);
    }

}
