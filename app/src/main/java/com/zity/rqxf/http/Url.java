package com.zity.rqxf.http;

/**
 * Created by luochao on 2017/5/25.
 * 接口地址
 */

public class Url {
//    public static String BaseURL="http://220.195.3.15:8080";
    public static String BaseURL="http://211.151.183.170:8096";
    //登录
    public static String LOGIN=BaseURL+"/petition/Mobile/login.do?";
    //乡镇办件统计
    public static String XZBJ=BaseURL+"/petition/Mobile/getNumByOrg.do?";
    //按信访情况办件统计
    public static String XFQK=BaseURL+"/petition/Mobile/getNumByAppeal.do?";
    //按时间办件统计
    public static String TIME=BaseURL+"/petition/Mobile/getNumByTime.do?";
    //按办理状态统计
    public static String BLZT=BaseURL+"/petition/Mobile/getNumByCases.do?";
    //案件统计列表
    public static String AJTJLB=BaseURL+"/petition/Mobile/getlist.do?";
    //信访条例
    public static String XFTL=BaseURL+"/petition/views/back/gonggao/ruleInfo.jsp";
    //各部门职责列表
    public static String GBMZZLB=BaseURL+"/petition/Mobile/getSectorList.do";
    //各部门职责列表详情
    public static String GBMZZXQ=BaseURL+"/petition/views/back/gonggao/info.jsp?";
    //代办列表
    public static String DBLB=BaseURL+"/petition/Mobile/getDaibanListByUserId.do?";
    //地区分类
    public static String DQFL=BaseURL+"/petition/Mobile/showArea.do?";
    //信访详情
    public static String XFXQ=BaseURL+"/petition/Mobile/letterInfoById.do?";
}
