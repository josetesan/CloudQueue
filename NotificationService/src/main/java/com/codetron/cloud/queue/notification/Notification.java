package com.codetron.cloud.queue.notification;

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
@Table(name="NOTIFICATIONS",indexes = {@Index(columnList = "email")})
@Getter
@ToString
@Builder
public class Notification implements Serializable{

    @Id
    private Long id;

    private String email;

    private String number;

    private BigDecimal prize;

    private LocalDate createDate;



}
