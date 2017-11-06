package com.sun.playcat.service;

import com.sun.playcat.dao.ConfigDao;
import com.sun.playcat.domain.Collect;
import com.sun.playcat.domain.Config;
import com.sun.playcat.domain.ConfigList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by sunlin on 2017/9/13.
 */
@Service("configService")
@Transactional
public class ConfigServicelmpl implements ConfigDao {
    @Resource
    private ConfigDao configDao;

    @Override
    public List<Config> get(ConfigList configList) {
        return configDao.get(configList);
    }
}
