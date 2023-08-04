package com.yc.dao;

import com.yc.bean.Account;

import java.util.List;

/**
 * @program: spring_application
 * @description:
 * @author: lwm
 * @create: 2023-08-03 20:28
 */
public interface AccountDao {
    /**
     * 添加account账号
     * @param money
     * @return
     */
    public int insert(double money);

    /**
     * 根据账号将money更新
     * @param accountId
     * @param money
     */
    public void update(int accountId,double money);

    /**
     *
     * @param accountId
     */
    public void delete(int accountId);

    /**
     *
     * @return
     */
    public int findCount();

    /**
     *
     * @return
     */
    public List<Account> findAll();

    /**
     *
     * @param accountId
     * @return
     */
    public Account findById(int accountId);
}
