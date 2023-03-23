package com.example.compete6.bean;

public class AddJianBean {

    /**
     * mostEducation : 博士
     * education : 本科
     * address : 北京
     * experience : 1 年
     * individualResume : 性格有点内向、乐观上进、有爱心并善于施教并行;对待工作认真负责，善于沟通、协调有较强的组织能力与团队精神;上进心强、勤于学习能不断进步自身的能力与综合素质。在未来的工作中，我将以充沛的精力，刻苦钻研的精神来努力工作，稳定地进步自己的工作能力，与公司同步发展。
     * money : 7000
     * positionId : 2
     * files : /dev-apihttp://localhost:9090/profile/upload/2021/04/22/1ac5e66a-0381-4867-9026-3ec00fff3ecf.pdf
     */

    private String mostEducation;
    private String education;
    private String address;
    private String experience;
    private String individualResume;
    private String money;
    private int positionId;
    private String files;

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
}
