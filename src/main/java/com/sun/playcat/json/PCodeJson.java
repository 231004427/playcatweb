package com.sun.playcat.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.playcat.common.Code;
import com.sun.playcat.common.GsonHelp;
import com.sun.playcat.dao.PCodeDao;
import com.sun.playcat.domain.ActionType;
import com.sun.playcat.domain.BaseRequest;
import com.sun.playcat.domain.PCode;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

/**
 * Created by sunlin on 2017/7/12.
 */
public class PCodeJson  {
    public Gson gson;
    PCodeDao pCodeDao;
    private WebApplicationContext webApplicationContext;

    public PCodeJson(WebApplicationContext context){
        gson= GsonHelp.getGsonObj();
        webApplicationContext=context;
        pCodeDao=(PCodeDao) webApplicationContext.getBean("pCodeService");
    }
    public String sendCode(BaseRequest baseRequest){
        //解析JSON
        PCode pCode=gson.fromJson(baseRequest.getData(),PCode.class);
        if(pCode.getPhone()==null)
        {
            return gson.toJson(MessageHelp.BuildBaseResult(3,
                    "手机号无效", ActionType.SEND_CODE, ""));
        }
        //生成验证码
        String code= Code.getRandNum(4);
        pCode.setCode(code);
        pCode.setStatus(1);
        pCode.setCreate_time(new Date());
        //保存数据库
        pCodeDao.insertCode(pCode);
        //发送验证码
        return gson.toJson(MessageHelp.BuildBaseResult(0,
                "", ActionType.SEND_CODE, "发送成功"));

    }
    public  String phoneCheck(BaseRequest baseRequest){
        //解析JSON
        PCode pCode=gson.fromJson(baseRequest.getData(),PCode.class);
        //手机号重复
        pCodeDao=(PCodeDao) webApplicationContext.getBean("pCodeService");
        int count=pCodeDao.checkPhone(pCode);
        if(count>0){
            return gson.toJson(MessageHelp.BuildBaseResult(2,
                    "手机号重复注册", ActionType.PHONE_CHECK, ""));
        }
        //验证码是否准确
        count=pCodeDao.checkCode(pCode);
        if(count>0){
            return gson.toJson(MessageHelp.BuildBaseResult(0,
                    "",ActionType.PHONE_CHECK,"验证通过"));
        }else{
             return gson.toJson(MessageHelp.BuildBaseResult(3,
                     "验证码错误",ActionType.PHONE_CHECK,""));
        }
    }
}
