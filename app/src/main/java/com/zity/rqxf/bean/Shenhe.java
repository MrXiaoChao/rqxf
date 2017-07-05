package com.zity.rqxf.bean;

/**
 * Created by luochao on 2017/7/4.
 * 延期审核
 */

public class Shenhe {

    /**
     * person : 组织部办案人
     * preprolongtime : 2
     * opinion : 延期申请测试
     */

    private String person;
    private int preprolongtime;
    private String opinion;

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public int getPreprolongtime() {
        return preprolongtime;
    }

    public void setPreprolongtime(int preprolongtime) {
        this.preprolongtime = preprolongtime;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }
}
