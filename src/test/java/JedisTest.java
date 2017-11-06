import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by sunlin on 2017/10/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class JedisTest {
    @Autowired
    private JedisPool jedisPool;//注入JedisPool
    @Test
    public void set(){
        Date time=new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(time);
        calendar.add(calendar.DATE,7);

        //获取ShardedJedis对象
        Jedis jedis = jedisPool.getResource();
        //存入键值对
        jedis.setex("key",604800,"hello");
        //回收ShardedJedis实例
        jedis.close();
    }

}
