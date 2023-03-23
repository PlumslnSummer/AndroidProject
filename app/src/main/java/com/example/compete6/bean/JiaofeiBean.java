package com.example.compete6.bean;

public class JiaofeiBean {

    /**
     * paymentType : 电子支付
     * phonenumber : 13800000000
     * rechargeAmount : 50
     * ruleId : 1
     * type : 2
     */

    private String paymentType;
    private String phonenumber;
    private int rechargeAmount;
    private int ruleId;
    private String type;

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public int getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(int rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public int getRuleId() {
        return ruleId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
