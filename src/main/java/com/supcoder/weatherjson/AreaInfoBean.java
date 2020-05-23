package com.supcoder.weatherjson;

/**
 * AreaInfoBean
 * 地区信息Bean
 *
 * @author lee
 * @date 2020-05-23
 */
public class AreaInfoBean {

    private String province;
    private String city;
    private String area;
    private int id;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getId() {
        return id;
    }

    public void setId(Object id) {
        if (id instanceof Integer) {
            this.id = (Integer) id;
        }
    }

    /**
     * 修正不规范的数据
     */
    public void correction() {
        if (StringUtils.isEmpty(area)) {
            if (!StringUtils.isEmpty(city)) {
                area = city;
            } else if (!StringUtils.isEmpty(province)) {
                area = province;
            }else {
                //省市区数据都没有
                return;
            }
        }
        if (StringUtils.isEmpty(city)) {
            city = area;
        }
        if (StringUtils.isEmpty(province)) {
            province = city;
        }
    }


    /**
     * 是否是脏数据
     * @return
     */
    public boolean isDirtyData(){
        return StringUtils.isEmpty(province) || StringUtils.isEmpty(city) || StringUtils.isEmpty(area);
    }

    @Override
    public String toString() {
        return "AreaInfoBean{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", id=" + id +
                '}';
    }
}
