import com.sun.playcat.dao.FriendDao;
import com.sun.playcat.domain.Friend;
import com.sun.playcat.domain.FriendList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by sunlin on 2017/8/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class FriendTest {
    @Resource
    private FriendDao friendService;
    @Test
    public void get(){
        Friend friend=new Friend();
        friend.setUser_id(10014);
        friend.setFriend_id(10012);
        friend=friendService.get(friend);
        assert (friend!=null);
    }
    @Test
    public void insert(){
        Friend friend=new Friend();
        friend.setFriend_id(1);
        friend.setUser_id(10012);
        friend.setCreate_time(new Date());
        friend.setStatus(1);
        friend.setGroup_id(-1);
        friend.setType(-1);
        friendService.insert(friend);

    }
    @Test
    public void insertCount(){
        Friend friend=new Friend();
        friend.setFriend_id(1);
        friend.setUser_id(10012);
        friend.setCreate_time(new Date());
        friend.setStatus(1);
        friend.setGroup_id(-1);
        friend.setType(-1);

        int count=friendService.insertCount(friend);

    }
    @Test
    public void noReadCount(){
        int count=friendService.noReadCount(10012);
        assert (count>0);
    }
    @Test
    public void search(){
        FriendList friendList=new FriendList();
        friendList.setUser_id(10012);
        friendList.setPageNum(2);
        friendList.setStart(0);

        friendList.setCount(friendService.searchCount(friendList));
        List<Friend> list=friendService.search(friendList);
    }
    @Test
    public void messageList(){

        FriendList friendList=new FriendList();
        friendList.setUser_id(10012);
        friendList.setPageNum(5);
        friendList.setStart(0);
        friendList.setCount(friendService.messageListCount(friendList));
        friendList.setList(friendService.messageList(friendList));
        assert (friendList.getCount()>0);

    }
    @Test
    public void updateRead(){
        Friend friend=new Friend();
        friend.setId(4);
        friend.setNo_read(0);
        friendService.updateRead(friend);
    }
    @Test
    public void setAllRead(){
        Friend friend=new Friend();
        friend.setUser_id(10012);
        friend.setFriend_id(10014);
        friendService.setAllRead(friend);
    }
}
