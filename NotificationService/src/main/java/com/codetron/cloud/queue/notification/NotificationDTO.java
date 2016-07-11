/******************************************************************************
 * Copyright (C) 2005 - 2016 Ventura24 S.L.U.                                *
 * *
 * Copyright and license details are included in Ventura24 license file.     *
 ******************************************************************************/
package com.codetron.cloud.queue.notification;

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
public class NotificationDTO implements Serializable{

    private Long id;
    private String email;
    private String number;
    private BigDecimal prize;
}
