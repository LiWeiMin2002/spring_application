package com.yc.biz;

import com.yc.bean.Account;
import com.yc.bean.OpRecord;
import com.yc.bean.OpType;
import com.yc.dao.AccountDao;
import com.yc.dao.OpRecordDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: spring_application
 * @description:
 * @author: lwm
 * @create: 2023-08-03 10:34
 */
@Service
@Log4j2
@Transactional
public class AccountBizImpl implements AccountBiz {
    @Autowired
    private AccountDao accountDao;

    @Autowired
    private OpRecordDao opRecordDao;


    @Override
    public Account openAccount(double money) {
        int accountId = this.accountDao.insert(money);

        OpRecord opRecord = new OpRecord();
        opRecord.setAccountId(accountId);
        opRecord.setOpmoney(money);
        opRecord.setOpType(OpType.DEPOSITE);
        this.opRecordDao.insertOpRecord(opRecord);

        Account a = new Account();
        a.setAccountId(accountId);
        a.setMoney(money);
        return a;
    }

    @Override
    public Account deposite(int accountId, double money) {
       return this.deposite(accountId,money,null);
    }

    @Override
    public Account deposite(int accountId, double money, Integer transferId) {
        Account a = null;
        try {
            a = this.accountDao.findById(accountId);
        } catch (RuntimeException re) {
            log.error(re.getMessage());
            throw new RuntimeException("查无此账户" + accountId + ",无法完成存款操作");
        }
        //存款时金额累加
        a.setMoney(a.getMoney() + money);
        this.accountDao.update(accountId, a.getMoney());

        OpRecord opRecord = new OpRecord();
        opRecord.setAccountId(accountId);
        opRecord.setOpmoney(money);
        if (transferId != null) {
            opRecord.setOpType(OpType.TRANSFER);
            opRecord.setOpmoney(transferId);
        } else {
            opRecord.setOpType(OpType.DEPOSITE);
        }
        this.opRecordDao.insertOpRecord(opRecord);
        return a;
    }

    @Override
    public Account withdraw(int accountId, double money) {
        return this.withdraw(accountId,money,null);
    }

    @Override
    public Account withdraw(int accountId, double money, Integer transferId) {
        Account a=null;
        try{
            a=this.accountDao.findById(accountId);
        }catch (RuntimeException re){
            log.error(re.getMessage());
            throw new RuntimeException("查无此账户"+accountId+"，无法完成存款操作");
        }
        a.setMoney(a.getMoney()-money);
        OpRecord opRecord=new OpRecord();
        opRecord.setAccountId(accountId);
        opRecord.setOpmoney(money);
        if(transferId!=null){
            opRecord.setOpType(OpType.TRANSFER);
            opRecord.setTransferId(transferId);
        }else{
            opRecord.setOpType(OpType.WITHDRAW);
        }
        //
        this.opRecordDao.insertOpRecord(opRecord);
        this.accountDao.update(accountId,a.getMoney());
            return a;
    }

    @Override
    public Account transfer(int accountId, double money, int toAccountId) {
        this.deposite(toAccountId,money,toAccountId);

        Account a=this.withdraw(accountId,money,toAccountId);
        return a;
    }

    @Override
    public Account findAccount(int accountId) {
        return this.accountDao.findById(accountId);
    }

    @Override
    public void addAccount(int accountId, double money) {
        System.out.println("添加账户：" + accountId);
    }
}
