package com.sun.playcat.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.playcat.common.FileHelp;
import com.sun.playcat.common.TokenHelp;
import com.sun.playcat.dao.PCodeDao;
import com.sun.playcat.dao.TokenDao;
import com.sun.playcat.dao.UserDao;
import com.sun.playcat.domain.*;
import org.springframework.web.context.WebApplicationContext;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by sunlin on 2017/7/2.
 */
public class UserJson extends BaseJson {
    private UserDao userService;
    private TokenDao tokenService;
    public UserJson(WebApplicationContext context) {
        super(context);
        userService =(UserDao) context.getBean("userService");
        tokenService=(TokenDao)context.getBean("tokenService");
    }
    public String updateQQ(BaseRequest baseRequest){
        User user=gson.fromJson(baseRequest.getData(),User.class);
        userService.updateQQ(user.getQq(),user.getId());
        return gson.toJson(MessageHelp.BuildBaseResult(0,
                "",ActionType.USER_UPDATE_QQ,"更新成功"));
    }
    public String updatePhone2(BaseRequest baseRequest){
        User user=gson.fromJson(baseRequest.getData(),User.class);
        userService.updatePhone2(user.getPhone2(),user.getId());
        return gson.toJson(MessageHelp.BuildBaseResult(0,
                "",ActionType.USER_UPDATE_PHONE2,"更新成功"));
    }
    public String updateAddress(BaseRequest baseRequest){
        User user=gson.fromJson(baseRequest.getData(),User.class);
        userService.updateAddress(user.getAddress(),user.getId());
        return gson.toJson(MessageHelp.BuildBaseResult(0,
                "",ActionType.USER_UPDATE_ADDRESS,"更新成功"));
    }
    public String addGold(BaseRequest baseRequest){
        User user=gson.fromJson(baseRequest.getData(),User.class);
        userService.addGold(user.getGold(),user.getId());
        return gson.toJson(MessageHelp.BuildBaseResult(0,
                "",ActionType.USER_UPDATE_WEIXIN,"更新成功"));
    }
    public String updateWeixin(BaseRequest baseRequest){
        User user=gson.fromJson(baseRequest.getData(),User.class);
        userService.updateWeixin(user.getWeixin(),user.getId());
        return gson.toJson(MessageHelp.BuildBaseResult(0,
                "",ActionType.USER_UPDATE_WEIXIN,"更新成功"));
    }
    public String get(BaseRequest baseRequest){
        User user=gson.fromJson(baseRequest.getData(),User.class);
        user=userService.getUser(user);

        BaseResult baseResult;
        if(user!=null) {
            baseResult = MessageHelp.BuildBaseResult(0,
                    "", ActionType.USER_GET, "获取成功", gson.toJson(user));
        }else{
            baseResult = MessageHelp.BuildBaseResult(1,
                    "用户不存在", ActionType.USER_GET, "");
        }
        return gson.toJson(baseResult);

    }
    public String login(BaseRequest baseRequest){
        User user=gson.fromJson(baseRequest.getData(),User.class);
        if(user.getPhone().isEmpty())
        {
            return gson.toJson(MessageHelp.BuildBaseResult(1,
                    "手机号不能为空", ActionType.LOGIN,""));
        }
        if(user.getPassword().isEmpty())
        {
            return gson.toJson(MessageHelp.BuildBaseResult(2,
                    "密码不能为空",ActionType.LOGIN,""));
        }
        user=userService.login(user);
        if(user!=null){
            //生成Token
            Token token=TokenHelp.getObj(10014);
            token.setUser_id(user.getId());
            tokenService.delete(user.getId());
            tokenService.insert(token);
            //更新Token
            userService.updateToken(user.getId(),token.getToken_data());
            user.setToken(token.getToken_data());
            //tokenService.clearToken(user.getId());
            return gson.toJson(MessageHelp.BuildBaseResult(0,
                    "",ActionType.LOGIN,"登录成功",gson.toJson(user)));
        }else{
            return gson.toJson(MessageHelp.BuildBaseResult(3,
                    "账号或密码错误",ActionType.LOGIN,""));
        }
    }
    public String registUser(BaseRequest baseRequest,HttpServletRequest request){

        try {
            //解析JSON
            User user = gson.fromJson(baseRequest.getData(), User.class);
            //判断是否重复
            int count=userService.count(user);
            if(count>0)
            {
                return gson.toJson(MessageHelp.BuildBaseResult(1,
                        "昵称重复",ActionType.REGIST,""));
            }
            //保存图片
            if(!user.getPhoto().isEmpty()) {
                String photoPath=FileHelp.SaveHeadBase64(request,user.getPhoto());
                if(photoPath!="") {
                    user.setPhoto(photoPath);
                }else{
                    user.setPhoto("");
                }
            }
            //提交用户信息
            userService.insertUser(user);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return gson.toJson(MessageHelp.BuildBaseResult(0,
                "",ActionType.REGIST,"注册成功"));
    }
    public String updatePhoto(BaseRequest baseRequest,HttpServletRequest request){
        //解析JSON
        User user = gson.fromJson(baseRequest.getData(), User.class);
        //保存图片
        if(!user.getPhoto().isEmpty()) {
            String photoPath=FileHelp.SaveHeadBase64(request,user.getPhoto());
            if(photoPath!="") {
                user.setPhoto(photoPath);
            }else{
                user.setPhoto("");
            }
        }
        //提交用户信息
        userService.updatePhoto(user);
        BaseResult baseResult=MessageHelp.BuildBaseResult(0,
                "",ActionType.USER_UPDATE_PHOTO,"保存成功",gson.toJson(user));
        return gson.toJson(baseResult);
    }
    public String updateName(BaseRequest baseRequest){
        User user = gson.fromJson(baseRequest.getData(), User.class);
        //判断是否重复
        int count=userService.count(user);
        if(count>0)
        {
            return gson.toJson(MessageHelp.BuildBaseResult(1,
                    "用户名重复",ActionType.USER_UPDATE_NAME,""));
        }
        //提交用户信息
        userService.updateName(user);
        BaseResult baseResult=MessageHelp.BuildBaseResult(0,
                "",ActionType.USER_UPDATE_NAME,"保存成功",gson.toJson(user));
        return gson.toJson(baseResult);
    }
    public String updateSex(BaseRequest baseRequest){
        User user = gson.fromJson(baseRequest.getData(), User.class);
        //提交用户信息
        userService.updateSex(user);
        BaseResult baseResult=MessageHelp.BuildBaseResult(0,
                "",ActionType.USER_UPDATE_SEX,"保存成功",gson.toJson(user));
        return gson.toJson(baseResult);
    }
    public String updateCity(BaseRequest baseRequest){
        User user = gson.fromJson(baseRequest.getData(), User.class);
        //提交用户信息
        userService.updateCity(user);
        BaseResult baseResult=MessageHelp.BuildBaseResult(0,
                "",ActionType.USER_UPDATE_CITY,"保存成功",gson.toJson(user));
        return gson.toJson(baseResult);
    }
    public String updatePassword(BaseRequest baseRequest){
        User user = gson.fromJson(baseRequest.getData(), User.class);
        String newPass=user.getAddress();
        //判断原来密码是否准确
        user=userService.login(user);
        if(user==null)
        {
            return gson.toJson(MessageHelp.BuildBaseResult(1,
                    "原密码错误",ActionType.USER_UPDATE_PASS,""));
        }
        //提交新密码
        user.setPassword(newPass);
        userService.updatePassword(user);
        BaseResult baseResult=MessageHelp.BuildBaseResult(0,
                "",ActionType.USER_UPDATE_PASS,"修改成功",gson.toJson(user));
        return gson.toJson(baseResult);
    }
    public String updatePassPhone(BaseRequest baseRequest){

        User user = gson.fromJson(baseRequest.getData(), User.class);
        //判断手机号是否已经注册
        int count=userService.count(user);
        if(count==0)
        {
            return gson.toJson(MessageHelp.BuildBaseResult(2,
                    "手机号未注册",ActionType.USER_UPDATE_PASS_PHONE,""));
        }
        //验证码是否准确
        PCode pCode =new PCode();
        pCode.setPhone(user.getPhone());
        pCode.setCode(user.getPhone2());
        PCodeDao pCodeDao=(PCodeDao) webApplicationContext.getBean("pCodeService");
        count=pCodeDao.checkCode(pCode);
        if(count<=0){
            return gson.toJson(MessageHelp.BuildBaseResult(1,
                    "验证码错误",ActionType.PHONE_CHECK,""));
        }
        //提交新密码
        user.setId(-1);
        user.setPassword(user.getPassword());
        userService.updatePassword(user);
        BaseResult baseResult=MessageHelp.BuildBaseResult(0,
                "",ActionType.USER_UPDATE_PASS_PHONE,"密码设置成功",gson.toJson(user));
        return gson.toJson(baseResult);
    }
}
