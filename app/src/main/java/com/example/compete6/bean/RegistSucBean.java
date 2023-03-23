package com.example.compete6.bean;

public class RegistSucBean {
    /**
     * msg : 新增用户'qwe'失败，登录账号已存在
     * code : 500
     */

    private String msg;
    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
