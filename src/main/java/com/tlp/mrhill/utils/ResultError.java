package com.tlp.mrhill.utils;

public class ResultError {
    public Integer errorCode;
    public String errorMsg;
    public ResultError(BaseErrorInfoInterface error){
        this.errorCode = error.getResultCode();
        this.errorMsg = error.getResultMsg();
    }
    public ResultError(Integer errorCode,String errorMsg){
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
