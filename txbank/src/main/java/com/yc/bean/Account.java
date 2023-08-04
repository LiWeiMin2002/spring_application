package com.yc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @program: spring_application
 * @description:
 * @author: lwm
 * @create: 2023-08-03 20:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account implements Serializable {
    private int accountId;
    private double money;
}
