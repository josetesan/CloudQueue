/******************************************************************************
 * Copyright (C) 2005 - 2016 ACME S.L.U.                                *
 * *
 * Copyright and license details are included in ACME license file.     *
 ******************************************************************************/
package com.codetron.cloud.queue.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *****************************************************************************
 *  Copyright (C) 2005 - 2016 ACME S.L.U.                                *
 *                                                                            *
 *  Copyright and license details are included in ACME license file.     *
 ******************************************************************************
 * Created by jsanc on 11/07/16.
 */
@Getter
@ToString
@AllArgsConstructor
@Builder
public class UserDTO implements Serializable{

    private Long id;
    private String number;
    private BigDecimal prize;

}
