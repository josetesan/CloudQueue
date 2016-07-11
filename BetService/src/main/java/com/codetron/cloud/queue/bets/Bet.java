package com.codetron.cloud.queue.bets;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by josetesan on 10/07/16.
 */
@Entity
@Table(name="BETS",indexes = {@Index(columnList = "userId")})
@Getter
@ToString
@Builder
public class Bet implements Serializable{

    @Id
    private Long id;

    private Long userId;

    private String number;

    private LocalDate dateCreated;


}
