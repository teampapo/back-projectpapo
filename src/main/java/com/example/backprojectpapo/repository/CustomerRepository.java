package com.example.backprojectpapo.repository;

import com.example.backprojectpapo.model.Customer;
import com.example.backprojectpapo.model.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {
    Optional<Customer> findByEmail(String email);

    @Query("select sr " +
            "from Customer c " +
            "join c.serviceRequests sr " +
            "   where c.id = :customer_id " +
            "and " +
            "   sr.dataService > :currentDate")
    List<ServiceRequest> findServiceRequestByCustomerIdAfterDatetime(@Param("customer_id") Integer id,
                                                                     @Param("date_time")LocalDateTime dateTime);
}
