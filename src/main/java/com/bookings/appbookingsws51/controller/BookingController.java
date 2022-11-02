package com.bookings.appbookingsws51.controller;

import com.bookings.appbookingsws51.entities.Booking;
import com.bookings.appbookingsws51.entities.Customer;
import com.bookings.appbookingsws51.service.IBookingService;
import com.bookings.appbookingsws51.service.ICustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final ICustomerService customerService;
    private final IBookingService bookingService;

    public BookingController(ICustomerService customerService, IBookingService bookingService) {
        this.customerService = customerService;
        this.bookingService = bookingService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Booking>>findAllBookings(){
        try{
            List<Booking> bookings = bookingService.getAll();
            if(bookings.size()>0)
                return new ResponseEntity<>(bookings, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking>findBookingById(@PathVariable("id") Long id) {
        try{
            Optional<Booking> booking = bookingService.getById(id);
            if(!booking.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<>(booking.get(), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchBetweenDates", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Booking>> findBookingBetweenDates(
            @RequestParam(name="checkin_date") String checkin_string,
            @RequestParam(name="checkout_date") String checkout_string) {
        try{
            Date checkin_date = ParseDate(checkin_string);
            Date checkout_date = ParseDate(checkout_string);

            List<Booking> bookings = bookingService.findBookingBetweenDates(checkin_date, checkout_date);
            if(bookings.size()>0)
                return new ResponseEntity<>(bookings, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static Date ParseDate(String date){
        SimpleDateFormat format= new SimpleDateFormat("dd/MM/yyyy");
        Date result = null;
        try{
            result = format.parse(date);
        }catch (Exception ex){
        }
        return result;
    }

    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking> insertBooking( @PathVariable("id") Long id, @Valid @RequestBody Booking booking){
        try{
            Optional<Customer> customer = customerService.getById(id);
            if(customer.isPresent()){
                booking.setCustomer(customer.get());
                Booking newBooking = bookingService.save(booking);
                return ResponseEntity.status(HttpStatus.CREATED).body(newBooking);
            }
            else
                return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking> updateBooking(@PathVariable("id") Long id, @Valid @RequestBody Booking booking){
        try{
            Optional<Booking> bookingOld = bookingService.getById(id);
            if(!bookingOld.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else{
                booking.setId(id);
                bookingService.save(booking);
                return new ResponseEntity<>(booking, HttpStatus.OK);
            }
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking> deleteBooking(@PathVariable("id") Long id){
        try{
            Optional<Booking> bookingDelete = bookingService.getById(id);
            if(bookingDelete.isPresent()){
                bookingService.delete(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
