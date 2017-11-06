package com.sun.playcat.dao;

import com.sun.playcat.domain.Local;
import com.sun.playcat.domain.User;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cache.annotation.CacheEvict;

import java.util.List;

/**
 * 这里的@MapperScan就是上面所讲的Mapper扫描器中所需要的配置，会自动生成代理对象。
 * 注意，接口中的方法名称要和对应的MyBatis映射文件中的语句的id值一样，因为生成的
 * 动态代理，会根据这个匹配相应的Sql语句执行。另外就是方法的参数和返回值也需要注
 * 意。接口中的方法如何定义，对应的MyBatis映射文件就应该进行相应的定义。
 * 最后，标注中的userDao是用来作为Spring的Bean的id(或name)进行使用的，方便我
 * 们在Service层进行注入使用。
 */

@MapperScan
public interface UserDao {
    //此处的方法名必须和mapper中的映射文件中的id同名
    //回去映射文件中通过com.hua.saf.dao.UserDao.getUser,即this.getClass().getName()+".getUser"
    public User getUser(User user);
    public User login(User user);
    public int insertUser(User user);
    public int count(User user);
    public int insertUseList(List<User> list);
    public int updateUser(User user);
    public int deleteUser(int id);
    public List<User> search(
            @Param("start") int start,
            @Param("pageNum") int pageNum,
            @Param("name") String name);
    public void updateQQ(@Param("qq") String qq,@Param("id") int id);
    public void updatePhone2(@Param("phone2") String phone2,@Param("id") int id);
    public void updateAddress(@Param("address") String address,@Param("id") int id);
    public void updateWeixin(@Param("weixin") String weixin,@Param("id") int id);
    public User getMoney(int id);

    public void updateGold(@Param("id") int id,@Param("gold") int gold);
    public void addGold(@Param("id") int id,@Param("gold") int gold);
    public void subGold(@Param("id") int id,@Param("gold") int gold);

    public void updateZuan(@Param("id") int id, @Param("zuan") int zuan);
    public void addZuan(@Param("id") int id,@Param("zuan") int gold);
    public void subZuan(@Param("id") int id,@Param("zuan") int gold);
    public void updatePhoto(User user);
    public void updateName(User user);
    public void updateSex(User user);
    public void updateCity(User user);
    public void updatePassword(User user);
    public void updateToken(@Param("id") int id,@Param("token") String token);
}
