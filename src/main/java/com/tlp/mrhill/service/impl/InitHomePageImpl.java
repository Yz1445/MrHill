package com.tlp.mrhill.service.impl;

import com.tlp.mrhill.mapper.MenuMapper;
import com.tlp.mrhill.model.Menu;
import com.tlp.mrhill.service.InitHomePage;
import com.tlp.mrhill.utils.MrHillTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class InitHomePageImpl implements InitHomePage {
    @Autowired
    MenuMapper menuMapper;

    @Override
    public Menu getMenu() {
        Menu mu = menuMapper.selectByPrimaryKey(MrHillTypeEnum.PROJECTNAME.getResultCode());
        if (Objects.isNull(mu)) {
            mu = new Menu();
            mu.setMenuname(MrHillTypeEnum.PROJECTNAME.getResultName());
            mu.setMenuid(MrHillTypeEnum.PROJECTNAME.getResultCode());
        }
        return mu;
    }
}
