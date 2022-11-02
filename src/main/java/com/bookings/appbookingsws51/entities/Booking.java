package com.bookings.appbookingsws51.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "bookings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="description", nullable = true, length = 200)
    private String description;
    @Column(name="number_persons", nullable = false)
    private int numberOfPersons;
    @Column(name = "create_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
    @Column(name = "checkin_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date checkinDate;
    @Column(name="checkout_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date checkoutDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Customer customer;
}
