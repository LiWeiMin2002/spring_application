package com.yc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @program: spring_application
 * @description:
 * @author: lwm
 * @create: 2023-08-03 20:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OpRecord {
    private int id;
    private int accountId;
    private double opmoney;
    private String optime;
    private OpType opType;
    private Integer transferId;
}
