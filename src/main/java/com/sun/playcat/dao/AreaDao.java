package com.sun.playcat.dao;

import com.sun.playcat.domain.Area;
import com.sun.playcat.domain.AreaList;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * Created by sunlin on 2017/8/13.
 */
@MapperScan
public interface AreaDao {
    public int searchCount(@Param("parent_id") int parent_id);
    public List<Area> search(
            @Param("parent_id") int parent_id,
            @Param("start") int start,
            @Param("pageNum") int pageNum);
}
