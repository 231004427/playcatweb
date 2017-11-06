package com.sun.playcat.dao;

import com.sun.playcat.domain.Collect;
import com.sun.playcat.domain.CollectList;
import com.sun.playcat.domain.Config;
import com.sun.playcat.domain.ConfigList;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * Created by sunlin on 2017/9/13.
 */
@MapperScan
public interface ConfigDao {
    public List<Config> get(ConfigList configList);
}
