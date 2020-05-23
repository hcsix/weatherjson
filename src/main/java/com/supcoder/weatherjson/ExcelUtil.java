package com.supcoder.weatherjson;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * ExcelUtil
 *
 * @author lee
 * @date 2020-05-23
 */
public class ExcelUtil {


    public static void parseExcel(String path, DealWorkBookExecutor dealWorkBookExecutor) {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("文件不存在");
            return;
        }
        FileInputStream fis = null;
        Workbook workBook = null;
        try {
            fis = new FileInputStream(file);
            // 使用XSSFWorkbook
            if (path.endsWith(".xlsx")) {
                workBook = new XSSFWorkbook(fis);
            } else if (path.endsWith(".xls")) {
                workBook = new HSSFWorkbook(fis, true);
            }
            if (workBook != null) {
                if (dealWorkBookExecutor != null) {
                    dealWorkBookExecutor.dealWorkBook(workBook);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally { //关流
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (workBook != null) {
                try {
                    workBook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static Object getCellVal(Cell cell){
        Object val = "";
        switch (cell.getCellType()) {
            case STRING:
                // 如果是字符串则保存
                val = cell.getRichStringCellValue().getString();
                break;
            case _NONE:
                break;
            case NUMERIC:
                //将数值转换为字符串
                val = (int)cell.getNumericCellValue();
                break;
            case BOOLEAN:
                val = cell.getBooleanCellValue();
                break;
            case FORMULA:
                val = cell.getCellFormula();
                break;
            case BLANK:
                break;
            case ERROR:
                val = cell.getErrorCellValue()+"";
                break;
            default:
                break;
        }
        return val;
    }


    public interface DealWorkBookExecutor {
        /**
         * 处理workBook
         * @param workBook
         */
        void dealWorkBook(Workbook workBook);
    }

}
