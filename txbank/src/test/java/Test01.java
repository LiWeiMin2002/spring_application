import com.yc.biz.AccountBiz;
import com.yc.configs.Config;
import junit.framework.TestCase;
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
 * @create: 2023-08-03 10:44
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class Test01 extends TestCase {
    @Autowired
    private AccountBiz accountBiz;

    @Test
    public void testAddAccount() {
        accountBiz.addAccount(1, 999);
    }


    @Test
    public void testAdd() {
        int x = 3, y = 4;
        Assert.assertEquals(7, x + y);
    }
}
