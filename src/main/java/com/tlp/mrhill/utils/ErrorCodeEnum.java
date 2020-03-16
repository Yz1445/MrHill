package com.tlp.mrhill.utils;

public enum ErrorCodeEnum implements BaseErrorInfoInterface {
    succeed(0,"成功"),
    userError(-1,"账户错误！"),
    passwordError(-2,"密码错误！"),
    userOrPaaswordError(-3,"账户或者密码错误！");
    ErrorCodeEnum(int code,String msg){
        this.errorCode = code;
        this.errorMsg = msg;
    }
    private int errorCode;
    private String errorMsg;

    @Override
    public Integer getResultCode() {
        return errorCode;
    }

    @Override
    public String getResultMsg() {
        return errorMsg;
    }
}
