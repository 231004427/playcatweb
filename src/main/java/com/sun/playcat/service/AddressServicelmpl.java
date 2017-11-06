package com.sun.playcat.service;

import com.sun.playcat.dao.AddressDao;
import com.sun.playcat.domain.Address;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by sunlin on 2017/8/9.
 */
@Service("addressService")
@Transactional
public class AddressServicelmpl implements AddressDao {
    @Resource
    private AddressDao addressDao;
    @Override
    public int insert(Address address) {
        return addressDao.insert(address);
    }

    @Override
    public Address get(Address address) {
        return addressDao.get(address);
    }

    @Override
    public void del(Address address) {
        addressDao.del(address);
    }

    @Override
    public void update(Address address) {
        addressDao.update(address);
    }

    @Override
    public int searchCount(int userId,int status) {
        return addressDao.searchCount(userId,status);
    }

    @Override
    public List<Address> search(int userId,int status,int start,int pageNum) {
        return addressDao.search(userId,status,start,pageNum);
    }
}
