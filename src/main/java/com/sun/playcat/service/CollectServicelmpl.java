package com.sun.playcat.service;

import com.sun.playcat.dao.CollectDao;
import com.sun.playcat.dao.GameDao;
import com.sun.playcat.domain.Collect;
import com.sun.playcat.domain.CollectList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by sunlin on 2017/7/27.
 */
@Service("collectService")
@Transactional
public class CollectServicelmpl implements CollectDao {
    @Resource
    private GameDao gameDao;
    @Resource
    private CollectDao collectDao;

    @Override
    public int insert(Collect collect) {
        collectDao.del(collect);
        gameDao.upOkNum(collect.getSid());
        return collectDao.insert(collect);
    }

    @Override
    public List<Collect> searchGame(CollectList collectList) {
        return collectDao.searchGame(collectList);
    }

    @Override
    public int searchCount(CollectList collectList) {
        return collectDao.searchCount(collectList);
    }

    @Override
    public void del(Collect collect) {
        collectDao.del(collect);
        gameDao.downOkNum(collect.getSid());
    }

    @Override
    public Collect get(@Param("id") int id) {
        return collectDao.get(id);
    }
}
