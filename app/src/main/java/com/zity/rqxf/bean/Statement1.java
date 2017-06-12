package com.zity.rqxf.bean;

/**
 * Created by luochao on 2017/6/12.
 * 案件统计 因为跟statement 中的 statistical_type字段 类型不同一个是string  一个是 int
 */

public class Statement1 {

    /**
     * statistical_type : 0
     * count : 21
     * name : 新案件
     * emg : time
     */

    private int statistical_type;
    private int count;
    private String name;
    private String emg;

    public int getStatistical_type() {
        return statistical_type;
    }

    public void setStatistical_type(int statistical_type) {
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
