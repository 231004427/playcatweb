import com.sun.playcat.common.MD5;
import com.sun.playcat.dao.TokenDao;
import com.sun.playcat.domain.Token;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by sunlin on 2017/10/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TokenHelpTest {
    @Resource
    private TokenDao tokenService;
    @Test
    public void insert(){
        Date time=new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(time);
        calendar.add(calendar.DATE,7);
        Token token=new Token();
        token.setUser_id(10012);
        String data=String.valueOf(calendar.getTimeInMillis());
        //String dataEncode=MD5.getMD5(data);

        token.setToken_data(data);
        token.setCreate_time(time);

        token.setExpire_time(calendar.getTime());
        tokenService.insert(token);
    }
    @Test
    public void get(){
        Token token=tokenService.get(10012);
        assert (token!=null);
    }
    @Test
    public void check(){
        String tokenData=tokenService.getStr(10012);
        tokenData=tokenService.getStr(10012);
        assert (tokenData!=null);
    }
}
