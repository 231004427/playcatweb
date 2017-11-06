package com.sun.playcat.service;

import com.sun.playcat.dao.FriendDao;
import com.sun.playcat.dao.MessageDao;
import com.sun.playcat.domain.Friend;
import com.sun.playcat.domain.Message;
import com.sun.playcat.domain.MessageList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by sunlin on 2017/8/26.
 */
@Service("messageService")
@Transactional
public class MessageServicelmpl implements MessageDao {
    @Resource
    private MessageDao messageDao;
    @Resource
    private FriendDao friendDao;
    @Override
    public void insert(Message message) {
        messageDao.insert(message);

        //更新对方未读数据
        Friend friend=new Friend();
        friend.setUser_id(message.getTo_user());
        friend.setFriend_id(message.getFrom_user());
        //文本消息
        if(message.getType()==1){
            friend.setLast_time(message.getCreate_time());
            friend.setLast_info(message.getData());
            //更新消息列表最后信息
            Friend myInfo=new Friend();
            myInfo.setUser_id(message.getFrom_user());
            myInfo.setFriend_id(message.getTo_user());
            myInfo.setLast_time(message.getCreate_time());
            myInfo.setLast_info(message.getData());
            myInfo.setNo_read(0);
            friendDao.updateNoRead(myInfo);
        }
        //未读数据
        if(message.getStatus()!=4) {friend.setNo_read(1);}else{friend.setNo_read(0);}
        friendDao.updateNoRead(friend);
    }
    @Override
    public List<Message> search(MessageList messageList) {

        List<Message> dataList=messageDao.search(messageList);
        //更新状态为已读

        return dataList;

    }
    @Override
    public int searchCount(MessageList messageList) {
        return messageDao.searchCount(messageList);
    }
    @Override
    public void updateStatus(Message message) {
        if(message.getId()<=0) {
            if (message.getFrom_user() <= 0) {
                throw new RuntimeException("Local getFrom_user should not be empty.");
            }
            if (message.getTo_user() <= 0) {
                throw new RuntimeException("Local getTo_user should not be empty.");
            }
        }
        messageDao.updateStatus(message);
        //更新已读数据
        if(message.getStatus()==4){
            Friend friend=new Friend();
            friend.setUser_id(message.getTo_user());
            friend.setFriend_id(message.getFrom_user());
            friend.setNo_read(1);
            friendDao.updateRead(friend);
        }
    }
    @Override
    public void updateStatusList(List<Message> list) {
        messageDao.updateStatusList(list);
    }

    @Override
    public void updateRead(Message message) {
        messageDao.updateRead(message);
    }
}
