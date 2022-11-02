package com.bookings.appbookingsws51.service.impl;

import com.bookings.appbookingsws51.entities.Customer;
import com.bookings.appbookingsws51.repository.ICustomerRepository;
import com.bookings.appbookingsws51.service.ICustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CustomerServiceImpl implements ICustomerService {

    private final ICustomerRepository customerRepository;
    public CustomerServiceImpl(ICustomerRepository customerRepository){
        this.customerRepository= customerRepository;
    }

    @Override
    @Transactional
    public Customer save(Customer customer) throws Exception {
        return customerRepository.save(customer);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> getAll() throws Exception {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getById(Long id) throws Exception {
        return customerRepository.findById(id);
    }

    @Override
    public Customer findByDni(String dni) throws Exception {
        return customerRepository.findByDni(dni);
    }

    @Override
    public List<Customer> findByLastName(String lastname) throws Exception {
        return customerRepository.findByLastName(lastname);
    }

    @Override
    public List<Customer> findByFirstName(String firstname) throws Exception {
        return customerRepository.findByFirstName(firstname);
    }

    @Override
    public List<Customer> findByFirstNameAndLastName(String firstname, String lastname) throws Exception {
        return customerRepository.findByFirstNameAndLastName(firstname, lastname);
    }
}
