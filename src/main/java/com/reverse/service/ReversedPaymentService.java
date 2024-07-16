package com.reverse.service;

import com.google.gson.Gson;
import com.reverse.entity.Payment;
import com.reverse.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ReversedPaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @KafkaListener(topics = "payment",groupId = "pmt-grp",containerFactory = "containerFactory")
    public void recivedPayment(String payload, @Header(KafkaHeaders.RECEIVED_TOPIC)String topic,@Header(KafkaHeaders.OFFSET)long offset){
     log.info("Payload: {} topic: {} Offset: {}",payload,topic,offset );
        Payment payment=new Gson().fromJson(payload, Payment.class);
        if(payment!=null){
            paymentRepository.save(payment);
        }
    }


    public List<Payment> fetchEmployeePayment() {
        Sort sortBy= Sort.by(new Sort.Order(Sort.Direction.ASC,"trnID").ignoreCase());
        return  paymentRepository.findAll(sortBy);
    }
}
