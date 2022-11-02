package com.bookings.appbookingsws51.repository;

import com.bookings.appbookingsws51.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IBookingRepository extends JpaRepository<Booking, Long> {
    @Query("select b from Booking b where b.checkinDate=:checkin and b.checkoutDate=:checkout")
    List<Booking> findBookingBetweenDates(@Param("checkin") Date checking_date,
                                          @Param("checkout") Date checkout_date);
}
