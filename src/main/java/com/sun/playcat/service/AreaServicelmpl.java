package com.sun.playcat.service;

import com.sun.playcat.dao.AreaDao;
import com.sun.playcat.domain.Area;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by sunlin on 2017/8/13.
 */
@Service("areaService")
@Transactional
public class AreaServicelmpl implements AreaDao {
    @Resource
    private AreaDao areaDao;
    @Override
    public int searchCount(@Param("parent_id") int parent_id) {
        return areaDao.searchCount(parent_id);
    }

    @Override
    public List<Area> search(@Param("parent_id") int parent_id, @Param("start") int start, @Param("pageNum") int pageNum) {
        return areaDao.search(parent_id,start,pageNum);
    }
}
