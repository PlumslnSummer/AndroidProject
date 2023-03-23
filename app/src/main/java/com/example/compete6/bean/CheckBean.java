package com.example.compete6.bean;

public class CheckBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":2,"userId":2,"mostEducation":"博士","education":"大专","address":"北京","experience":"2年以上","individualResume":"热情随和，活波开朗，具有进取精神和团队精神，有较强的动手能力。良好协调沟通能力，适应力强，反应快、积极、灵活，爱创新!两年的会计经历锻炼了我细心的准则，以及冷静解决困难的能力。不过参加实践活动有限，社会经验相对缺乏，我相信通过立足于社会能不断提升发展自己。","money":"12000","positionId":2,"files":"/profile/2020/11/03/95474274-4c4d-467c-94a1-9959521abee4.docx","positionName":null,"userName":null}
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
     * id : 2
     * userId : 2
     * mostEducation : 博士
     * education : 大专
     * address : 北京
     * experience : 2年以上
     * individualResume : 热情随和，活波开朗，具有进取精神和团队精神，有较强的动手能力。良好协调沟通能力，适应力强，反应快、积极、灵活，爱创新!两年的会计经历锻炼了我细心的准则，以及冷静解决困难的能力。不过参加实践活动有限，社会经验相对缺乏，我相信通过立足于社会能不断提升发展自己。
     * money : 12000
     * positionId : 2
     * files : /profile/2020/11/03/95474274-4c4d-467c-94a1-9959521abee4.docx
     * positionName : null
     * userName : null
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
        private int userId;
        private String mostEducation;
        private String education;
        private String address;
        private String experience;
        private String individualResume;
        private String money;
        private int positionId;
        private String files;
        private Object positionName;
        private Object userName;

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

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getMostEducation() {
            return mostEducation;
        }

        public void setMostEducation(String mostEducation) {
            this.mostEducation = mostEducation;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getExperience() {
            return experience;
        }

        public void setExperience(String experience) {
            this.experience = experience;
        }

        public String getIndividualResume() {
            return individualResume;
        }

        public void setIndividualResume(String individualResume) {
            this.individualResume = individualResume;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public int getPositionId() {
            return positionId;
        }

        public void setPositionId(int positionId) {
            this.positionId = positionId;
        }

        public String getFiles() {
            return files;
        }

        public void setFiles(String files) {
            this.files = files;
        }

        public Object getPositionName() {
            return positionName;
        }

        public void setPositionName(Object positionName) {
            this.positionName = positionName;
        }

        public Object getUserName() {
            return userName;
        }

        public void setUserName(Object userName) {
            this.userName = userName;
        }
    }
}
