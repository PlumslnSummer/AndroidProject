package com.example.compete6.bean;

import java.util.List;

public class ZhiyuanBannerBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : [{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":7,"title":"县委党校开展为翁添绿为党增彩义务植树志愿活动","sort":1,"imgUrl":"/prod-api/profile/upload/image/2022/03/13/57720336-7f5a-43fa-aa6b-640a3c3d329f.jpeg"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":8,"title":"省检一分院团支部开展植树造林志愿服务活动","sort":2,"imgUrl":"/prod-api/profile/upload/image/2022/03/13/e249bbcd-782f-4cb6-bcee-ba828769f953.jpeg"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":9,"title":"志愿建功行喜迎二十大2022年南澳县学雷锋志愿服务广场活动","sort":3,"imgUrl":"/prod-api/profile/upload/image/2022/03/13/3855eb7e-27eb-44a0-afce-14df55601c45.jpeg"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":10,"title":"省检一分院团支部开展植树造林志愿服务活动","sort":4,"imgUrl":"/prod-api/profile/upload/image/2022/03/13/c162c0f5-1121-4980-88f1-a3aa9988bfb0.jpeg"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":11,"title":"湖北孝感：加快植树造林助推国家森林城市创建","sort":5,"imgUrl":"/prod-api/profile/upload/image/2022/03/13/15b18642-df9c-405e-bad2-e3dca20badd3.jpeg"}]
     */

    private String msg;
    private int code;
    /**
     * searchValue : null
     * createBy : null
     * createTime : null
     * updateBy : null
     * updateTime : null
     * remark : null
     * params : {}
     * id : 7
     * title : 县委党校开展为翁添绿为党增彩义务植树志愿活动
     * sort : 1
     * imgUrl : /prod-api/profile/upload/image/2022/03/13/57720336-7f5a-43fa-aa6b-640a3c3d329f.jpeg
     */

    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private Object searchValue;
        private Object createBy;
        private Object createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private int id;
        private String title;
        private int sort;
        private String imgUrl;

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

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
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

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }
}
