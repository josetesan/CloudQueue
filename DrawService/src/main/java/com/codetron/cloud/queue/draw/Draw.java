package com.codetron.cloud.queue.draw;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by josetesan on 10/07/16.
 */
@Table(name="DRAWS")
@Entity
@Getter
@ToString
@Builder
public class Draw implements Serializable {

    @Id
    private Long id;

    private String winningNumber;

    private LocalDate dateCreated;
}
