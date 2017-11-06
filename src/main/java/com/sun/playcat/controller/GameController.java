package com.sun.playcat.controller;

import com.sun.playcat.dao.GameDao;
import com.sun.playcat.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sunlin on 2017/8/1.
 */
@Controller
public class GameController {
    @Autowired
    private GameDao gameService;
    @RequestMapping(value="/game/rule/{id}",method= RequestMethod.GET)
    public ModelAndView get(
            @PathVariable String id,
            HttpServletRequest req,
            HttpServletResponse resp){
        ModelAndView modelAndView = new ModelAndView();
        try {
            Game game = gameService.getRule(Integer.parseInt(id));
            modelAndView.addObject("game", game);
            modelAndView.setViewName("gameRule");
        }catch (Exception e)
        {
            modelAndView.addObject("error",e);
            modelAndView.setViewName("error");
            return modelAndView;
        }
        return modelAndView;
    }
}
