package com.zity.rqxf.http;

/**
 * Created by luochao on 2017/5/25.
 * 接口地址
 */

public class Url {
//    public static String BaseURL="http://220.195.3.15:8080";
    public static String BaseURL="http://211.151.183.170:8096/petition";
    //登录
    public static String LOGIN=BaseURL+"/Mobile/login.do?";
    //乡镇办件统计
    public static String XZBJ=BaseURL+"/Mobile/getNumByOrg.do?";
    //按信访情况办件统计
    public static String XFQK=BaseURL+"/Mobile/getNumByAppeal.do?";
    //按时间办件统计
    public static String TIME=BaseURL+"/Mobile/getNumByTime.do?";
    //按办理状态统计
    public static String BLZT=BaseURL+"/Mobile/getNumByCases.do?";
    //案件统计列表
    public static String AJTJLB=BaseURL+"/Mobile/getlist.do?";
    //信访条例
    public static String XFTL=BaseURL+"/views/back/gonggao/ruleInfo.jsp";
    //各部门职责列表
    public static String GBMZZLB=BaseURL+"/Mobile/getSectorList.do";
    //各部门职责列表详情
    public static String GBMZZXQ=BaseURL+"/views/back/gonggao/info.jsp?";
    //代办列表
    public static String DBLB=BaseURL+"/Mobile/getDaibanListByUserId.do?";
    //地区分类
    public static String DQFL=BaseURL+"/Mobile/showArea.do?";
    //信访详情
    public static String XFXQ=BaseURL+"/Mobile/letterInfoById.do?";
    //延期申请
    public static String XQSQ=BaseURL+"/Mobile/deferredApply.do?";
    //延期审核回显
    public static String XQSHHX=BaseURL+"/Mobile/deferredList.do?";
    //延期审核申请
    public static String XQSHSQ=BaseURL+"/Mobile/deferred.do?";
    //拟办申请
    public static String NBSQ=BaseURL+"/Mobile/unitCommentsApply.do?";
    //处理意见审核回显
    public static String SHHX=BaseURL+"/Mobile/ideaList.do?";
    //拟办申请审核
    public static String NBSQSH=BaseURL+"/Mobile/saveIdea.do?";
    //接案申请前三个框的回显信息
    public static String JASQHX=BaseURL+"/Mobile/finalListOfApply.do?";
    //结案申请
    public static String JASQ=BaseURL+"/Mobile/unitTodone.do?";
    //结案审核回显
    public static String JASHHX=BaseURL+"/Mobile/finalList.do?";
    //结案审核提交
    public static String JASHTJ=BaseURL+"/Mobile/saveFinal.do?";
    //接收案件
    public static String JIESHOU=BaseURL+"/Mobile/takeLetter.do?";
    //办案人受理案件
    public static String SLAN=BaseURL+"/Mobile/updateContentChild.do?";
    //办案人退回案件
    public static String THAN=BaseURL+"/Mobile/caseReturn.do?";
    //修改责任人回显
    public static String XGZRRHX=BaseURL+"/Mobile/showPrincipal.do?";
}
