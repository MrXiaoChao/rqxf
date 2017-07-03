package com.zity.rqxf.bean;

/**
 * Created by luochao on 2017/6/12.
 */

public class User {

    /**
     * uName : 组织部市包案领导
     * level : 1   1代表普通用户  0代表领导
     * status : true
     * userId : e9253c15-2da9-4aff-bfc1-f418f7a0f643
     * postionName : 市包案领导
     * orgName : 组织部
     * msg : 登录成功！
     */
    private String uName;
    private String level;
    private String status;
    private String userId;
    private String postionName;
    private String orgName;
    private String msg;

    public String getUName() {
        return uName;
    }

    public void setUName(String uName) {
        this.uName = uName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostionName() {
        return postionName;
    }

    public void setPostionName(String postionName) {
        this.postionName = postionName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
