package com.sun.playcat.dao;

import com.sun.playcat.domain.Comment;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * Created by sunlin on 2017/7/25.
 */
@MapperScan
public interface CommentDao {
    public int insert(Comment comment);
    public int searchCount(@Param("sid") int sid,
                           @Param("userId") int userId,
                           @Param("type") int type,
                           @Param("status") int status);
    public List<Comment> search(
            @Param("sid") int sid,
            @Param("userId") int userId,
            @Param("type") int type,
            @Param("status") int status,
            @Param("start") int start,
            @Param("pageNum") int pageNum);
}
