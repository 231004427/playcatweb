package com.sun.playcat.dao;

import com.sun.playcat.domain.PCode;
import org.mybatis.spring.annotation.MapperScan;

/**
 * Created by sunlin on 2017/7/12.
 */
@MapperScan
public interface PCodeDao {
    public int checkPhone(PCode pCode);
    public int checkCode(PCode pCode);
    public int insertCode(PCode pCode);
    public void updateCode(PCode pCode);
}
