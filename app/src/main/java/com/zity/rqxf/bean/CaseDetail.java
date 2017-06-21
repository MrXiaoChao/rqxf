package com.zity.rqxf.bean;

import java.util.List;

/**
 * Created by luochao on 2017/6/20.
 * 案件详情
 */

public class CaseDetail {

    /**
     * phone : 13212321232
     * status : 999
     * repeats : 初
     * stage : 2017-06-16--卫生部--责任单位接收
     2017-06-10--信访局--信访局处理
     2017-06-09--卫生部--责任单位退回
     2017-06-09--卫生部--责任单位接收
     2017-06-09--信访局--结案申请通过
     2017-06-09--组织部--责任单位结案申请
     2017-06-09--信访局--处理意见审核通过
     2017-06-09--组织部--责任单位处理意见申请
     2017-06-09--信访局--延期申请审核通过
     2017-06-09--组织部--责任单位延期申请
     2017-06-09--组织部--责任单位受理
     2017-06-09--组织部--责任单位接收
     2017-06-09--信访局--信访局处理

     * zenrenren : weqweqw
     * content : owowoow
     * functionary : [{"baoprincipals":"4546","baoleader":"2342","bananren":"组织部办案人","municipalMeaders":"2342"},{"baoprincipals":"guichongsunzi2","baoleader":"guisunzi","bananren":"组织部办案人","municipalMeaders":"guierzi"},{"baoprincipals":"","baoleader":"","bananren":"","municipalMeaders":""}]
     * problemPossession : owowoow
     * address : owowoow
     * letterProperty : 领导访
     * name : owowoow
     * appeal : 本市信访
     * documentNumber : 132123212321234561
     * processingDate : 1496937600000
     * comment : 点评
     */

    private String phone;
    private int status;
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
    private long processingDate;
    private String comment;
    private List<FunctionaryBean> functionary;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public long getProcessingDate() {
        return processingDate;
    }

    public void setProcessingDate(long processingDate) {
        this.processingDate = processingDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<FunctionaryBean> getFunctionary() {
        return functionary;
    }

    public void setFunctionary(List<FunctionaryBean> functionary) {
        this.functionary = functionary;
    }

    public static class FunctionaryBean {
        /**
         * baoprincipals : 4546
         * baoleader : 2342
         * bananren : 组织部办案人
         * municipalMeaders : 2342
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
            this.bananren = bananren;
        }

        public String getMunicipalMeaders() {
            return municipalMeaders;
        }

        public void setMunicipalMeaders(String municipalMeaders) {
            this.municipalMeaders = municipalMeaders;
        }
    }
}
