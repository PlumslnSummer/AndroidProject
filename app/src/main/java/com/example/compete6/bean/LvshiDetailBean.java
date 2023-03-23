package com.example.compete6.bean;

public class LvshiDetailBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":10,"name":"陈宇律师","legalExpertiseId":10,"avatarUrl":"/prod-api/profile/upload/2022/03/12/81ea936c-9c26-402a-93d8-77328fbf6bf6.jpeg","baseInfo":"陈宇律师法律功底深厚，实践经验丰富，业务涵盖诉讼和非诉讼，在执业前曾担任上市公司法务多年，先后参与筹办多家企业，参与大型BOT项目的谈判、筹建、运营等，故特别擅长一揽子解决复杂案件和系列案，对企业法律、海域生态资源征迁补偿、行政诉讼等有专长。此外，陈宇律师还担任多家企业法律顾问，办理过大量民商事、刑事案件，均效果良好。","eduInfo":"陈宇律师，毕业于厦门大学，现任福建汇德律师事务所主任、书记，系中国执业律师，福州市国有企业法律顾问智库成员，第十届福建省律协破产重组法律专业委员会委员，第六届福州市律协行业规则委员会委员，第七届福州市律协理事会理事，\u201c2017-2018年度福建省优秀律师\u201d，福州市法律服务行业\u201c2018-2019年度优秀共产党员\u201d，投资移民律师，2020年获评\u201c行政法专业律师\u201d。陈宇律师最先执业于福建元一律师事务所，2011年参与创办福建熹龙律师事务所，2015年任书记，2016年入伙福建汇德律师事务所，现为该所主任、书记、合伙人、专职律师。","licenseNo":"13501201110466094","certificateImgUrl":"/dev-api/profile/upload/2022/03/12/7577678b-132c-441e-86c3-5922b6857fce.jpeg","workStartAt":"2021-10-12","serviceTimes":83,"favorableRate":100,"legalExpertiseName":"拆迁安置","favorableCount":1,"sort":"favorableRate"}
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
     * id : 10
     * name : 陈宇律师
     * legalExpertiseId : 10
     * avatarUrl : /prod-api/profile/upload/2022/03/12/81ea936c-9c26-402a-93d8-77328fbf6bf6.jpeg
     * baseInfo : 陈宇律师法律功底深厚，实践经验丰富，业务涵盖诉讼和非诉讼，在执业前曾担任上市公司法务多年，先后参与筹办多家企业，参与大型BOT项目的谈判、筹建、运营等，故特别擅长一揽子解决复杂案件和系列案，对企业法律、海域生态资源征迁补偿、行政诉讼等有专长。此外，陈宇律师还担任多家企业法律顾问，办理过大量民商事、刑事案件，均效果良好。
     * eduInfo : 陈宇律师，毕业于厦门大学，现任福建汇德律师事务所主任、书记，系中国执业律师，福州市国有企业法律顾问智库成员，第十届福建省律协破产重组法律专业委员会委员，第六届福州市律协行业规则委员会委员，第七届福州市律协理事会理事，“2017-2018年度福建省优秀律师”，福州市法律服务行业“2018-2019年度优秀共产党员”，投资移民律师，2020年获评“行政法专业律师”。陈宇律师最先执业于福建元一律师事务所，2011年参与创办福建熹龙律师事务所，2015年任书记，2016年入伙福建汇德律师事务所，现为该所主任、书记、合伙人、专职律师。
     * licenseNo : 13501201110466094
     * certificateImgUrl : /dev-api/profile/upload/2022/03/12/7577678b-132c-441e-86c3-5922b6857fce.jpeg
     * workStartAt : 2021-10-12
     * serviceTimes : 83
     * favorableRate : 100
     * legalExpertiseName : 拆迁安置
     * favorableCount : 1
     * sort : favorableRate
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
        private int legalExpertiseId;
        private String avatarUrl;
        private String baseInfo;
        private String eduInfo;
        private String licenseNo;
        private String certificateImgUrl;
        private String workStartAt;
        private int serviceTimes;
        private int favorableRate;
        private String legalExpertiseName;
        private int favorableCount;
        private String sort;

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

        public int getLegalExpertiseId() {
            return legalExpertiseId;
        }

        public void setLegalExpertiseId(int legalExpertiseId) {
            this.legalExpertiseId = legalExpertiseId;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public String getBaseInfo() {
            return baseInfo;
        }

        public void setBaseInfo(String baseInfo) {
            this.baseInfo = baseInfo;
        }

        public String getEduInfo() {
            return eduInfo;
        }

        public void setEduInfo(String eduInfo) {
            this.eduInfo = eduInfo;
        }

        public String getLicenseNo() {
            return licenseNo;
        }

        public void setLicenseNo(String licenseNo) {
            this.licenseNo = licenseNo;
        }

        public String getCertificateImgUrl() {
            return certificateImgUrl;
        }

        public void setCertificateImgUrl(String certificateImgUrl) {
            this.certificateImgUrl = certificateImgUrl;
        }

        public String getWorkStartAt() {
            return workStartAt;
        }

        public void setWorkStartAt(String workStartAt) {
            this.workStartAt = workStartAt;
        }

        public int getServiceTimes() {
            return serviceTimes;
        }

        public void setServiceTimes(int serviceTimes) {
            this.serviceTimes = serviceTimes;
        }

        public int getFavorableRate() {
            return favorableRate;
        }

        public void setFavorableRate(int favorableRate) {
            this.favorableRate = favorableRate;
        }

        public String getLegalExpertiseName() {
            return legalExpertiseName;
        }

        public void setLegalExpertiseName(String legalExpertiseName) {
            this.legalExpertiseName = legalExpertiseName;
        }

        public int getFavorableCount() {
            return favorableCount;
        }

        public void setFavorableCount(int favorableCount) {
            this.favorableCount = favorableCount;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }
    }
}
