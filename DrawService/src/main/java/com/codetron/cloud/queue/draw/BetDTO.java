package com.codetron.cloud.queue.draw;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by josete on 11/07/2016.
 */

@Getter
@ToString
@NoArgsConstructor
public class BetDTO implements Serializable{

    private Long betId;
    private Long userId;
    private String number;
    private LocalDate dateCreated;

    public BetDTO(Long userId, String number) {
        this.betId = -1L;
        this.userId = userId;
        this.number = number;
        this.dateCreated = LocalDate.now();
    }
}
