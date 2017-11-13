package com.sun.playcat.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.playcat.common.GsonHelp;
import com.sun.playcat.common.Log;
import com.sun.playcat.dao.UserDao;
import com.sun.playcat.domain.ActionType;
import com.sun.playcat.domain.User;
import com.sun.playcat.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

/**
 * Created by sunlin on 2017/6/27.
 */
@Controller
public class ApiController {
    //@Autowired
    //private UserDao userService;

    @RequestMapping(value="/api/{app}/{action}/{version}",method= RequestMethod.GET)
    public ModelAndView get(
            @PathVariable String app,
            @PathVariable String action,
            @PathVariable String version,
            HttpServletRequest req,
            HttpServletResponse resp){
        ModelAndView modelAndView = new ModelAndView();

        if(app.equals("playcat")){
            //api/get/playcat/user_info/1
            if(action.equals("userInfo")){

                String idStr=req.getParameter("id");
                //User user=userService.getUser(Integer.parseInt(idStr));
                //modelAndView.addObject("user", user);
                modelAndView.setViewName("get");
            }
        }
        return modelAndView;
    }
    @RequestMapping(value = "/api/playcat/post",
            method = RequestMethod.POST,produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String post(@RequestBody String json,
            HttpServletRequest req,
            HttpServletResponse resp)
    {
        if(json.equals("")){
            Gson gson = new GsonBuilder().create();
            return gson.toJson(MessageHelp.BuildBaseResult(2,
                    "数据为空",0,""));
        };
        try {
            json= URLDecoder.decode(json, "UTF-8");
            //Log.debug(json);
            WebApplicationContext webContext=ContextLoader.getCurrentWebApplicationContext();
            //返回数据
            return MainAction.BuildJsonWeb(webContext,req,json);
        } catch (Exception e) {
            Log.error(e.toString());
            Gson gson = GsonHelp.getGsonObj();
            return gson.toJson(MessageHelp.BuildBaseResult(1,
                    "系统错误",-1,"服务器错误"));
        }
    }
}
