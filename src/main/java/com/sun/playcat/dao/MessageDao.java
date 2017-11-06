package com.sun.playcat.dao;

import com.sun.playcat.domain.Message;
import com.sun.playcat.domain.MessageList;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * Created by sunlin on 2017/8/23.
 */
@MapperScan
public interface MessageDao {
    public void insert(Message message);
    public List<Message> search(MessageList messageList);
    public int searchCount(MessageList messageList);
    public void updateStatus(Message message);
    public void updateStatusList(List<Message> list);
    public void updateRead(Message message);
}
