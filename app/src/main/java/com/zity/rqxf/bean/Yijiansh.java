package com.zity.rqxf.bean;

/**
 * Created by luochao on 2017/7/4.
 * 意见审核
 */

public class Yijiansh {
    /**
     * name : 组织部办案人
     * opinion : 办理意见申请*办理情况
     * preopinion : 办理意见申请*拟办理情况
     */

    private String name;
    private String opinion;
    private String preopinion;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getPreopinion() {
        return preopinion;
    }

    public void setPreopinion(String preopinion) {
        this.preopinion = preopinion;
    }
}
