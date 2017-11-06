package com.sun.playcat.service;

import com.sun.playcat.dao.PCodeDao;
import com.sun.playcat.dao.UserDao;
import com.sun.playcat.domain.PCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by sunlin on 2017/7/12.
 */
@Service("pCodeService")
@Transactional
public class PCodeServiceImpl implements PCodeDao {
    @Resource
    private PCodeDao pCodeDao;
    @Override
    public int checkPhone(PCode pCode) {
        return pCodeDao.checkPhone(pCode);
    }
    @Override
    public int checkCode(PCode pCode) {
        if(pCode.getPhone()==null||pCode.getPhone().equalsIgnoreCase("")){
            throw new RuntimeException("User phone should not be empty.");}
        if(pCode.getCode()==null||pCode.getCode().equalsIgnoreCase("")){
            throw new RuntimeException("User code should not be empty.");}
        int count=pCodeDao.checkCode(pCode);
        if(count>0) {
            pCode.setStatus(2);
            updateCode(pCode);
        }
        return count;
    }
    @Override
    public int insertCode(PCode pCode) {
        if(pCode.getPhone()==null||pCode.getPhone().equalsIgnoreCase("")){
            throw new RuntimeException("User phone should not be empty.");}
        if(pCode.getCode()==null||pCode.getCode().equalsIgnoreCase("")){
            throw new RuntimeException("User code should not be empty.");}
        pCode.setStatus(2);
        updateCode(pCode);
        pCode.setStatus(1);
        int returnNum=pCodeDao.insertCode(pCode);
        return returnNum;
    }
    @Override
    public void updateCode(PCode pCode) {
        pCodeDao.updateCode(pCode);
    }
}
