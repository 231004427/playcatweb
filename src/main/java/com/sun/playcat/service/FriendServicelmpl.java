package com.sun.playcat.service;

import com.sun.playcat.dao.FriendDao;
import com.sun.playcat.dao.MessageDao;
import com.sun.playcat.domain.Friend;
import com.sun.playcat.domain.FriendList;
import com.sun.playcat.domain.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by sunlin on 2017/8/19.
 */
@Service("friendService")
@Transactional
public class FriendServicelmpl implements FriendDao {
    @Resource
    private FriendDao friendDao;
    @Resource
    private MessageDao messageDao;

    @Override
    public void insert(Friend friend) {
        friendDao.insert(friend);
    }

    @Override
    public int searchCount(FriendList friendList) {
        return friendDao.searchCount(friendList);
    }

    @Override
    public int insertCount(Friend friend) {
        return friendDao.insertCount(friend);
    }

    @Override
    public List<Friend> search(FriendList friendList) {
        return friendDao.search(friendList);
    }

    @Override
    public int noReadCount(@Param("userId") int userId) {
        return friendDao.noReadCount(userId);
    }
    @Override
    public List<Friend> messageList(FriendList friendList) {
        return friendDao.messageList(friendList);
    }
    @Override
    public int messageListCount(FriendList friendList) {
        return friendDao.messageListCount(friendList);
    }

    @Override
    public void updateNoRead(Friend friend) {
        friendDao.updateNoRead(friend);
    }

    @Override
    public void updateRead(Friend friend) {
        friendDao.updateRead(friend);
    }
    @Override
    public void setNoRead(Friend friend) {
        friendDao.setNoRead(friend);
    }
    @Override
    public void setAllRead(Friend friend){
        friend.setNo_read(0);
        //设置可读为0
        friendDao.setNoRead(friend);
        //消息重置为已读
        Message message=new Message();
        message.setTo_user(friend.getUser_id());
        message.setFrom_user(friend.getFriend_id());
        messageDao.updateRead(message);
    }

}
