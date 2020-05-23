package com.supcoder.weatherjson.model;

import java.util.List;

/**
 * CityBean
 *
 * @author lee
 * @date 2020-05-23
 */
public class CityBean {

    private String name;

    private List<AreaInfoBean> area;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AreaInfoBean> getArea() {
        return area;
    }

    public void setArea(List<AreaInfoBean> area) {
        this.area = area;
    }
}
