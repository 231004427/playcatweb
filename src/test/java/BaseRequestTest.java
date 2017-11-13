import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.sun.playcat.common.GsonBinder;
import com.sun.playcat.domain.BaseRequest;
import com.sun.playcat.domain.Token;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Date;

/**
 * Created by sunlin on 2017/11/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class BaseRequestTest {
    @Test
    public void testJson(){
        String jsonStr="{\"actionType\":41,\"appid\":1,\"data\":\"{\\\"type\\\":1}\",\"dateTime\":\"2017-11-06 12:00:00\",\"token\":\"\",\"userid\":0,\"vesion\":0}";
        String jsonStr2="{\"token\":\"1509365498165\",\"dateTime\":\"Nov 6, 2017 3:50:04 PM\",\"data\":\"{\\\"type\\\":1}\",\"appid\":1,\"actionType\":41,\"userid\":10012,\"vesion\":0}";

        Token token=new Token();
        token.setPhone("123123");
        token.setPassword("123123");
        //token.setUser_id(1);
        token.setExpire_time(new Date());
        //token.setCreate_time(new Date());
        //token.setToken_data("");
        Gson gson = new GsonBuilder()
                //序列化null
                .serializeNulls()
                // 设置日期时间格式，另有2个重载方法
                // 在序列化和反序化时均生效
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                // 禁此序列化内部类
                //.disableInnerClassSerialization()
                //生成不可执行的Json（多了 )]}' 这4个字符）
                //.generateNonExecutableJson()
                //禁止转义html标签
                .disableHtmlEscaping()
                //格式化输出
                .setPrettyPrinting()
                .create();
        String temp= GsonBinder.toJsonStr(token);

        Token baseResult=GsonBinder.toObj(temp,Token.class);
    }
}

