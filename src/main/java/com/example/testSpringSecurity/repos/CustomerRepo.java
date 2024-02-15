package com.example.testSpringSecurity.repos;

import com.example.testSpringSecurity.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    @Query(value = "SELECT * FROM customers c WHERE c.username = :username"
    , nativeQuery = true)
    Customer findCustomerByUserName(@Param("username") String user_name);
}
