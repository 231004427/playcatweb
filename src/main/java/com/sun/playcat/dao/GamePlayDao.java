package com.sun.playcat.dao;

/**
 * Created by sunlin on 2017/7/29.
 */

import com.sun.playcat.domain.GameList;
import com.sun.playcat.domain.GamePlay;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@MapperScan
public interface GamePlayDao {
    public int insert(GamePlay gamePlay);
    public void upPoints(@Param("user_id") int user_id,
                             @Param("game_id") int game_id,
                             @Param("points") int points);
    public void downPoints(@Param("user_id") int user_id,
                         @Param("game_id") int game_id,
                         @Param("points") int points);
    public List<GamePlay> getTopList(
            @Param("start") int start,
            @Param("pageNum") int pageNum,
            @Param("game_id") int game_id);
    public int getTopListCount(@Param("game_id") int game_id);
    public List<GamePlay> getUserList(
            @Param("start") int start,
            @Param("pageNum") int pageNum,
            @Param("user_id") int user_id);
    public int getUserListCount(@Param("user_id") int user_id);
}
