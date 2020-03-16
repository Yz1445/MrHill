package com.tlp.mrhill.controller;

import com.alibaba.druid.util.StringUtils;
import com.tlp.mrhill.service.impl.LoginImpl;
import com.tlp.mrhill.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/MrHill")
public class GetHtmlController {
    @Autowired
    HttpServletRequest request;
    @Autowired
    LoginImpl login;
    @RequestMapping("/topTiles")
    public ModelAndView getTopTiles(ModelAndView modelAndView){
        modelAndView.addObject("UserName",100);
        modelAndView.setViewName("topTiles");
        return modelAndView;
    }
    @RequestMapping("/register")
    public ModelAndView getRegister(ModelAndView modelAndView,String userId){
        System.out.println("用户id:"+userId);

        modelAndView.setViewName("register");
        if (!StringUtils.isEmpty(userId)) {
            modelAndView.addObject("readonly","readonly");
        }
        return modelAndView;
    }
    @RequestMapping("/getHtmlCssTest")
    public ModelAndView getHtmlCssTest(ModelAndView modelAndView){
        modelAndView.setViewName("htmlCssTest");
        return  modelAndView;
    }
    @RequestMapping("/getUserControl")
    public ModelAndView getUserControl(ModelAndView modelAndView){
//        List<User> lu = login.findAllUser(0,10);
        List<UserVO> luv = new ArrayList<UserVO>();
        luv.add(new UserVO("1","yanzhao","123","杨钊"));
        modelAndView.addObject("UserList",luv);
        modelAndView.setViewName("userControl");
        return modelAndView;
    }
}
