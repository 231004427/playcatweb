package com.sun.playcat.json;

import com.google.gson.Gson;
import com.sun.playcat.domain.ActionType;
import com.sun.playcat.domain.BaseRequest;
import com.sun.playcat.domain.User;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sunlin on 2017/7/27.
 */
public class MainAction {
    public static String BuildJsonWeb(
            WebApplicationContext webContext,HttpServletRequest req, String requestJson) {
        Gson gson=new Gson();
        BaseRequest baseResult=gson.fromJson(requestJson,BaseRequest.class);


        String returnJson="";
        int actionNum=baseResult.getActionType();
        boolean isCheck=true;

        if(actionNum==ActionType.LOGIN||actionNum==ActionType.TOKEN_BUILD||actionNum==ActionType.CONFIG_GET){
            isCheck=false;
        }

        if(isCheck) {
            //判断token
            TokenJson tokenJson = new TokenJson(webContext);
            returnJson = tokenJson.check(baseResult);
            if (!returnJson.isEmpty()) {
                return returnJson;
            }
        }


        //用户查询
        if(actionNum==ActionType.USER_GET){
            UserJson userJson=new UserJson(webContext);
            returnJson=userJson.get(baseResult);
        }
        //用户登入
        if(actionNum== ActionType.LOGIN){
            UserJson userJson=new UserJson(webContext);
            returnJson=userJson.login(baseResult);}
        //发送手机验证码
        if(actionNum==ActionType.SEND_CODE){
            PCodeJson pCodeJson=new PCodeJson(webContext);
            returnJson=pCodeJson.sendCode(baseResult);}
        //手机验证
        if (actionNum==ActionType.PHONE_CHECK) {
            PCodeJson pCodeJson=new PCodeJson(webContext);
            returnJson=pCodeJson.phoneCheck(baseResult);}
        //用户注册
        if (actionNum==ActionType.REGIST) {
            UserJson userJson=new UserJson(webContext);
            returnJson=userJson.registUser(baseResult,req);}
        //游戏列表
        if(actionNum==ActionType.GAME_SEARCH){
            GameJson gameJson=new GameJson(webContext);
            returnJson=gameJson.search(baseResult);}
        //游戏信息获取
        if(actionNum==ActionType.GAME_GET){
            GameJson gameJson=new GameJson(webContext);
            returnJson=gameJson.get(baseResult);}
        //游戏评论
        if(actionNum==ActionType.COMMENT_SEARCH){
            CommentJson commentJson=new CommentJson(webContext);
            returnJson=commentJson.search(baseResult);}
        //添加游戏评论
        if(actionNum==ActionType.COMMENT_ADD){
            CommentJson commentJson=new CommentJson(webContext);
            returnJson=commentJson.add(baseResult);}
        //取消收藏
        if(actionNum==ActionType.COLLECT_DEL){
            CollectJson collectJson=new CollectJson(webContext);
            returnJson=collectJson.del(baseResult);
        }
        //添加收藏
        if(actionNum==ActionType.COLLECT_ADD){
            CollectJson collectJson=new CollectJson(webContext);
            returnJson=collectJson.insert(baseResult);
        }
        //获取收藏
        if(actionNum==ActionType.COLLECT_GET){
            CollectJson collectJson=new CollectJson(webContext);
            returnJson=collectJson.get(baseResult);
        }
        //获取玩家
        if(actionNum==ActionType.GAME_PLAY_SEARCH){
            GamePlayJson gamePlayJson=new GamePlayJson(webContext);
            returnJson=gamePlayJson.getTopList(baseResult);
        }
        //获取商品列表
        if(actionNum==ActionType.GOODS_SEARCH){
            GoodsJson goodsJson=new GoodsJson(webContext);
            returnJson=goodsJson.search(baseResult);
        }
        //获取地址
        if(actionNum==ActionType.ADDRESS_GET){
            AddressJson addressJson=new AddressJson(webContext);
            returnJson=addressJson.get(baseResult);
        }
        //添加地址
        if(actionNum==ActionType.ADDRESS_ADD){
            AddressJson addressJson=new AddressJson(webContext);
            returnJson=addressJson.insert(baseResult);
        }
        //更新地址
        if(actionNum==ActionType.ADDRESS_UPDATE){
            AddressJson addressJson=new AddressJson(webContext);
            returnJson=addressJson.update(baseResult);
        }
        //删除地址
        if(actionNum==ActionType.ADDRESS_DEL){
            AddressJson addressJson=new AddressJson(webContext);
            returnJson=addressJson.del(baseResult);
        }
        //查询地址
        if(actionNum==ActionType.ADDRESS_SEARCH){
            AddressJson addressJson=new AddressJson(webContext);
            returnJson=addressJson.search(baseResult);
        }
        //修改QQ
        if(actionNum==ActionType.USER_UPDATE_QQ){
            UserJson userJson=new UserJson(webContext);
            returnJson=userJson.updateQQ(baseResult);
        }
        //修改充值手机号
        if(actionNum==ActionType.USER_UPDATE_PHONE2){
            UserJson userJson=new UserJson(webContext);
            returnJson=userJson.updatePhone2(baseResult);
        }
        //修改地址
        if(actionNum==ActionType.USER_UPDATE_ADDRESS){
            UserJson userJson=new UserJson(webContext);
            returnJson=userJson.updateAddress(baseResult);
        }
        //修改微信
        if(actionNum==ActionType.USER_UPDATE_WEIXIN){
            UserJson userJson=new UserJson(webContext);
            returnJson=userJson.updateWeixin(baseResult);
        }
        //获取地区
         if(actionNum==ActionType.AREA_SEARCH){
            AreaJson areaJson=new AreaJson(webContext);
             returnJson=areaJson.search(baseResult);
         }
         //添加订单
        if(actionNum==ActionType.ORDER_ADD){
             OrderJson orderJson=new OrderJson(webContext);
             returnJson=orderJson.insert(baseResult);
        }
        //添加朋友
        if(actionNum==ActionType.FRIEND_ADD){
            FriendJson friendJson=new FriendJson(webContext);
            returnJson=friendJson.insert(baseResult);
        }
        //查询朋友
        if (actionNum==ActionType.FRIEND_SEARCH){
            FriendJson friendJson=new FriendJson(webContext);
            returnJson=friendJson.search(baseResult);
        }
        //查看战绩
        if(actionNum==ActionType.GAME_PLAY_SEARCH_USER){
            GamePlayJson gamePlayJson=new GamePlayJson(webContext);
            returnJson=gamePlayJson.getUserList(baseResult);
        }
        //查询未读数
        if(actionNum==ActionType.FRIEND_NO_READ){
            FriendJson friendJson=new FriendJson(webContext);
            returnJson=friendJson.noReadCount(baseResult);
        }
        //查询好友消息
        if(actionNum==ActionType.FRIEND_MESSAGE){
            FriendJson friendJson=new FriendJson(webContext);
            returnJson=friendJson.messageList(baseResult);
        }
        //更新为可读状态
        if(actionNum==ActionType.FRIEND_SET_ALL_READ){
            FriendJson friendJson=new FriendJson(webContext);
            returnJson=friendJson.setAllRead(baseResult);
        }
        //消息列表
        if(actionNum==ActionType.MESSAGE_SEARCH){
            MessageJson messageJson=new MessageJson(webContext);
            returnJson=messageJson.search(baseResult);
        }
        //更新消息状态
        if(actionNum==ActionType.MESSAGE_UPDATE_STATUS){
            MessageJson messageJson=new MessageJson(webContext);
            returnJson=messageJson.updateStatus(baseResult);
        }
        //更新消息状态
        if(actionNum==ActionType.MESSAGE_UPDATE_LIST_STATUS){
            MessageJson messageJson=new MessageJson(webContext);
            returnJson=messageJson.updateStatusList(baseResult);
        }
        //添加消息
        if(actionNum==ActionType.MESSAGE_ADD){
            MessageJson messageJson=new MessageJson(webContext);
            returnJson=messageJson.add(baseResult);
        }
        //更新头像
        if(actionNum==ActionType.USER_UPDATE_PHOTO){
            UserJson userJson=new UserJson(webContext);
            returnJson=userJson.updatePhoto(baseResult,req);
        }
        //更新用户名
        if(actionNum==ActionType.USER_UPDATE_NAME){
            UserJson userJson=new UserJson(webContext);
            returnJson=userJson.updateName(baseResult);
        }
        //更新性别
        if(actionNum==ActionType.USER_UPDATE_SEX){
            UserJson userJson=new UserJson(webContext);
            returnJson=userJson.updateSex(baseResult);
        }
        //更新城市
        if(actionNum==ActionType.USER_UPDATE_CITY){
            UserJson userJson=new UserJson(webContext);
            returnJson=userJson.updateCity(baseResult);
        }
        //获取收藏游戏
        if(actionNum==ActionType.COLLECT_SARCH_GAME){
            CollectJson collectJson=new CollectJson(webContext);
            returnJson=collectJson.searchGame(baseResult);
        }
        //获取订单列表
        if(actionNum==ActionType.ORDER_SEARCH){
            OrderJson orderJson=new OrderJson(webContext);
            returnJson=orderJson.search(baseResult);
        }
        //更新密码
        if(actionNum==ActionType.USER_UPDATE_PASS){
            UserJson userJson=new UserJson(webContext);
            returnJson=userJson.updatePassword(baseResult);
        }
        //忘记密码
        if(actionNum==ActionType.USER_UPDATE_PASS_PHONE){
            UserJson userJson=new UserJson(webContext);
            returnJson=userJson.updatePassPhone(baseResult);
        }
        //获取配置信息
        if(actionNum==ActionType.CONFIG_GET){
            ConfigJson configJson=new ConfigJson(webContext);
            returnJson=configJson.get(baseResult);
        }
        //获取TOKEN
        if(actionNum==ActionType.TOKEN_BUILD){
            TokenJson tokenJson = new TokenJson(webContext);
            returnJson=tokenJson.build(baseResult);
        }
        //申请朋友
        if(actionNum==ActionType.MESSAGE_ADD_FRIEND){
            MessageJson messageJson=new MessageJson(webContext);
            returnJson=messageJson.addFriend(baseResult);
        }
        return returnJson;
        }
}
