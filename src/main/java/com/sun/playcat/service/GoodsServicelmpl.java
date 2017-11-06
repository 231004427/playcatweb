package com.sun.playcat.service;

import com.sun.playcat.dao.GoodsDao;
import com.sun.playcat.domain.Goods;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by sunlin on 2017/8/6.
 */
@Service("goodsService")
@Transactional
public class GoodsServicelmpl implements GoodsDao {
    @Resource
    private GoodsDao goodsDao;
    @Override
    public int searchCount(int type) {
        return goodsDao.searchCount(type);
    }
    @Override
    public List<Goods> search(int start,int pageNum,int type) {
        return goodsDao.search(start,pageNum,type);
    }

    @Override
    public Goods selectPrice(@Param("id") int id) {
        return goodsDao.selectPrice(id);
    }
}
