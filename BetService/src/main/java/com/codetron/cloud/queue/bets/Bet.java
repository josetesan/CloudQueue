package com.codetron.cloud.queue.bets;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@NoArgsConstructor
@AllArgsConstructor
public class Bet implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    private Long userId;

    private String number;
    @JsonIgnore
    private LocalDate dateCreated;


}
