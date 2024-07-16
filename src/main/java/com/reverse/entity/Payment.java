package com.reverse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pay_tbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String accountNumber;
    private String accnHolderName;
    private String trnID;
    private Double amount;
    private String debitAccount;
    private String paymentStatus;
    private String paymentDate;

}
