package com.sun.playcat.service;

import com.sun.playcat.dao.TokenDao;
import com.sun.playcat.dao.UserDao;
import com.sun.playcat.domain.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * Created by sunlin on 2017/10/25.
 */
@Service("tokenService")
@Transactional
public class TokenServicelmpl implements TokenDao {
    @Resource
    private TokenDao tokenDao;
    @Override
    public Token get(int user_id) {
        return tokenDao.get(user_id);
    }
    @Override
    @Cacheable(value="cacheToken",key="#user_id")
    public String getStr(int user_id) {
        Token token=tokenDao.get(user_id);
        if(token!=null){
            return token.getToken_data();
        }else{
            return "0";
        }
    }
    @Override
    @CacheEvict(value="cacheToken",key="#uer_id")
    public int clearToken(int uer_id){
        return uer_id;
    }
    @Override
    public int insert(Token token) {
        tokenDao.insert(token);
        return 1;

    }
    @Override
    public void delete(int user_id) {
        tokenDao.delete(user_id);
    }
}
