package com.sun.playcat.service;

import com.sun.playcat.common.MD5;
import com.sun.playcat.dao.*;
import com.sun.playcat.domain.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by sunlin on 2017/5/16.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserDao {

    @Resource
    private UserDao userDao;
    @Resource
    private LocalDao localDao;
    @Resource
    private FriendDao friendDao;
    @Resource
    private OrderDao orderDao;
    @Resource
    private CollectDao collectDao;
    @Resource
    private TokenDao tokenDao;
    @Autowired
    private JedisPool jedisPool;//注入JedisPool

    public User getUser(User user) {
        User resutlUser=userDao.getUser(user);
        //获取订单数
        resutlUser.setOrderNum(orderDao.orderCount(user.getId()));
        //获取收藏数
        CollectList collect=new CollectList();
        collect.setUid(user.getId());
        collect.setStatus(1);
        collect.setType(1);
        resutlUser.setLoveNum(collectDao.searchCount(collect));
        //获取未读消息数
        //resutlUser.setNo_read(friendDao.noReadCount(user.getId()));
        return resutlUser;

    }
    @Override
    public User login(User user) {

        if(user.getPhone()==null||user.getPhone().equalsIgnoreCase("")){
            if(user.getId()==0){
            throw new RuntimeException("User phone or id should not be empty.");
            }
        }
        return userDao.login(user);
    }

    public int insertUser(User user) {
        userDao.insertUser(user);
        if(user.getPhone().equalsIgnoreCase("")){
            throw new RuntimeException("User phone should not be empty.");}
        user.getLocal().setUid(user.getId());
        localDao.insertLocal(user.getLocal());
        if(user.getLocal().getUid()<=0){
            throw new RuntimeException("Local uid should not be empty.");}
        //自动关注系统用户
        Friend friend=new Friend();
        friend.setUser_id(user.getId());
        friend.setFriend_id(1);
        friend.setCreate_time(new Date());
        friend.setStatus(1);
        friend.setGroup_id(-1);
        friend.setType(2);
        friendDao.insert(friend);
        return user.getId();
    }
    public int count(User user){
        return userDao.count(user);
    }
    public int insertUseList(List<User> list) {
        return userDao.insertUseList(list);
    }
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }
    public int deleteUser(int id) {
        return userDao.deleteUser(id);
    }
    public List<User> search(int start,int pageNum,String name) {
        List<User> u=userDao.search(start,pageNum,"%"+name);
        return u;
    }

    @Override
    public void updateQQ(@Param("qq") String qq, @Param("id") int id) {
        userDao.updateQQ(qq,id);
    }

    @Override
    public void updatePhone2(@Param("phone2") String phone2, @Param("id") int id) {
        userDao.updatePhone2(phone2,id);
    }
    @Override
    public void updateAddress(@Param("address") String address, @Param("id") int id) {
        userDao.updateAddress(address,id);
    }

    @Override
    public void updateWeixin(@Param("weixin") String weixin, @Param("id") int id) {
        userDao.updateWeixin(weixin,id);
    }

    @Override
    public User getMoney(int id) {
        return userDao.getMoney(id);
    }

    @Override
    public void updateGold(@Param("id") int id, @Param("gold") int gold) {
        userDao.updateGold(id,gold);
    }

    @Override
    public void addGold(@Param("id") int id, @Param("gold") int gold) {
        userDao.addGold(id,gold);
    }

    @Override
    public void subGold(@Param("id") int id, @Param("gold") int gold) {
        userDao.subGold(id,gold);
    }

    @Override
    public void updateZuan(@Param("id") int id, @Param("zuan") int zhuan) {
        userDao.updateZuan(id,zhuan);
    }

    @Override
    public void addZuan(@Param("id") int id, @Param("zuan") int gold) {
        userDao.addZuan(id,gold);
    }
    @Override
    public void subZuan(@Param("id") int id, @Param("zuan") int gold) {
        userDao.addZuan(id,gold);
    }

    @Override
    public void updatePhoto(User user) {
        userDao.updatePhoto(user);
    }

    @Override
    public void updateName(User user) {
        userDao.updateName(user);
    }

    @Override
    public void updateSex(User user) {
        userDao.updateSex(user);
    }

    @Override
    public void updateCity(User user) {
        userDao.updateCity(user);
    }

    @Override
    public void updatePassword(User user) {
        userDao.updatePassword(user);
    }

    @Override
    @CacheEvict(value="cacheToken",key="#id")
    public void updateToken(@Param("id") int id,String token) {
        userDao.updateToken(id,token);
        //更新Redis
        Jedis jedis = jedisPool.getResource();
        jedis.setex(String.valueOf(id),86400,token);
        jedis.close();
    }

}