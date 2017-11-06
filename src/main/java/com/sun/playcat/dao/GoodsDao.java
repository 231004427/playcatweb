package com.sun.playcat.dao;

import com.sun.playcat.domain.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by sunlin on 2017/8/6.
 */
public interface GoodsDao {
    public int searchCount(@Param("type") int type);
    public List<Goods> search(
            @Param("start") int start,
            @Param("pageNum") int pageNum,
            @Param("type") int type);
    public Goods selectPrice(@Param("id") int id);
}
