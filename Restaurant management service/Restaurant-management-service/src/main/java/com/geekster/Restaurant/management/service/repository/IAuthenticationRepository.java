package com.geekster.Restaurant.management.service.repository;

import com.geekster.Restaurant.management.service.models.Authentication;
import com.geekster.Restaurant.management.service.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthenticationRepository extends JpaRepository<Authentication , Long> {
    Authentication findBycustomer(Customer customerObj);

    Authentication findFirstByAuthenticationToken(String token);
}
