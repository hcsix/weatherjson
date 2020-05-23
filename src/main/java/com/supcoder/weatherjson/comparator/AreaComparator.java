package com.supcoder.weatherjson.comparator;

import com.supcoder.weatherjson.AreaInfoBean;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

/**
 * AreaComparator
 * 通过地区名字进行排序
 *
 * @author lee
 * @date 2020-05-23
 */
public class AreaComparator implements Comparator<AreaInfoBean> {
    public int compare(AreaInfoBean o1, AreaInfoBean o2) {

        Collator instance = Collator.getInstance(Locale.CHINA);
        if (!o1.getProvince().equals(o2.getProvince())) {
            return instance.compare(o1.getProvince(), o2.getProvince());
        } else if (!o1.getCity().equals(o2.getCity())) {
            return instance.compare(o1.getCity(), o2.getCity());
        } else if (!o1.getArea().equals(o2.getArea())) {
            return instance.compare(o1.getArea(), o2.getArea());
        }
        return o1.getId() - o2.getId();
    }
}
