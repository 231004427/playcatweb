package com.sun.playcat.common;

import com.sun.playcat.domain.Token;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by sunlin on 2017/11/1.
 */
public class TokenHelp {
    public static Token getObj(int id){
        Date time = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(time);
        //calendar.add(calendar.DATE,7);
        calendar.add(calendar.SECOND,86400);
        String data=String.valueOf(id)+"&"+String.valueOf(calendar.getTimeInMillis());
        Token token=new Token();
        token.setToken_data(data);
        token.setCreate_time(time);
        token.setExpire_time(calendar.getTime());
        return token;
    }
}
