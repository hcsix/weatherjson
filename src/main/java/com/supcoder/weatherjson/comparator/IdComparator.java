package com.supcoder.weatherjson.comparator;

import com.supcoder.weatherjson.AreaInfoBean;

import java.util.Comparator;

/**
 * IdComparator
 * 通过ID排序
 *
 * @author lee
 * @date 2020-05-23
 */
public class IdComparator implements Comparator<AreaInfoBean> {


    public int compare(AreaInfoBean o1, AreaInfoBean o2) {
        return o1.getId() - o2.getId();
    }
}
