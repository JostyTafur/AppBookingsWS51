package com.bookings.appbookingsws51.service.impl;

import com.bookings.appbookingsws51.entities.Booking;
import com.bookings.appbookingsws51.repository.IBookingRepository;
import com.bookings.appbookingsws51.service.IBookingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookingServiceImpl implements IBookingService {
    private final IBookingRepository bookingRepository;

    public BookingServiceImpl(IBookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    @Transactional
    public Booking save(Booking booking) throws Exception {
        return bookingRepository.save(booking);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        bookingRepository.deleteById(id);
    }

    @Override
    public List<Booking> getAll() throws Exception {
        return bookingRepository.findAll();
    }

    @Override
    public Optional<Booking> getById(Long id) throws Exception {
        return bookingRepository.findById(id);
    }

    @Override
    public List<Booking> findBookingBetweenDates(Date checking_date, Date checkout_date) throws Exception {
        return bookingRepository.findBookingBetweenDates(checking_date, checkout_date);
    }
}
