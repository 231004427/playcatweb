import com.sun.playcat.common.Code;
import com.sun.playcat.dao.PCodeDao;
import com.sun.playcat.domain.PCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by sunlin on 2017/7/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class PCodeTest extends AbstractJUnit4SpringContextTests {

    @Resource
    private PCodeDao pCodeService;
    @Test
    public void checkCode()
    {
        PCode pCode=new PCode();
        pCode.setPhone("13588045452");
        pCode.setCode("5174");
        int count=pCodeService.checkCode(pCode);
        assert (count>0);
    }
    @Test
    public void insertCode()
    {
        PCode pCode=new PCode();
        //生成验证码
        String code= Code.getRandNum(4);
        pCode.setPhone("13588045452");
        pCode.setCode(code);
        pCode.setCreate_time(new Date());
        //保存数据库
        int count =pCodeService.insertCode(pCode);
        assert (count>0);

    }
    @Test
    public void checkPhone()
    {
        PCode pCode=new PCode();
        pCode.setPhone("13588045452");

        int count=pCodeService.checkPhone(pCode);

        assert (count>0);
    }
}
