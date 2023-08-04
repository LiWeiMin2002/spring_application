package com.yc.biz;

import org.springframework.stereotype.Service;

/**
 * @program: spring_application
 * @description:
 * @author: lwm
 * @create: 2023-08-03 10:34
 */
@Service
public class AccountBizImpl implements AccountBiz {
    @Override
    public void addAccount(int accountId,double money){
        System.out.println("添加账户："+accountId);
    }
}
