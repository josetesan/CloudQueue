package com.codetron.cloud.queue.customer;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by josetesan on 10/07/16.
 */
@Entity
@Table(name = "CUSTOMERS")
@Getter
@ToString
@Builder
public class Customer implements Serializable{

    @Id
    private Long id;

    private String name;

    private String surname;

    private String email;

}
