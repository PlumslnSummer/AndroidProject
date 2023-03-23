package com.example.compete6.bean;

public class RencaiDeBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":7,"name":"大连市中山区","imgUrl":"/prod-api/profile/upload/image/2022/03/14/466cb9ef-4fc3-458a-8499-27a1b7101445.jpeg","introduce":"中山区，隶属于辽宁省大连市，是大连市的金融和商业中心。中山区位于大连市区东部。截至2018年，中山区陆地面积47.41平方千米，海岸线和岛岸线总长42.53千米  。属海洋性暖温带季风气候，辖9个街道，政府驻地位于桂林街道。根据第七次全国人口普查数据，截至2020年11月1日零时，中山区常住人口388564人。"}
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
     * name : 大连市中山区
     * imgUrl : /prod-api/profile/upload/image/2022/03/14/466cb9ef-4fc3-458a-8499-27a1b7101445.jpeg
     * introduce : 中山区，隶属于辽宁省大连市，是大连市的金融和商业中心。中山区位于大连市区东部。截至2018年，中山区陆地面积47.41平方千米，海岸线和岛岸线总长42.53千米  。属海洋性暖温带季风气候，辖9个街道，政府驻地位于桂林街道。根据第七次全国人口普查数据，截至2020年11月1日零时，中山区常住人口388564人。
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
        private Object createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private int id;
        private String name;
        private String imgUrl;
        private String introduce;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }
    }
}
