import com.yc.bean.Account;
import com.yc.configs.Config;
import com.yc.configs.DataSourceConfig;
import com.yc.dao.AccountDao;
import junit.framework.TestCase;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
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
 * @create: 2023-08-04 16:17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Config.class, DataSourceConfig.class})
@Log4j2
public class AccountDaoJdbcTemplateImplTest extends TestCase {
    @Autowired
    private AccountDao accountDao;

    @Test
    public void insert() {
        int accountID = accountDao.insert(100);
        log.info("新开账户为：" + accountID);
        Assert.assertNotNull(accountID);
    }


    @Test
    public void update() {
        accountDao.update(31, 10);
    }


    @Test
    public void delete() {
        accountDao.delete(32);
    }


    @Test
    public void findCount() {
        int total = accountDao.findCount();
        Assert.assertEquals(3, total);
    }

    @Test
    public void findAll() {
        List<Account> list = this.accountDao.findAll();
        log.info(list);
    }

    @Test
    public void findById() {
        Account account = this.accountDao.findById(31);
        log.info(account);
    }
}
