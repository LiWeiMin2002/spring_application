package com.yc.biz;

import com.yc.bean.Account;

/**
 * @program: spring_application
 * @description:
 * @author: lwm
 * @create: 2023-08-03 10:34
 */
public interface AccountBiz {
    public Account openAccount(double money);

    public Account deposite(int accountId, double money);

    public Account deposite(int accountId, double money, Integer transferId);

    public Account withdraw(int accountId, double money);

    public Account withdraw(int accountId, double money, Integer transferId);

    public Account transfer(int accountId, double money, int toAccountId);

    public Account findAccount(int accountId);

    public void addAccount(int accountId, double money);
}
