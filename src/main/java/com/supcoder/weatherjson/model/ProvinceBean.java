package com.supcoder.weatherjson.model;

import java.util.List;

/**
 * ProvinceBean
 *
 * @author lee
 * @date 2020-05-23
 */
public class ProvinceBean {

    private String name;

    private List<CityBean> city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityBean> getCity() {
        return city;
    }

    public void setCity(List<CityBean> city) {
        this.city = city;
    }
}
