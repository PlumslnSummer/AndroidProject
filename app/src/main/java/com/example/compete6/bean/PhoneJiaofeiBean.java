package com.example.compete6.bean;

public class PhoneJiaofeiBean {

    /**
     * msg : 充值成功
     * code : 200
     * data : {"searchValue":null,"createBy":"1111241","createTime":"2022-07-28 15:49:15","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":33,"title":"话费充值","phonenumber":"13800000000","rechargeAmount":50,"paymentAmount":49,"paymentType":"电子支付","rechargeTime":"2022-07-28 15:49:15","userId":1111241}
     */

    private String msg;
    private int code;
    /**
     * searchValue : null
     * createBy : 1111241
     * createTime : 2022-07-28 15:49:15
     * updateBy : null
     * updateTime : null
     * remark : null
     * params : {}
     * id : 33
     * title : 话费充值
     * phonenumber : 13800000000
     * rechargeAmount : 50.0
     * paymentAmount : 49.0
     * paymentType : 电子支付
     * rechargeTime : 2022-07-28 15:49:15
     * userId : 1111241
     */

    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private Object searchValue;
        private String createBy;
        private String createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private int id;
        private String title;
        private String phonenumber;
        private double rechargeAmount;
        private double paymentAmount;
        private String paymentType;
        private String rechargeTime;
        private int userId;

        public Object getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(Object searchValue) {
            this.searchValue = searchValue;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPhonenumber() {
            return phonenumber;
        }

        public void setPhonenumber(String phonenumber) {
            this.phonenumber = phonenumber;
        }

        public double getRechargeAmount() {
            return rechargeAmount;
        }

        public void setRechargeAmount(double rechargeAmount) {
            this.rechargeAmount = rechargeAmount;
        }

        public double getPaymentAmount() {
            return paymentAmount;
        }

        public void setPaymentAmount(double paymentAmount) {
            this.paymentAmount = paymentAmount;
        }

        public String getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(String paymentType) {
            this.paymentType = paymentType;
        }

        public String getRechargeTime() {
            return rechargeTime;
        }

        public void setRechargeTime(String rechargeTime) {
            this.rechargeTime = rechargeTime;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
