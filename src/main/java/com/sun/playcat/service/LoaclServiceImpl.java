package com.sun.playcat.service;

import com.sun.playcat.dao.LocalDao;
import com.sun.playcat.domain.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by sunlin on 2017/7/12.
 */
@Service("localService")
@Transactional
public class LoaclServiceImpl implements LocalDao {
    @Resource
    private LocalDao localDao;
    @Override
    public int insertLocal(Local local) {
        return localDao.insertLocal(local);
    }
}
