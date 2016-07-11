package com.codetron.cloud.queue.draw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by josetesan on 10/07/16.
 */
@Service
public class DrawService {

    private DrawRepository drawRepository;

    @Autowired
    public DrawService(DrawRepository drawRepository) {
        this.drawRepository = drawRepository;
    }


    public Draw retrieveHotel(final Long id) {
        return this.drawRepository.findOne(id);
    }

    public Draw saveHotel(final Draw draw) {
        return this.drawRepository.save(draw);
    }


    public List<Draw> retrieveAll() {
        return this.drawRepository.findAll();
    }

}
