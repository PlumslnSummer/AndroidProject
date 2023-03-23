package com.example.compete6.bean;

public class AddTousuBean {

    /**
     * companyId : 7
     * infoNo : ST0000001
     * questionType : 1
     * description : 帮我看看这个快递到底是怎么回事，谢谢。
     */

    private int companyId;
    private String infoNo;
    private String questionType;
    private String description;

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getInfoNo() {
        return infoNo;
    }

    public void setInfoNo(String infoNo) {
        this.infoNo = infoNo;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
