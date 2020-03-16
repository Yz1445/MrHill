package com.tlp.mrhill.service.impl;

//import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageHelper;
import com.tlp.mrhill.mapper.UserMapper;
import com.tlp.mrhill.model.User;
import com.tlp.mrhill.service.Login;
import com.tlp.mrhill.vo.JsonRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginImpl implements Login {
    @Autowired
    UserMapper userMapper;

    @Override
    public User longin(User user) {
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        return userMapper.getUser(user);
    }

    @Override
    public JsonRequest findAllUser(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        JsonRequest rq = new JsonRequest();
        rq.setCode(0);
        rq.setCount(1000);
        List<User> lu = userMapper.selectAllUser();
        System.out.println("获得的数据数量："+lu.size());
        rq.setData(lu);
        return rq;
    }

}
