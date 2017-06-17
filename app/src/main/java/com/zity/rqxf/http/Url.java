package com.zity.rqxf.http;

/**
 * Created by luochao on 2017/5/25.
 * 接口地址
 */

public class Url {
    //登录
    public static String LOGIN="http://220.195.3.15:8080/petition/Mobile/login.do?";
    //乡镇办件统计
    public static String XZBJ="http://220.195.3.15:8080/petition/Mobile/getNumByOrg.do?";
    //按信访情况办件统计
    public static String XFQK="http://220.195.3.15:8080/petition/Mobile/getNumByAppeal.do?";
    //按时间办件统计
    public static String TIME="http://220.195.3.15:8080/petition/Mobile/getNumByTime.do?";
    //按办理状态统计
    public static String BLZT="http://220.195.3.15:8080/petition/Mobile/getNumByCases.do?";
    //案件统计列表
    public static String AJTJLB="http://220.195.3.15:8080/petition/Mobile/getlist.do?";
    //信访条例
    public static String XFTL="http://220.195.3.15:8080/petition/views/back/gonggao/ruleInfo.jsp";
    //各部门职责列表
    public static String GBMZZLB="http://220.195.3.15:8080/petition/Mobile/getSectorList.do";
    //各部门职责列表详情
    public static String GBMZZXQ="http://220.195.3.15:8080/petition/views/back/gonggao/info.jsp?";
    //代办列表
    public static String DBLB="http://220.195.3.15:8080/petition/Mobile/getDaibanListByUserId.do?";
    //地区分类
    public static String DQFL=" http://220.195.3.15:8080/petition/Mobile/showArea.do?";
}
