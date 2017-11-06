package com.sun.playcat.service;

import com.sun.playcat.dao.GameDao;
import com.sun.playcat.domain.Game;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by sunlin on 2017/7/16.
 */
@Service("gameService")
@Transactional
public class GameServicelmpl implements GameDao {
    @Resource
    private GameDao gameDao;

    @Override
    public int searchCount(int isgood,int type) {
        return gameDao.searchCount(isgood,type);
    }

    @Override
    public List<Game> search(int start,int pageNum,int isgood,int type) {
        return gameDao.search(start,pageNum,isgood,type);
    }
    @Override
    public Game getGame(int id) {
        return gameDao.getGame(id);
    }

    @Override
    public void upOkNum(@Param("id") int id) {
        gameDao.upOkNum(id);
    }

    @Override
    public void downOkNum(@Param("id") int id) {
        gameDao.downOkNum(id);
    }

    @Override
    public Game getRule(@Param("id") int id) {
        return gameDao.getRule(id);
    }

}
