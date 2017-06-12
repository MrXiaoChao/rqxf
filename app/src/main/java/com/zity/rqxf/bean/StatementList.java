package com.zity.rqxf.bean;

/**
 * Created by luochao on 2017/6/12.
 * 案件统计列表
 */

public class StatementList {
    /**
     * content : 问题及诉求
     * letterId : b4483332-12c9-4a15-86ee-7c9dd3a8c483
     * processingDate : 2017-06-20
     * petitioners : 匿名
     */

    private String content;
    private String letterId;
    private String processingDate;
    private String petitioners;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLetterId() {
        return letterId;
    }

    public void setLetterId(String letterId) {
        this.letterId = letterId;
    }

    public String getProcessingDate() {
        return processingDate;
    }

    public void setProcessingDate(String processingDate) {
        this.processingDate = processingDate;
    }

    public String getPetitioners() {
        return petitioners;
    }

    public void setPetitioners(String petitioners) {
        this.petitioners = petitioners;
    }
}
