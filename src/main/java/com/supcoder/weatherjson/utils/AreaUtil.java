package com.supcoder.weatherjson.utils;

import com.supcoder.weatherjson.model.AreaInfoBean;
import com.supcoder.weatherjson.model.CityBean;
import com.supcoder.weatherjson.model.ProvinceBean;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * AreaUtil
 *
 * @author lee
 * @date 2020-05-23
 */
public class AreaUtil {

    private static final int COLUMN_ID = 0;
    private static final int COLUMN_PROVINCE = 1;
    private static final int COLUMN_CITY = 2;
    private static final int COLUMN_AREA = 3;


    public static List<AreaInfoBean> workBookToAreaInfoList(Workbook workBook) {
        List<AreaInfoBean> areaList = new ArrayList<AreaInfoBean>();

        // 获取第一个sheet
        Sheet sheet = workBook.getSheetAt(0);
        for (Row row : sheet) {
            AreaInfoBean areaInfoBean = new AreaInfoBean();
            // 遍历当前行的所有cell
            for (Cell cell : row) {

                switch (cell.getColumnIndex()) {
                    case COLUMN_ID:
                        areaInfoBean.setId(ExcelUtil.getCellVal(cell));
                        break;
                    case COLUMN_PROVINCE:
                        areaInfoBean.setProvince(ExcelUtil.getCellVal(cell).toString());
                        break;
                    case COLUMN_CITY:
                        areaInfoBean.setCity(ExcelUtil.getCellVal(cell).toString());
                        break;
                    case COLUMN_AREA:
                        areaInfoBean.setArea(ExcelUtil.getCellVal(cell).toString());
                        break;
                    default:
                        break;

                }
            }
            areaInfoBean.correction();
            areaList.add(areaInfoBean);
        }
        return areaList;
    }


    public static boolean areaInfoToJson(List<AreaInfoBean> areas) {
        boolean isSuc = true;
        try {
            List<List<AreaInfoBean>> provinceList = new ArrayList<List<AreaInfoBean>>();
            for (int i = 0; i < areas.size(); i++) {
                AreaInfoBean curArea = areas.get(i);
                if (i == 0) {
                    List<AreaInfoBean> areaList = new ArrayList<AreaInfoBean>();
                    areaList.add(curArea);
                    provinceList.add(areaList);
                } else {
                    AreaInfoBean preArea = areas.get(i - 1);
                    if (preArea.getProvince().equals(curArea.getProvince())) {
                        provinceList.get(provinceList.size() - 1).add(curArea);
                    } else {
                        List<AreaInfoBean> areaList = new ArrayList<AreaInfoBean>();
                        areaList.add(curArea);
                        provinceList.add(areaList);
                    }
                }
            }

            for (List<AreaInfoBean> cityList : provinceList) {
                System.out.println("---------");

                for (AreaInfoBean bean : cityList) {
                    System.out.println(bean.toString());
                }

                System.out.println("---------");
            }


        } catch (Exception e) {
            isSuc = false;
            e.printStackTrace();
        }
        return isSuc;
    }
}
