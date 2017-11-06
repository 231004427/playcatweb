package com.sun.playcat.service;

import com.sun.playcat.dao.CommentDao;
import com.sun.playcat.domain.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by sunlin on 2017/7/25.
 */
@Service("commentService")
@Transactional
public class CommentServiceImpl implements CommentDao {
    @Resource
    private CommentDao commentDao;

    @Override
    public int insert(Comment comment) {
        return commentDao.insert(comment);
    }

    @Override
    public int searchCount(@Param("sid") int sid, @Param("userId") int userId, @Param("type") int type, @Param("status") int status) {
        return commentDao.searchCount(sid,userId,type,status);
    }

    @Override
    public List<Comment> search(@Param("sid") int sid, @Param("userId") int userId, @Param("type") int type, @Param("status") int status, @Param("start") int start, @Param("pageNum") int pageNum) {
        return commentDao.search(sid,userId,type,status,start,pageNum);
    }
}
