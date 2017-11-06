package com.sun.playcat.dao;

import com.sun.playcat.domain.Game;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * Created by sunlin on 2017/7/16.
 */
@MapperScan
public interface GameDao {
    public int searchCount(@Param("isgood") int isgood,
                           @Param("type") int type);
    public List<Game> search(
            @Param("start") int start,
            @Param("pageNum") int pageNum,
            @Param("isgood") int isgood,
            @Param("type") int type);
    public Game getGame(@Param("id") int id);
    public void upOkNum(@Param("id") int id);
    public void downOkNum(@Param("id") int id);
    public Game getRule(@Param("id") int id);
}
