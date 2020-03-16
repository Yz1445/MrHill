package com.tlp.mrhill.vo;

public class UserVO {
    public String userId;

    private String userName;

    public String password;

    public String phone;

    public UserVO(String userId,String userName,String password, String phone){
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.phone = phone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
