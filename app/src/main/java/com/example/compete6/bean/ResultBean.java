package com.example.compete6.bean;

import java.util.List;

public class ResultBean {

    /**
     * total : 13
     * rows : [{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":11,"type":8,"name":"报纸","imgUrl":"/prod-api/profile/upload/image/2022/03/14/0095ccf4-6a6f-4808-b945-9418efc5013b.jpeg"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":12,"type":8,"name":"纸箱","imgUrl":"/prod-api/profile/upload/image/2022/03/14/4de46b09-3fed-47a2-a006-96656af8db69.jpeg"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":13,"type":8,"name":"书本","imgUrl":"/prod-api/profile/upload/image/2022/03/14/290b010e-b084-46d4-bd53-40da9ff8a1cc.jpeg"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":14,"type":8,"name":"塑料瓶","imgUrl":"/prod-api/profile/upload/image/2022/03/14/0c1daebe-052a-4f48-9835-263d8ad988ae.jpeg"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":15,"type":8,"name":"油桶","imgUrl":"/prod-api/profile/upload/image/2022/03/14/955d61b3-691f-419c-9595-b7d41eb665e7.jpeg"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":16,"type":8,"name":"衣架等塑料制品","imgUrl":"/prod-api/profile/upload/image/2022/03/14/97fb2630-c036-48ab-a40d-6542ab0ecf87.jpeg"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":17,"type":8,"name":"玻璃瓶","imgUrl":"/prod-api/profile/upload/image/2022/03/14/459edb70-08cc-417a-b020-aa245b9f2a15.jpeg"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":18,"type":8,"name":"酒瓶","imgUrl":"/prod-api/profile/upload/image/2022/03/14/e8c9a01f-246f-4394-95ff-b28111a37101.jpeg"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":19,"type":8,"name":"易拉罐","imgUrl":"/prod-api/profile/upload/image/2022/03/14/2b7a95fb-0b00-481a-8149-77523395967d.jpeg"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":20,"type":8,"name":"锅勺","imgUrl":"/prod-api/profile/upload/image/2022/03/14/2df973aa-90fd-415f-974c-28958dcd05f7.jpeg"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":21,"type":8,"name":"刀具","imgUrl":"/prod-api/profile/upload/image/2022/03/14/33b8478c-1084-40b3-b445-1e87ccda754b.jpeg"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":22,"type":8,"name":"毛绒玩具","imgUrl":"/prod-api/profile/upload/image/2022/03/14/f3a503fb-af50-441b-aaaa-26ece0f506e1.jpeg"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":23,"type":8,"name":"插座","imgUrl":"/prod-api/profile/upload/image/2022/03/14/310bd44c-c22a-434f-bf9a-eeb2df8efbd8.jpeg"}]
     * code : 200
     * msg : 查询成功
     */

    private int total;
    private int code;
    private String msg;
    /**
     * searchValue : null
     * createBy : null
     * createTime : null
     * updateBy : null
     * updateTime : null
     * remark : null
     * params : {}
     * id : 11
     * type : 8
     * name : 报纸
     * imgUrl : /prod-api/profile/upload/image/2022/03/14/0095ccf4-6a6f-4808-b945-9418efc5013b.jpeg
     */

    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

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

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        private Object searchValue;
        private Object createBy;
        private Object createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private int id;
        private int type;
        private String name;
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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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
    }
}
