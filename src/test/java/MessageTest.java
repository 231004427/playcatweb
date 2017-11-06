import com.sun.playcat.dao.MessageDao;
import com.sun.playcat.domain.Message;
import com.sun.playcat.domain.MessageList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by sunlin on 2017/8/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class MessageTest {
    @Resource
    private MessageDao messageService;
    @Test
    public void insert(){
        String data="12345687890";
        Message message=new Message();
        message.setFrom_user(10013);
        message.setTo_user(10012);
        message.setVesion(1);
        message.setType(1);//文本
        message.setLength(10);
        message.setData(data);
        message.setStatus(1);
        message.setCreate_time(new Date());
        messageService.insert(message);
    }
    @Test
    public void search(){
        MessageList messageList=new MessageList();
        messageList.setFrom_user(10012);
        messageList.setTo_user(10012);
        messageList.setPageNum(10);
        messageList.setStart(0);
        messageList.setList(messageService.search(messageList));
        messageList.setCount(messageService.searchCount(messageList));

        assert (messageList.getList().size()>0);

    }
    @Test
    public void updateStatus(){
        Message message=new Message();
        message.setFrom_user(10012);
        message.setTo_user(10013);
        message.setId(18);
        message.setStatus(4);
        messageService.updateStatus(message);
    }
}
