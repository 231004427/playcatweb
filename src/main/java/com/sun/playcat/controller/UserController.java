package com.sun.playcat.controller;

import com.sun.playcat.dao.UserDao;
import com.sun.playcat.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.net.www.http.HttpClient;

import java.util.List;

/**
 * Created by sunlin on 2017/5/17.
 */
@Controller
public class UserController {
    @Autowired
    private UserDao userService;

    @RequestMapping("/list/abc")
    public String showList(){

        //User user=userService.getUser(1);

        return "123123";
    }
    @RequestMapping(value = "/add",method ={RequestMethod.POST})
    @ResponseBody
    public String saveUser(@ModelAttribute(value="userobj")User userObj,Model model) throws Exception{

        //userService.insertUser(userObj) ;



        return "redirect:/list" ;
    }

}
