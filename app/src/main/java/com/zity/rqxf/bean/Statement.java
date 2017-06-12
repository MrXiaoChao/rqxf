package com.zity.rqxf.bean;

/**
 * Created by luochao on 2017/6/12.
 * 案件统计
 */

public class Statement {

    /**
     * statistical_type : 3bea67a3-2190-45a1-aea5-4d5519266743
     * count : 5
     * name : 信访局
     * emg : org
     */

    private String statistical_type;
    private int count;
    private String name;
    private String emg;

    public String getStatistical_type() {
        return statistical_type;
    }

    public void setStatistical_type(String statistical_type) {
        this.statistical_type = statistical_type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmg() {
        return emg;
    }

    public void setEmg(String emg) {
        this.emg = emg;
    }
}
