package com.example.compete6.bean;

public class SuccessBean {

    /**
     * code : 200
     * msg : 操作成功
     * token : eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl91c2VyX2tleSI6ImY2NTg5NjlhLWJmMDMtNDM0Mi1iYjFmLTRiNzAxYmQzNGViMCJ9.rsmLIODdu3p7DszpItwEyLXEJc20RejFXtjBITxYDA7q4PKyborAdyAsLGAtR4YEuRiq9XQIGdchtrLovGRpFA
     */

    private int code;
    private String msg;
    private String token;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
