package com.supcoder.weatherjson;

import com.supcoder.weatherjson.comparator.AreaComparator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.*;

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
                List<AreaInfoBean> areaList = workBookToAreaInfoList(workBook);
//                Collections.sort(areaList,new IdComparator());
                Collections.sort(areaList,new AreaComparator());
                for (AreaInfoBean bean:areaList){
                    System.out.println(bean.toString());
                }

            }
        });
    }


    private static final int COLUMN_ID = 0;
    private static final int COLUMN_PROVINCE = 1;
    private static final int COLUMN_CITY = 2;
    private static final int COLUMN_AREA = 3;


    private static List<AreaInfoBean> workBookToAreaInfoList(Workbook workBook) {
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
}
