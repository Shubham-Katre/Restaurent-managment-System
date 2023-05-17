package com.geekster.Restaurant.management.service.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    private String customerEmailId;
    private String customerPassword;
    private  String customerFirstName;
    private String customerLastName;
    private String customerAddress;
    private  String customerMobileNumber;

    public Customer(String customerEmailId, String customerPassword, String customerFirstName, String customerLastName, String customerAddress, String customerMobileNumber) {
        this.customerEmailId = customerEmailId;
        this.customerPassword = customerPassword;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerAddress = customerAddress;
        this.customerMobileNumber = customerMobileNumber;
    }


}
