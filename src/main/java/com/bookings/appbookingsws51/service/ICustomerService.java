package com.bookings.appbookingsws51.service;

import com.bookings.appbookingsws51.entities.Customer;
import java.util.List;

public interface ICustomerService extends CrudService<Customer> {
    Customer findByDni(String dni) throws Exception;
    List<Customer> findByLastName(String lastname) throws Exception;
    List<Customer> findByFirstName(String firstname) throws Exception;
    List<Customer> findByFirstNameAndLastName(String firstname, String lastname) throws Exception;
}
