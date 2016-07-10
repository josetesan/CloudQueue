package com.codetron.cloud.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by josetesan on 10/07/16.
 */
@Service
public class BookingService {

    private BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }


    public Booking createBooking(final Booking booking) {
        return this.bookingRepository.save(booking);
    }


    public void removeBooking(final Booking booking) {
        this.bookingRepository.delete(booking);
    }

    public List<Booking> retrieveAllByCustomerId(final Long customerId) {
        return this.bookingRepository.findAllByCustomerId(customerId);
    }
}
