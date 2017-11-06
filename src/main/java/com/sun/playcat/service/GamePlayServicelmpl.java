package com.sun.playcat.service;

import com.sun.playcat.dao.GamePlayDao;
import com.sun.playcat.domain.GamePlay;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by sunlin on 2017/7/29.
 */
@Service("gamePlayService")
@Transactional
public class GamePlayServicelmpl implements GamePlayDao {

    @Resource
    private GamePlayDao gamePlayDao;

    @Override
    public int insert(GamePlay gamePlay) {
        return gamePlayDao.insert(gamePlay);
    }

    @Override
    public void upPoints(int user_id,int game_id,int points) {
        gamePlayDao.upPoints(user_id,game_id,points);
    }

    @Override
    public void downPoints(int user_id,int game_id,int points) {
        gamePlayDao.downPoints(user_id,game_id,points);
    }
    @Override
    public List<GamePlay> getTopList(int start,int pageNum,int game_id) {
        return gamePlayDao.getTopList(start,pageNum,game_id);
    }

    @Override
    public int getTopListCount(int game_id) {
        return gamePlayDao.getTopListCount(game_id);
    }

    @Override
    public List<GamePlay> getUserList(@Param("start") int start, @Param("pageNum") int pageNum, @Param("user_id") int user_id) {
        return gamePlayDao.getUserList(start,pageNum,user_id);
    }

    @Override
    public int getUserListCount(@Param("user_id") int user_id) {
        return gamePlayDao.getUserListCount(user_id);
    }
}
