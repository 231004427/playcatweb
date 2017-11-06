package com.sun.playcat.json;

import com.sun.playcat.dao.AddressDao;
import com.sun.playcat.domain.*;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by sunlin on 2017/8/9.
 */
public class AddressJson extends BaseJson {

    private AddressDao addressService;
    public AddressJson(WebApplicationContext context) {
        super(context);
        addressService=(AddressDao) context.getBean("addressService");
    }
    public String insert(BaseRequest baseRequest){
        Address address=gson.fromJson(baseRequest.getData(),Address.class);
        addressService.insert(address);
        BaseResult baseResult=MessageHelp.BuildBaseResult(0,
                "", ActionType.ADDRESS_ADD,"添加成功",gson.toJson(address));
        return gson.toJson(baseResult);
    }
    public String get(BaseRequest baseRequest){
        Address address=gson.fromJson(baseRequest.getData(),Address.class);
        Address objData=addressService.get(address);
        BaseResult baseResult = MessageHelp.BuildBaseResult(0,
                "", ActionType.ADDRESS_GET, "获取成功", gson.toJson(objData));
        return gson.toJson(baseResult);
    }
    public String search(BaseRequest baseRequest){

        AddressList objList=gson.fromJson(baseRequest.getData(),AddressList.class);

        objList.setCount(addressService.searchCount(objList.getUser_id(),objList.getStatus()));
        objList.setAddressList(addressService.search(
                objList.getUser_id(),
                objList.getStatus(),
                objList.getStart(),
                objList.getPageNum()));

        BaseResult baseResult = MessageHelp.BuildBaseResult(0,
                "", ActionType.ADDRESS_SEARCH, "获取成功", gson.toJson(objList));
        return gson.toJson(baseResult);
    }
    public String update(BaseRequest baseRequest){
        Address address=gson.fromJson(baseRequest.getData(),Address.class);
        addressService.update(address);
        return gson.toJson(MessageHelp.BuildBaseResult(0,
                "", ActionType.ADDRESS_UPDATE,"更新成功"));
    }
    public String del(BaseRequest baseRequest){
        Address address=gson.fromJson(baseRequest.getData(),Address.class);
        addressService.del(address);
        return gson.toJson(MessageHelp.BuildBaseResult(0,
                "", ActionType.ADDRESS_UPDATE,"删除成功"));
    }
}
