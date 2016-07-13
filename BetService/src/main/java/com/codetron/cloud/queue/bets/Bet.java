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
import java.time.LocalDate;

/**
 * Created by josetesan on 10/07/16.
 */
@Entity
@Table(name="BETS",indexes = {@Index(columnList = "userId"),@Index(columnList = "number")})
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bet implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long betId;
    private Long userId;
    private String number;
    private LocalDate dateCreated;


}
