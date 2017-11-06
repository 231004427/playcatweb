package com.sun.playcat.dao;

import com.sun.playcat.domain.Token;
import org.mybatis.spring.annotation.MapperScan;

/**
 * Created by sunlin on 2017/10/25.
 */
@MapperScan
public interface TokenDao {
    public Token get(int user_id);
    public String getStr(int user_id);
    public int insert(Token token);
    public void delete(int user_id);
    public int clearToken(int uer_id);
}
