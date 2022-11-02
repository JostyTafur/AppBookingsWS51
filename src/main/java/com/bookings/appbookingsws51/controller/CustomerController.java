package com.bookings.appbookingsws51.controller;

import com.bookings.appbookingsws51.entities.Customer;
import com.bookings.appbookingsws51.service.ICustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final ICustomerService customerService;

    public CustomerController(ICustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> findAll(){
        try{
            List<Customer> customers = customerService.getAll();
            return new ResponseEntity<>(customers, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> findById(@PathVariable("id") Long id){
        try{
            Optional<Customer> customer = customerService.getById(id);
            if(!customer.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(customer.get(), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByDni/{dni}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> findByDni(@PathVariable("dni") String dni){
        try{
            Customer customer = customerService.findByDni(dni);
            if(customer==null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByLastname/{lastname}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> findByLastName(@PathVariable("lastname")String lastname){
        try{
            List<Customer> customers = customerService.findByLastName(lastname);
            if(customers.size()>0)
                return new ResponseEntity<>(customers, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByFirstName/{firstname}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> findByFirstName(@PathVariable("firstname") String firstname){
        try{
            List<Customer> customers = customerService.findByFirstName(firstname);
            if(customers.size()>0)
                return new ResponseEntity<>(customers, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByFirstnameAndLastname/{firstname}/{lastname}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> findByFirstnameAndLastname(
            @PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname){
        try{
            List<Customer> customers = customerService.findByFirstNameAndLastName(firstname, lastname);
            if(customers.size()>0)
                return new ResponseEntity<>(customers, HttpStatus.OK);
            else
                return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> insertCustomer(@Valid @RequestBody Customer customer){
        try{
            Customer customerNew = customerService.save(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body(customerNew);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable("id") Long id, @Valid @RequestBody Customer customer){
        try{
            Optional<Customer> customerUp = customerService.getById(id);
            if(!customerUp.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            customer.setId(id);
            customerService.save(customer);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> deleteCustomer (@PathVariable("id") Long id){
        try{
            Optional<Customer> customerDelete = customerService.getById(id);
            if(!customerDelete.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            customerService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
