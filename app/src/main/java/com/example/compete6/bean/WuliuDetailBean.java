package com.example.compete6.bean;

import java.util.List;

public class WuliuDetailBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":9,"name":"百世快递","sort":1,"imgUrl":"/prod-api/profile/upload/image/2022/03/14/cce0fba6-ad2c-4b12-a9a2-a860ad6f7cad.jpeg","introduce":"\u201c百世快递\u201d是一家在国内率先运用信息化手段探索快递行业转型升级之路的大型民营快递公司。 [1] \n2010年11月，杭州百世网络技术有限公司成功收购\u201c汇通快运\u201d，随后更名为\u201c百世汇通\u201d，成为百世网络旗下的知名快递品牌。\n2016年，\u201c百世汇通\u201d更名，正式以新名称\u201c百世快递\u201d面世。 [2] \n2021年《财富》中国500强排行榜，排名341。 [46] \n2021年10月29日，极兔速递以68亿元收购百世集团国内快递业务","shippingMethod":"海运，空运，陆运","phone":"18829320120","priceList":[],"newsList":[]}
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
     * id : 9
     * name : 百世快递
     * sort : 1
     * imgUrl : /prod-api/profile/upload/image/2022/03/14/cce0fba6-ad2c-4b12-a9a2-a860ad6f7cad.jpeg
     * introduce : “百世快递”是一家在国内率先运用信息化手段探索快递行业转型升级之路的大型民营快递公司。 [1]
     2010年11月，杭州百世网络技术有限公司成功收购“汇通快运”，随后更名为“百世汇通”，成为百世网络旗下的知名快递品牌。
     2016年，“百世汇通”更名，正式以新名称“百世快递”面世。 [2]
     2021年《财富》中国500强排行榜，排名341。 [46]
     2021年10月29日，极兔速递以68亿元收购百世集团国内快递业务
     * shippingMethod : 海运，空运，陆运
     * phone : 18829320120
     * priceList : []
     * newsList : []
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
        private int sort;
        private String imgUrl;
        private String introduce;
        private String shippingMethod;
        private String phone;
        private List<?> priceList;
        private List<?> newsList;

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

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getShippingMethod() {
            return shippingMethod;
        }

        public void setShippingMethod(String shippingMethod) {
            this.shippingMethod = shippingMethod;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public List<?> getPriceList() {
            return priceList;
        }

        public void setPriceList(List<?> priceList) {
            this.priceList = priceList;
        }

        public List<?> getNewsList() {
            return newsList;
        }

        public void setNewsList(List<?> newsList) {
            this.newsList = newsList;
        }
    }
}
