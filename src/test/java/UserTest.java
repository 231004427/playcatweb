import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.playcat.common.TokenHelp;
import com.sun.playcat.dao.TokenDao;
import com.sun.playcat.dao.UserDao;
import com.sun.playcat.domain.Local;
import com.sun.playcat.domain.Token;
import com.sun.playcat.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class UserTest extends AbstractJUnit4SpringContextTests{

    //注入类
    @Resource
    private UserDao userService;
    @Resource
    private TokenDao tokenService;
    @Test
    public void login(){

        //生成Token
        Token token= TokenHelp.getObj(10014);
        token.setUser_id(10014);
        tokenService.delete(10014);
        tokenService.insert(token);
        //更新Token
        userService.updateToken(10014,token.getToken_data());

        String tokenStr=tokenService.getStr(10014);

        token= TokenHelp.getObj(10014);
        token.setUser_id(10014);
        tokenService.delete(10014);
        tokenService.insert(token);

        //更新Token
        userService.updateToken(10014,token.getToken_data());

        tokenStr=tokenService.getStr(10014);

        long timeExt=Long.parseLong(tokenStr.split("&")[1]);

    }
    @Test
    public void updateToken(){
        userService.updateToken(10012,"123");
    }
    @Test
    public void getMoney()
    {
        User user=new User();
        user=userService.getMoney(10012);
        assert (user.getZhuan()>0);
    }
    @Test
    public void updateZhuan()
    {
        userService.updateZuan(10012,60);
    }
    @Test
    public void updateGold()
    {
        userService.updateGold(10012,120);
    }
    @Test
    public void update()
    {
        User user=new User();
        user.setId(10012);
        user.setQq("231004427");
        user.setPhone2("135888934342");
        user.setAddress("浙江 杭州 江干区 九堡镇 东方电子商务园区 8幢 100室");

        userService.updateAddress(user.getAddress(),user.getId());
        userService.updatePhone2(user.getPhone2(),user.getId());
        userService.updateQQ(user.getQq(),user.getId());

    }
    @Test
    public void count()
    {
        User user=new User();
        user.setPhone("13588045452");
        int count=userService.count(user);
        assert(count>0);
        logger.debug("count:"+count);
    }
    @Test
    public void loginUser(){
        User user=new User();
        user.setPhone("135880454521");
        user.setPassword("E10ADC3949BA59ABBE56E057F20F883E");
        user=userService.login(user);
        assert (user==null);
    }
    @Test
    public void insertUser(){

        Local local=new Local();
        local.setCountry("中国");
        local.setProvince("浙江省");
        local.setCity("杭州");
        local.setPolitical("江干区");
        local.setRoute("九堡镇");
        local.setStreet("九盛路");
        local.setUpdateTime(new Date());
        local.setLatitude(0.233453);
        local.setLongitude(0.433231);
        local.setStatus(1);

        Date date=new Date();
        User user=new User();
        user.setLocal(local);
        user.setCity("杭州");
        user.setCount(0);
        user.setCreate(date);
        user.setGold(0);
        user.setLevel(1);
        user.setName("1");
        user.setPassword("1");
        user.setPhone("123456789");
        user.setPhoto("");
        user.setSex(1);
        user.setStatus(1);
        user.setUpdate(date);
        user.setZhuan(0);
        user.setId(-1);
        userService.insertUser(user);
        logger.error("userid:"+user.getId());
    }
    @Test
    public void getUser(){
        User user=new User();
        user.setId(1111);
        user=userService.getUser(user);

        assert (user==null);

    }
}

