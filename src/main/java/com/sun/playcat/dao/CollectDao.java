package com.sun.playcat.dao;

import com.sun.playcat.domain.Collect;
import com.sun.playcat.domain.CollectList;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * Created by sunlin on 2017/7/27.
 */
@MapperScan
public interface CollectDao {
    public int insert(Collect collect);
    public List<Collect> searchGame(CollectList collectList);
    public int searchCount(CollectList collectList);
    public void del(Collect collect);
    public Collect get(@Param("id") int id);
}
