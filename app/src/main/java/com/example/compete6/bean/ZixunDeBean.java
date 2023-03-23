package com.example.compete6.bean;

public class ZixunDeBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"searchValue":null,"createBy":null,"createTime":"2022-07-27 17:46:35","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":198,"fromUserId":1111241,"lawyerId":10,"legalExpertiseId":7,"content":"要把沙发装冰箱，总共分几步？","imageUrls":"/dev-api/profile/upload/image/2022/02/25/19d10a51-2950-46b0-ad70-daf7c5160320.jpg,/dev-api/profile/upload/image/2022/02/25/7dd5505a-8ffb49d5-81e2-58a66f08d34a.png","phone":"18842656666","state":"0","score":0,"evaluate":null,"lawyerName":"陈宇律师","legalExpertiseName":null,"likeCount":null}
     */

    private String msg;
    private int code;
    /**
     * searchValue : null
     * createBy : null
     * createTime : 2022-07-27 17:46:35
     * updateBy : null
     * updateTime : null
     * remark : null
     * params : {}
     * id : 198
     * fromUserId : 1111241
     * lawyerId : 10
     * legalExpertiseId : 7
     * content : 要把沙发装冰箱，总共分几步？
     * imageUrls : /dev-api/profile/upload/image/2022/02/25/19d10a51-2950-46b0-ad70-daf7c5160320.jpg,/dev-api/profile/upload/image/2022/02/25/7dd5505a-8ffb49d5-81e2-58a66f08d34a.png
     * phone : 18842656666
     * state : 0
     * score : 0
     * evaluate : null
     * lawyerName : 陈宇律师
     * legalExpertiseName : null
     * likeCount : null
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
        private int fromUserId;
        private int lawyerId;
        private int legalExpertiseId;
        private String content;
        private String imageUrls;
        private String phone;
        private String state;
        private int score;
        private Object evaluate;
        private String lawyerName;
        private Object legalExpertiseName;
        private Object likeCount;

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

        public int getFromUserId() {
            return fromUserId;
        }

        public void setFromUserId(int fromUserId) {
            this.fromUserId = fromUserId;
        }

        public int getLawyerId() {
            return lawyerId;
        }

        public void setLawyerId(int lawyerId) {
            this.lawyerId = lawyerId;
        }

        public int getLegalExpertiseId() {
            return legalExpertiseId;
        }

        public void setLegalExpertiseId(int legalExpertiseId) {
            this.legalExpertiseId = legalExpertiseId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImageUrls() {
            return imageUrls;
        }

        public void setImageUrls(String imageUrls) {
            this.imageUrls = imageUrls;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public Object getEvaluate() {
            return evaluate;
        }

        public void setEvaluate(Object evaluate) {
            this.evaluate = evaluate;
        }

        public String getLawyerName() {
            return lawyerName;
        }

        public void setLawyerName(String lawyerName) {
            this.lawyerName = lawyerName;
        }

        public Object getLegalExpertiseName() {
            return legalExpertiseName;
        }

        public void setLegalExpertiseName(Object legalExpertiseName) {
            this.legalExpertiseName = legalExpertiseName;
        }

        public Object getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(Object likeCount) {
            this.likeCount = likeCount;
        }
    }
}
