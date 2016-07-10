package com.codetron.cloud.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by josetesan on 10/07/16.
 */
@Service
public class HotelService {

    private HotelRepository hotelRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }


    public Hotel retrieveHotel(final Long id) {
        return this.hotelRepository.findOne(id);
    }

    public Hotel saveHotel(final Hotel hotel) {
        return this.hotelRepository.save(hotel);
    }


    public List<Hotel> retrieveAll() {
        return this.hotelRepository.findAll();
    }

}
