package com.sun.playcat.dao;

import com.sun.playcat.domain.Local;
import org.mybatis.spring.annotation.MapperScan;

/**
 * Created by sunlin on 2017/7/12.
 */
@MapperScan
public interface LocalDao {
    public int insertLocal(Local local);
}
