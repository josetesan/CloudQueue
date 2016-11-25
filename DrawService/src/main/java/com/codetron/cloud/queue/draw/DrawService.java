package com.codetron.cloud.queue.draw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by josetesan on 10/07/16.
 */
@Service
public class DrawService {

    private final static Integer MAX_BETS = 10;

    private static final Logger LOGGER = LoggerFactory.getLogger(DrawService.class);

    private DrawRepository drawRepository;

    private RabbitTemplate rabbitTemplate;

    private List<String> numbers = new ArrayList<>(MAX_BETS);

    @Autowired
    public DrawService(final DrawRepository drawRepository, final RabbitTemplate rabbitTemplate) {
        this.drawRepository = drawRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void playDraw() {

        final Integer random = LocalTime.now().getSecond() % 10;

        final String winner = numbers.get(random);

        LOGGER.info("Looks like we have a winner !!, {}", winner);

        final Draw draw = Draw.builder()
                .winningNumber(winner)
                .dateCreated(LocalDate.now())
                .build();

        this.drawRepository.save(draw);

        // notify winner !

        this.rabbitTemplate.convertAndSend("OUT.DRAW",winner);

        // reset numbers
        this.numbers.clear();


    }

    public void storeNumber(final String number) {
        LOGGER.info("Adding {} to pot" , number);
        numbers.add(number);
        LOGGER.info("Pot already have {}", numbers);
    }
}
