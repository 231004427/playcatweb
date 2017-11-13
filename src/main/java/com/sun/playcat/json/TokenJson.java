package com.sun.playcat.json;

import com.sun.playcat.common.Log;
import com.sun.playcat.common.MD5;
import com.sun.playcat.common.TokenHelp;
import com.sun.playcat.dao.TokenDao;
import com.sun.playcat.dao.UserDao;
import com.sun.playcat.domain.ActionType;
import com.sun.playcat.domain.BaseRequest;
import com.sun.playcat.domain.Token;
import com.sun.playcat.domain.User;
import org.springframework.web.context.WebApplicationContext;
import sun.misc.BASE64Encoder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by sunlin on 2017/10/25.
 */
public class TokenJson extends BaseJson {
    private TokenDao tokenService;
    private UserDao userService;
    public TokenJson(WebApplicationContext context) {
        super(context);
        tokenService =(TokenDao) context.getBean("tokenService");
        userService =(UserDao) context.getBean("userService");
    }
    public String build(BaseRequest baseRequest){
        Token token= gson.fromJson(baseRequest.getData(),Token.class);
        //判断用户密码
        User user=new User();
        user.setPhone(token.getPhone());
        user.setPassword(token.getPassword());
        if(user.getPhone().isEmpty())
        {
            return gson.toJson(MessageHelp.BuildBaseResult(1,
                    "手机号不能为空", ActionType.TOKEN_BUILD,""));
        }
        if(user.getPassword().isEmpty())
        {
            return gson.toJson(MessageHelp.BuildBaseResult(2,
                    "密码不能为空",ActionType.TOKEN_BUILD,""));
        }
        user=userService.login(user);
        if(user!=null){
            //生成Token
            token= TokenHelp.getObj(user.getId());
            token.setUser_id(user.getId());
            tokenService.delete(user.getId());
            tokenService.insert(token);
            //更新Token
            userService.updateToken(user.getId(),token.getToken_data());
            //tokenService.clearToken(user.getId());
            return gson.toJson(MessageHelp.BuildBaseResult(0,
                    "", ActionType.TOKEN_BUILD,"获取成功",gson.toJson(token)));
        }else{
            return gson.toJson(MessageHelp.BuildBaseResult(3,
                    "账号或密码错误",ActionType.TOKEN_BUILD,""));
        }
    }

    public String check(BaseRequest baseResult) {
        String tokenStr=tokenService.getStr(baseResult.getUserid());
        if(tokenStr.equals("0")){
            return gson.toJson(MessageHelp.BuildBaseResult(504,
                    "token null",ActionType.TOKEN_ERROR,"token null"));
        }else{
            if(baseResult.getToken().equals(tokenStr)){
                long timeExt=Long.parseLong(baseResult.getToken().split("&")[1]);
                long timeNow=System.currentTimeMillis();
                Log.debug("token:"+baseResult.getToken()+" now:"+timeNow);
                if(timeNow>timeExt){
                    return gson.toJson(MessageHelp.BuildBaseResult(505,
                            "token expire",ActionType.TOKEN_ERROR,"token expire"));
                }else {
                    return "";
                }
            }else
            {
                return gson.toJson(MessageHelp.BuildBaseResult(506,
                        "token error",ActionType.TOKEN_ERROR,"token error"));
            }
        }
    }
}
