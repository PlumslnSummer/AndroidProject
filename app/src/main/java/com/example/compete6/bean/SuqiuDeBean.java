package com.example.compete6.bean;

public class SuqiuDeBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"searchValue":null,"createBy":null,"createTime":"2022-03-12 07:36:00","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":7,"userId":1,"appealCategoryId":8,"title":"积分落户政策","content":"请问最新的积分落户政策是怎么样的？","undertaker":"人社局","state":"0","detailResult":null,"imgUrl":null,"appealCategoryName":"积分落户"}
     */

    private String msg;
    private int code;
    /**
     * searchValue : null
     * createBy : null
     * createTime : 2022-03-12 07:36:00
     * updateBy : null
     * updateTime : null
     * remark : null
     * params : {}
     * id : 7
     * userId : 1
     * appealCategoryId : 8
     * title : 积分落户政策
     * content : 请问最新的积分落户政策是怎么样的？
     * undertaker : 人社局
     * state : 0
     * detailResult : null
     * imgUrl : null
     * appealCategoryName : 积分落户
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
        private Object createBy;
        private String createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private int id;
        private int userId;
        private int appealCategoryId;
        private String title;
        private String content;
        private String undertaker;
        private String state;
        private Object detailResult;
        private Object imgUrl;
        private String appealCategoryName;

        public Object getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(Object searchValue) {
            this.searchValue = searchValue;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
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

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getAppealCategoryId() {
            return appealCategoryId;
        }

        public void setAppealCategoryId(int appealCategoryId) {
            this.appealCategoryId = appealCategoryId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUndertaker() {
            return undertaker;
        }

        public void setUndertaker(String undertaker) {
            this.undertaker = undertaker;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public Object getDetailResult() {
            return detailResult;
        }

        public void setDetailResult(Object detailResult) {
            this.detailResult = detailResult;
        }

        public Object getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(Object imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getAppealCategoryName() {
            return appealCategoryName;
        }

        public void setAppealCategoryName(String appealCategoryName) {
            this.appealCategoryName = appealCategoryName;
        }
    }
}
