package com.tlp.mrhill.utils;

public enum MrHillTypeEnum implements BaseTypeInfoInterFace {
    PROJECTNAME(1,"基础平台");
    private int MenuId;
    private String MenuName;
    MrHillTypeEnum(int menuId, String menuName){
        this.MenuId = menuId;
        this.MenuName = menuName;

    }
    @Override
    public Integer getResultCode() {
        return MenuId;
    }

    @Override
    public String getResultName() {
        return MenuName;
    }
}
