import com.yc.bean.OpRecord;
import com.yc.bean.OpType;
import com.yc.configs.Config;
import com.yc.configs.DataSourceConfig;
import com.yc.dao.OpRecordDao;
import junit.framework.TestCase;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @program: spring_application
 * @description:
 * @author: lwm
 * @create: 2023-08-05 09:23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Config.class, DataSourceConfig.class})
@Log4j2
public class OpRecordJdbcTemplateImplTest extends TestCase {
    @Autowired
    private OpRecordDao opRecordDao;

    @Test
    public void insertOpRecord() {
        OpRecord opRecord = new OpRecord();
        opRecord.setAccountId(31);
        opRecord.setOpmoney(5);
        opRecord.setOpType(OpType.DEPOSITE);
        this.opRecordDao.insertOpRecord(opRecord);
    }

    @Test
    public void findOpRecord1() {
        List<OpRecord>list=this.opRecordDao.findOpRecord(19);
        System.out.println(list);
    }

    @Test
    public void findOpRecord2() {
        List<OpRecord>list=this.opRecordDao.findOpRecord(19,"DEPOSITE");
        System.out.println(list);
    }

    @Test
    public void findOpRecord3() {

    }
}
