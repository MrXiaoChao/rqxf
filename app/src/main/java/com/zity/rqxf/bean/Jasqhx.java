package com.zity.rqxf.bean;

/**
 * Created by luochao on 2017/7/5.
 * 结案申请回显
 */

public class Jasqhx {

    /**
     * setTime : 2017-07-11 00:00:00.0
     * content : null
     * opinion : 办理意见申请*办理情况
     * code : 201700007-002
     * petitionerContent : daland
     */

    private String setTime;
    private Object content;
    private String opinion;
    private String code;
    private String petitionerContent;

    public String getSetTime() {
        return setTime;
    }

    public void setSetTime(String setTime) {
        this.setTime = setTime;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPetitionerContent() {
        return petitionerContent;
    }

    public void setPetitionerContent(String petitionerContent) {
        this.petitionerContent = petitionerContent;
    }
}
