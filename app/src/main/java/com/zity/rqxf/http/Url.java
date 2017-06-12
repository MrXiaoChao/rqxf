package com.zity.rqxf.http;

/**
 * Created by luochao on 2017/5/25.
 * 接口地址
 */

public class Url {
    //登录
    public static String LOGIN="http://211.151.183.170:8096/petition/Mobile/login.do?";
    //乡镇办件统计
    public static String XZBJ="http://211.151.183.170:8096/petition/Mobile/getNumByOrg.do?";
    //按信访情况办件统计
    public static String XFQK="http://211.151.183.170:8096/petition/Mobile/getNumByAppeal.do?";
    //按时间办件统计
    public static String TIME="http://211.151.183.170:8096/petition/Mobile/getNumByTime.do?";
    //按办理状态统计
    public static String BLZT="http://211.151.183.170:8096/petition/Mobile/getNumByCases.do?";
    //案件统计列表
    public static String AJTJLB=" http://211.151.183.170:8096/petition/Mobile/getlist.do?";

}
