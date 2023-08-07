package com.yc.dao;

import com.yc.bean.OpRecord;
import com.yc.bean.OpType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * @program: spring_application
 * @description:
 * @author: lwm
 * @create: 2023-08-04 18:44
 */
@Repository
public class OpRecordJdbcTemplateImpl implements OpRecordDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void init(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void insertOpRecord(OpRecord opRecord) {
        String sql = "insert into oprecord(accountId,opmoney,optime,optype,transferId) values(?,?,now(),?,?)";
        this.jdbcTemplate.update(
                sql, opRecord.getAccountId(), opRecord.getOpmoney(),
                opRecord.getOpType().getKey(),
                opRecord.getTransferId()
        );
    }

    @Override
    public List<OpRecord> findOpRecord(int accountId) {
        List<OpRecord> list = this.jdbcTemplate.query(
                "select * from oprecord where accountId=? order by optime desc",
                (resultSet, rowNum) -> {
                    OpRecord opRecord = new OpRecord();
                    opRecord.setId(resultSet.getInt(1));
                    opRecord.setAccountId(resultSet.getInt(2));
                    opRecord.setOpmoney(resultSet.getDouble(3));
                    opRecord.setOptime(resultSet.getString(4));

                    String optype = resultSet.getString(5);
                    if (optype.equalsIgnoreCase("withdraw")) {
                        opRecord.setOpType(OpType.WITHDRAW);
                    } else if (optype.equalsIgnoreCase("deposite")) {
                        opRecord.setOpType(OpType.DEPOSITE);
                    } else {
                        opRecord.setOpType(OpType.TRANSFER);
                    }
                    opRecord.setTransferId(resultSet.getInt(6));
                    return opRecord;
                }, accountId
        );
        return list;
    }

    @Override
    public List<OpRecord> findOpRecord(int accountId, String opType) {
        List<OpRecord> list = this.jdbcTemplate.query(
                "select * from oprecord where accountId=? and opType=? order by optime desc",
                (resultSet, rowNum) -> {
                    OpRecord opRecord = new OpRecord();
                    opRecord.setId(resultSet.getInt(1));
                    opRecord.setAccountId(resultSet.getInt(2));
                    opRecord.setOpmoney(resultSet.getDouble(3));
                    opRecord.setOptime(resultSet.getString(4));
                    String optype = resultSet.getString(5);
                    if (optype.equalsIgnoreCase("withdraw")) {
                        opRecord.setOpType(OpType.WITHDRAW);
                    } else if (optype.equalsIgnoreCase("deposite")) {
                        opRecord.setOpType(OpType.DEPOSITE);
                    } else {
                        opRecord.setOpType(OpType.TRANSFER);
                    }
                    opRecord.setTransferId(resultSet.getInt(6));
                    return opRecord;
                }, accountId, opType
        );
        return list;
    }

    @Override
    public List<OpRecord> findOpRecord(OpRecord opRecord) {
        return null;
    }
}
