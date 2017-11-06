package com.sun.playcat.json;

import com.sun.playcat.domain.BaseResult;

/**
 * Created by sunlin on 2017/7/6.
 */
public class MessageHelp {
    public static BaseResult BuildBaseResult(int errNum, String errMsg, int type, String txt,String data){
        BaseResult result = new BaseResult();
        result.setErrcode(errNum);
        result.setErrmsg(errMsg);
        result.setType(type);
        result.setText(txt);
        result.setData(data);
        return result;
    }
    public static BaseResult BuildBaseResult(int errNum, String errMsg, int type, String txt){
        BaseResult result = new BaseResult();
        result.setErrcode(errNum);
        result.setErrmsg(errMsg);
        result.setType(type);
        result.setText(txt);
        result.setData("");
        return result;
    }
}
