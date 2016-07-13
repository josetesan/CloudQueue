package com.codetron.cloud.queue.bets;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by josetesan on 14/07/16.
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WinnerDTO implements Serializable {

    private Long id;
    private String number;
    private BigDecimal prize;

    public WinnerDTO(Bet bet) {
        this.id = bet.getUserId();
        this.number = bet.getNumber();
        this.prize = BigDecimal.TEN;
    }
}
