package com.bookings.appbookingsws51.repository;

import com.bookings.appbookingsws51.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByDni(String dni);
    List<Customer> findByLastName(String lastname);
    List<Customer> findByFirstName(String firstname);
    List<Customer> findByFirstNameAndLastName(String firstname, String lastname);
}
