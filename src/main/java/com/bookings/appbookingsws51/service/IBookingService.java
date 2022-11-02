package com.bookings.appbookingsws51.service;

import com.bookings.appbookingsws51.entities.Booking;
import java.util.Date;
import java.util.List;

public interface IBookingService extends CrudService<Booking>{
    List<Booking> findBookingBetweenDates(Date checking_date, Date checkout_date) throws Exception;
}
