/******************************************************************************
 * Copyright (C) 2005 - 2016 Ventura24 S.L.U.                                *
 * *
 * Copyright and license details are included in Ventura24 license file.     *
 ******************************************************************************/
package com.codetron.cloud.queue.web;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 *****************************************************************************
 *  Copyright (C) 2005 - 2016 Ventura24 S.L.U.                                *
 *                                                                            *
 *  Copyright and license details are included in Ventura24 license file.     *
 ******************************************************************************
 * Created by jsanc on 11/07/16.
 */
@Getter
@ToString
@AllArgsConstructor
public class WinnerDTO implements Serializable{

    private Long userId;
    private String number;
    private BigDecimal prize;
}
