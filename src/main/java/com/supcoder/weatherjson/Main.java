package com.supcoder.weatherjson;

import com.supcoder.weatherjson.comparator.AreaComparator;
import com.supcoder.weatherjson.model.AreaInfoBean;
import com.supcoder.weatherjson.utils.AreaUtil;
import com.supcoder.weatherjson.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.Collections;
import java.util.List;

/**
 * Main
 *
 * @author lee
 * @date 2020-05-23
 */
public class Main {

    public static void main(String[] args) {
        ExcelUtil.parseExcel("src/main/resources/城市ID.xls", new ExcelUtil.DealWorkBookExecutor() {

            public void dealWorkBook(Workbook workBook) {
                List<AreaInfoBean> areaList = AreaUtil.workBookToAreaInfoList(workBook);
                Collections.sort(areaList, new AreaComparator());
                AreaUtil.areaInfoToJson(areaList);

            }
        });
    }


}
