import com.yc.bean.Account;
import com.yc.biz.AccountBiz;
import com.yc.configs.Config;
import com.yc.configs.DataSourceConfig;
import junit.framework.TestCase;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @program: spring_application
 * @description:
 * @author: lwm
 * @create: 2023-08-06 15:01
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Config.class, DataSourceConfig.class})
@Log4j2
public class AccountBizImplTest extends TestCase {
    @Autowired
    private AccountBiz accountBiz;

    @Test
    public void findAccount() {
        Account a = accountBiz.findAccount(31);
        Assert.assertNotNull(a);
        log.info(a);
    }

    @Test
    public void openAccount() {
        Account a = accountBiz.openAccount(100);
        Assert.assertNotNull(a);
        log.info(a);
    }

    @Test
    public void deposite() {
        Account a = accountBiz.deposite(33, 1);
        Assert.assertNotNull(a);
        log.info(a);
    }

    @Test
    public void withdraw() {
        Account a = accountBiz.withdraw(33, 10000);
        Assert.assertNotNull(a);
        log.info(a);
    }

    @Test
    public void transfer() {
        Account a = accountBiz.transfer(33, 2, 32);
        Assert.assertNotNull(a);
        log.info(a);
    }
}
