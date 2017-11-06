package com.sun.playcat.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by sunlin on 2017/8/9.
 */
public class BaseJson {
    public Gson gson;
    public WebApplicationContext webApplicationContext;

    public BaseJson(WebApplicationContext context){
        gson=new GsonBuilder().create();
        webApplicationContext=context;
    }
}
