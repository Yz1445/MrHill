package com.tlp.mrhill.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.tlp.mrhill.config.LoginConfig;
import com.tlp.mrhill.model.User;
import com.tlp.mrhill.service.Login;
import com.tlp.mrhill.service.impl.InitHomePageImpl;
import com.tlp.mrhill.service.impl.LoginImpl;
import com.tlp.mrhill.utils.ErrorCodeEnum;
import com.tlp.mrhill.utils.proxy.LoginProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller

public class LoginController {
    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    @Autowired
    LoginImpl login;

    @Autowired
    LoginProxy loginProxy;

    @Autowired
    InitHomePageImpl initHomePage;

    @Autowired
    LoginConfig loginConfig;

    private Map<String,String> mapUser = new HashMap<>();
    @RequestMapping
    public ModelAndView login(ModelAndView modelAndView){
        System.out.println("被调用了");
        modelAndView.addObject("MenuName",initHomePage.getMenu().getMenuname());
        modelAndView.setViewName("login");
        return modelAndView;
    }
    @RequestMapping("/MrHill/logindemo")
    public ModelAndView logindemo(ModelAndView modelAndView){
        User u = new User();
        u.setUserName(request.getParameter("username"));
        u.setPassword(request.getParameter("password"));
        Login li = (Login) loginProxy.bind(login);
        User user = li.longin(u);
        HttpSession session = request.getSession();
        if (session.getAttribute(loginConfig.SESSION_KEY) != null) {
            user = (User) session.getAttribute(loginConfig.SESSION_KEY);
        } else {
            System.out.println("session是NULL");
        }
        if (user != null) {
            mapUser.put(user.getUserName(),user.getPassword());
            System.out.println("session:"+session.isNew());
            System.out.println("新建session");
            session.setAttribute(loginConfig.SESSION_KEY,user);
            modelAndView.addObject("MenuName",initHomePage.getMenu().getMenuname());
            modelAndView.addObject("UserName",user.getUserName());
            modelAndView.addObject("Name",user.getName());
            modelAndView.setViewName("indexDemo");
        } else {
            modelAndView.setViewName("login");
            modelAndView.addObject("errorFlg",true);
            modelAndView.addObject("errorMsg",ErrorCodeEnum.userOrPaaswordError.getResultMsg());
        }
        return modelAndView;
    }
    @RequestMapping("/MrHill/getAllUser")
    @ResponseBody
    public void getAllUser(int curr, int nums) throws IOException {
        response.getWriter().println(JSON.toJSONString(login.findAllUser(curr,nums)));
    }
//    @ResponseBody
    @RequestMapping("/MrHill/test")
    public String loginTest(){
        return "topTiles";
    }
}
