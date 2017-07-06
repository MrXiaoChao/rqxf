package com.zity.rqxf.bean;

import java.util.List;

/**
 * Created by luochao on 2017/6/20.
 * 案件详情
 */

public class CaseDetail {

    /**
     * phone : 13582770102
     * repeats : 初
     * stage : 2017-06-22--信访局--结案申请通过
     2017-06-22--开发区--责任单位结案申请
     2017-06-22--信访局--处理意见审核通过
     2017-06-22--开发区--责任单位处理意见申请
     2017-06-22--开发区--责任单位受理
     2017-06-22--开发区--责任单位接收
     2017-06-22--信访局--信访局处理

     * zenrenren : 开发区
     * content : 关于开发区八村张顺反映村西开发区400亩地没有公开招投标被卖，且村内账上没有的问题。
     * functionary : [{"baoprincipals":"李四","baoleader":"赵振华","bananren":"开发区群众服务中心","municipalMeaders":"王晓刚"}]
     * problemPossession : 开发区
     * address : 开发区八村
     * letterProperty : 来访
     * name : 张顺
     * appeal : 本市信访
     * documentNumber : 130903198801021234
     * processingDate : 2017-06-22
     * buttonList : [{"statusId":16,"statusName":"点评"}]
     */

    private String phone;
    private String repeats;
    private String stage;
    private String zenrenren;
    private String content;
    private String problemPossession;
    private String address;
    private String letterProperty;
    private String name;
    private String appeal;
    private String documentNumber;
    private String processingDate;
    private List<FunctionaryBean> functionary;
    private List<ButtonListBean> buttonList;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRepeats() {
        return repeats;
    }

    public void setRepeats(String repeats) {
        this.repeats = repeats;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getZenrenren() {
        return zenrenren;
    }

    public void setZenrenren(String zenrenren) {
        this.zenrenren = zenrenren;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getProblemPossession() {
        return problemPossession;
    }

    public void setProblemPossession(String problemPossession) {
        this.problemPossession = problemPossession;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLetterProperty() {
        return letterProperty;
    }

    public void setLetterProperty(String letterProperty) {
        this.letterProperty = letterProperty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppeal() {
        return appeal;
    }

    public void setAppeal(String appeal) {
        this.appeal = appeal;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getProcessingDate() {
        return processingDate;
    }

    public void setProcessingDate(String processingDate) {
        this.processingDate = processingDate;
    }

    public List<FunctionaryBean> getFunctionary() {
        return functionary;
    }

    public void setFunctionary(List<FunctionaryBean> functionary) {
        this.functionary = functionary;
    }

    public List<ButtonListBean> getButtonList() {
        return buttonList;
    }

    public void setButtonList(List<ButtonListBean> buttonList) {
        this.buttonList = buttonList;
    }

    public static class FunctionaryBean {
        /**
         * baoprincipals : 李四
         * baoleader : 赵振华
         * bananren : 开发区群众服务中心
         * municipalMeaders : 王晓刚
         */

        private String baoprincipals;
        private String baoleader;
        private String bananren;
        private String municipalMeaders;

        public String getBaoprincipals() {
            return baoprincipals;
        }

        public void setBaoprincipals(String baoprincipals) {
            this.baoprincipals = baoprincipals;
        }

        public String getBaoleader() {
            return baoleader;
        }

        public void setBaoleader(String baoleader) {
            this.baoleader = baoleader;
        }

        public String getBananren() {
            return bananren;
        }

        public void setBananren(String bananren) {
            this.bananren = (bananren!=null)?bananren:"";
        }

        public String getMunicipalMeaders() {
            return municipalMeaders;
        }

        public void setMunicipalMeaders(String municipalMeaders) {
            this.municipalMeaders = municipalMeaders;
        }
    }

    public static class ButtonListBean {
        /**
         * statusId : 16
         * statusName : 点评
         */

        private int statusId;
        private String statusName;

        public int getStatusId() {
            return statusId;
        }

        public void setStatusId(int statusId) {
            this.statusId = statusId;
        }

        public String getStatusName() {
            return statusName;
        }

        public void setStatusName(String statusName) {
            this.statusName = statusName;
        }
    }
}
