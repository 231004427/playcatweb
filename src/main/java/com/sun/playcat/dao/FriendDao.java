package com.sun.playcat.dao;

import com.sun.playcat.domain.Friend;
import com.sun.playcat.domain.FriendList;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * Created by sunlin on 2017/8/19.
 */
@MapperScan
public interface FriendDao {
    public void insert(Friend friend);
    public Friend get(Friend friend);
    public void delete(Friend friend);
    public int searchCount(FriendList friendList);
    public int insertCount(Friend friend);
    public List<Friend> search(FriendList friendList);
    public int noReadCount(@Param("user_id") int userId);
    public List<Friend> messageList(FriendList friendList);
    public int messageListCount(FriendList friendList);
    public void updateNoRead(Friend friend);
    public void updateRead(Friend friend);
    public void setNoRead(Friend friend);
    public void setAllRead(Friend friend);
}
