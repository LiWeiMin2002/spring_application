package com.yc.dao;

import com.yc.bean.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * @program: spring_application
 * @description:
 * @author: lwm
 * @create: 2023-08-04 15:59
 */
@Repository
public class AccountDaoJdbcTemplateImpl implements AccountDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void init(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public int insert(double money) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into accounts(balance) values(?)", new String[]{"accountId"});
            ps.setString(1, money + "");
            return ps;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public void update(int accountId, double money) {
        this.jdbcTemplate.update(
                "update accounts set balance=balance+? where accountId=?",
                money + "", accountId + ""
        );
    }

    @Override
    public void delete(int accountId) {
        this.jdbcTemplate.update(
                "delete from accounts where accountId=?",
                Integer.valueOf(accountId)
        );
    }

    @Override
    public int findCount() {
        int rowCount = this.jdbcTemplate.queryForObject("select count(*) from accounts", Integer.class);
        return rowCount;
    }

    @Override
    public List<Account> findAll() {
        List<Account> list = jdbcTemplate.query(
                "select * from accounts",
                (resultSet, rowNum) -> {
                    Account account = new Account();
                    account.setAccountId(resultSet.getInt(1));
                    account.setMoney(resultSet.getDouble(2));
                    return account;
                }
        );
        return list;
    }

    @Override
    public Account findById(int accountId) {
        Account account = jdbcTemplate.queryForObject(
                "select * from accounts where accountId=?",
                (resultSet, rowNum) -> {
                    Account a = new Account();
                    a.setAccountId(resultSet.getInt(1));
                    a.setMoney(resultSet.getDouble(2));
                    return a;
                }, accountId
        );
        return account;
    }
}
