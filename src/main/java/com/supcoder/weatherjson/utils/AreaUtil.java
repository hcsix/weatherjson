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


            List<List<List<AreaInfoBean>>> areaList = new ArrayList<List<List<AreaInfoBean>>>();
            for (List<AreaInfoBean> cityList : provinceList) {
                List<List<AreaInfoBean>> cList = new ArrayList<List<AreaInfoBean>>();
                for (int i = 0; i < cityList.size(); i++) {
                    AreaInfoBean curArea = cityList.get(i);
                    if (i == 0) {
                        List<AreaInfoBean> aList = new ArrayList<AreaInfoBean>();
                        aList.add(curArea);
                        cList.add(aList);
                    } else {
                        AreaInfoBean preArea = cityList.get(i - 1);
                        if (preArea.getCity().equals(curArea.getCity())) {
                            cList.get(cList.size() - 1).add(curArea);
                        } else {
                            List<AreaInfoBean> aList = new ArrayList<AreaInfoBean>();
                            aList.add(curArea);
                            cList.add(aList);
                        }
                    }
                }
                areaList.add(cList);
            }


            for (int k = 0; k < areaList.size(); k++) {
                if (k==0){
                    System.out.println("[");
                }
                List<List<AreaInfoBean>> p = areaList.get(k);
                System.out.println("\t{");
                for (int j = 0; j < p.size(); j++) {
                    List<AreaInfoBean> c = p.get(j);
                    for (int i = 0; i < c.size(); i++) {
                        AreaInfoBean a = c.get(i);
                        if (j==0 && i == 0){
                            System.out.println("\t\t\"province\":" + a.getProvince() + ",");
                            System.out.println("\t\t\"city\":[");
                        }

                        if (i == 0) {
                            System.out.println("\t\t\t{");
                            System.out.println("\t\t\t\t\"name\":" + a.getCity() + ",");
                            System.out.println("\t\t\t\t\"area\":[");
                        }
                        System.out.println("\t\t\t\t\t"+a.jsonData()+",");
                        if (i == c.size() - 1) {
                            System.out.println("\t\t\t\t]");
                            System.out.println("\t\t\t}");
                        }
                        if (i == c.size() - 1 && j == p.size()-1){
                            System.out.println("\t\t]");
                        }
                    }
                }


                if (k == areaList.size()-1){
                    System.out.println("\t}\n]");
                }else {
                    System.out.println("\t},");
                }
            }


        } catch (Exception e) {
            isSuc = false;
            e.printStackTrace();
        }
        return isSuc;
    }
}
