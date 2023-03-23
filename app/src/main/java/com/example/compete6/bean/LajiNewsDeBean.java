package com.example.compete6.bean;

public class LajiNewsDeBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"searchValue":null,"createBy":null,"createTime":"2022-03-14 07:54:52","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":13,"type":7,"title":"邢台市首个垃圾分类宣教站启用","imgUrl":"/prod-api/profile/upload/image/2022/03/14/3a1b689e-b2f7-48ee-8e73-80e84e1828fd.jpeg","content":"<p><span style=\"color: rgb(0, 0, 0);\">1月17日，邢台市党政机关垃圾分类宣教站正式启用，为我市垃圾分类工作提供了一个强化环保意识、培养分类习惯的教育基地和前沿阵地。这也是我市首个以垃圾分类为主题的宣教站。<\/span><\/p><p><br><\/p><p><span class=\"ql-cursor\">﻿<\/span>邢台市党政机关垃圾分类宣教站位于市政府顺德院，由市机关后勤服务中心和市城管局联合创建，旨在充分发挥党政机关等公共机构在垃圾分类工作中的示范引领作用，进一步带动全社会共同参与到垃圾分类的活动中来，争当垃圾分类的倡导者、践行者和推广者。<\/p><p> 该宣教站建筑面积约50平方米，分宣传、教育、互动体验以及全市生活垃圾分类工作进展等板块，以文字、图片、视频、实物等方式，充分展示垃圾分类重要性、分类方法及后期处置过程，引导广大党政机关干部带头参与到垃圾分类减量化、资源化、无害化活动中去。<\/p><p> 邢台市机关后勤服务中心党组书记、主任陈德礼表示：\u201c我们将充分发挥宣教阵地作用，坚持宣教先行，辐射全体，分期分批强化培训，进一步提升党政机关干部职工参与生活垃圾分类的积极性和投放准确率。\u201d<\/p><p><br><\/p>","author":"李博"}
     */

    private String msg;
    private int code;
    /**
     * searchValue : null
     * createBy : null
     * createTime : 2022-03-14 07:54:52
     * updateBy : null
     * updateTime : null
     * remark : null
     * params : {}
     * id : 13
     * type : 7
     * title : 邢台市首个垃圾分类宣教站启用
     * imgUrl : /prod-api/profile/upload/image/2022/03/14/3a1b689e-b2f7-48ee-8e73-80e84e1828fd.jpeg
     * content : <p><span style="color: rgb(0, 0, 0);">1月17日，邢台市党政机关垃圾分类宣教站正式启用，为我市垃圾分类工作提供了一个强化环保意识、培养分类习惯的教育基地和前沿阵地。这也是我市首个以垃圾分类为主题的宣教站。</span></p><p><br></p><p><span class="ql-cursor">﻿</span>邢台市党政机关垃圾分类宣教站位于市政府顺德院，由市机关后勤服务中心和市城管局联合创建，旨在充分发挥党政机关等公共机构在垃圾分类工作中的示范引领作用，进一步带动全社会共同参与到垃圾分类的活动中来，争当垃圾分类的倡导者、践行者和推广者。</p><p> 该宣教站建筑面积约50平方米，分宣传、教育、互动体验以及全市生活垃圾分类工作进展等板块，以文字、图片、视频、实物等方式，充分展示垃圾分类重要性、分类方法及后期处置过程，引导广大党政机关干部带头参与到垃圾分类减量化、资源化、无害化活动中去。</p><p> 邢台市机关后勤服务中心党组书记、主任陈德礼表示：“我们将充分发挥宣教阵地作用，坚持宣教先行，辐射全体，分期分批强化培训，进一步提升党政机关干部职工参与生活垃圾分类的积极性和投放准确率。”</p><p><br></p>
     * author : 李博
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
        private int type;
        private String title;
        private String imgUrl;
        private String content;
        private String author;

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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }
    }
}
