package com.yc.dao;

import com.yc.bean.OpRecord;

import java.util.List;

/**
 * @program: spring_application
 * @description:
 * @author: lwm
 * @create: 2023-08-03 20:44
 */
public interface OpRecordDao {
    /**
     * @param opRecord
     */
    public void insertOpRecord(OpRecord opRecord);

    /**
     * @param accountId
     * @return
     */
    public List<OpRecord> findOpRecord(int accountId);

    /**
     * @param accountId
     * @param opType
     * @return
     */
    public List<OpRecord> findOpRecord(int accountId, String opType);

    /**
     * @param opRecord
     * @return
     */
    public List<OpRecord> findOpRecord(OpRecord opRecord);

}
