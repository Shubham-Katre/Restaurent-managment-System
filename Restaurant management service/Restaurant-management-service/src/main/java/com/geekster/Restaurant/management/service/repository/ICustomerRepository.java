package com.geekster.Restaurant.management.service.repository;

import com.geekster.Restaurant.management.service.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {

    Customer findFirstByCustomerEmailId(String userEmailId);
}
