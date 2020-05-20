package com.kfit.demo.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kfit.demo.bean.Spmpn;
import com.kfit.demo.bean.User;
import com.kfit.demo.bean.yywddhz;
import com.kfit.demo.bean.yywddmx;
import com.kfit.demo.service.SpzlService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.BorderSide;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by zouLu on 2017-12-14.
 */
public class ExportExcelUtils {
    public static void exportExcel(HttpServletResponse response, String fileName, ExcelData data) throws Exception {
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
        exportExcel(data, response.getOutputStream());
    }

    public static void exportExcel(ExcelData data, OutputStream out) throws Exception {

        XSSFWorkbook wb = new XSSFWorkbook();
        try {
            String sheetName = data.getName();
            if (null == sheetName) {
                sheetName = "Sheet1";
            }
            XSSFSheet sheet = wb.createSheet(sheetName);
            writeExcel(wb, sheet, data);

            wb.write(out);
        } finally {
            wb.close();
        }
    }

    private static void writeExcel(XSSFWorkbook wb, Sheet sheet, ExcelData data) {

        int rowIndex = 0;

        rowIndex = writeTitlesToExcel(wb, sheet, data.getTitles());
        writeRowsToExcel(wb, sheet, data.getRows(), rowIndex);
        autoSizeColumns(sheet, data.getTitles().size() + 1);

    }

    private static int writeTitlesToExcel(XSSFWorkbook wb, Sheet sheet, List<String> titles) {
        int rowIndex = 0;
        int colIndex = 0;

        Font titleFont = wb.createFont();
        titleFont.setFontName("simsun");
        titleFont.setBold(true);
        // titleFont.setFontHeightInPoints((short) 14);
        titleFont.setColor(IndexedColors.BLACK.index);

        XSSFCellStyle titleStyle = wb.createCellStyle();
        titleStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        titleStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        titleStyle.setFillForegroundColor(new XSSFColor(new Color(182, 184, 192)));
        titleStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        titleStyle.setFont(titleFont);
        setBorder(titleStyle, BorderStyle.THIN, new XSSFColor(new Color(0, 0, 0)));

        Row titleRow = sheet.createRow(rowIndex);
        // titleRow.setHeightInPoints(25);
        colIndex = 0;

        for (String field : titles) {
            Cell cell = titleRow.createCell(colIndex);
            cell.setCellValue(field);
            cell.setCellStyle(titleStyle);
            colIndex++;
        }

        rowIndex++;
        return rowIndex;
    }

    private static int writeRowsToExcel(XSSFWorkbook wb, Sheet sheet, List<List<Object>> rows, int rowIndex) {
        int colIndex = 0;

        Font dataFont = wb.createFont();
        dataFont.setFontName("simsun");
        // dataFont.setFontHeightInPoints((short) 14);
        dataFont.setColor(IndexedColors.BLACK.index);

        XSSFCellStyle dataStyle = wb.createCellStyle();
        dataStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        dataStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        dataStyle.setFont(dataFont);
        setBorder(dataStyle, BorderStyle.THIN, new XSSFColor(new Color(0, 0, 0)));

        for (List<Object> rowData : rows) {
            Row dataRow = sheet.createRow(rowIndex);
            // dataRow.setHeightInPoints(25);
            colIndex = 0;

            for (Object cellData : rowData) {
                Cell cell = dataRow.createCell(colIndex);
                if (cellData != null) {
                    cell.setCellValue(cellData.toString());
                } else {
                    cell.setCellValue("");
                }

                cell.setCellStyle(dataStyle);
                colIndex++;
            }
            rowIndex++;
        }
        return rowIndex;
    }

    private static void autoSizeColumns(Sheet sheet, int columnNumber) {

        for (int i = 0; i < columnNumber; i++) {
            int orgWidth = sheet.getColumnWidth(i);
            sheet.autoSizeColumn(i, true);
            int newWidth = (int) (sheet.getColumnWidth(i) + 100);
            if (newWidth > orgWidth) {
                sheet.setColumnWidth(i, newWidth);
            } else {
                sheet.setColumnWidth(i, orgWidth);
            }
        }
    }

    private static void setBorder(XSSFCellStyle style, BorderStyle border, XSSFColor color) {
        style.setBorderTop(border);
        style.setBorderLeft(border);
        style.setBorderRight(border);
        style.setBorderBottom(border);
        style.setBorderColor(BorderSide.TOP, color);
        style.setBorderColor(BorderSide.LEFT, color);
        style.setBorderColor(BorderSide.RIGHT, color);
        style.setBorderColor(BorderSide.BOTTOM, color);
    }
    private static final String UPLOAD_DIRECTORY = "upload";

    //MultipartFile上传文件
    public static String getFileInfo(HttpServletRequest request, HttpServletResponse response, MultipartFile file) {
        String filePath = new String();
        String uploadPath = request.getSession().getServletContext().getRealPath("./") + UPLOAD_DIRECTORY;

        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        //判断文件是否为空
        if (!file.isEmpty()) {
            try {
                //文件的保存路径
                filePath = request.getSession().getServletContext().getRealPath("/") + UPLOAD_DIRECTORY + File.separator + file.getOriginalFilename();

                //转存文件
                file.transferTo(new File(filePath));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return filePath;
    }

    public static void DoFile(String fileName,MultipartFile file) throws Exception {
        boolean notNull = false;
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new Exception("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        if(sheet!=null) {
            notNull = true;
        }
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }

            Spmpn spmpn = new Spmpn();
            double name = row.getCell(0).getNumericCellValue();

            String phone = row.getCell(1).getStringCellValue();

            spmpn.setGoods_sn(phone);

        }


    }
    public static String getGisn(Map<String, String> map){
        String[] keys = (String[])map.keySet().toArray(new String[0]);
        Arrays.sort(keys);
        StringBuilder query = new StringBuilder();
        String[] bytes = keys;
        int len$ = keys.length;
        for(int i$ = 0; i$ < len$; ++i$) {
            String key = bytes[i$];
            String value = (String)map.get(key);
            if(new ValidateSignUtil().areNotEmpty(new String[]{key, value})) {
                query.append(key).append(value);
            }
        }
        return  query.toString();
    }

    public static String batchSnycTestceshi() {
        String requestUrl = "http://erpsrv.mypharma.com/order/getOrders";
 //       String requestUrl = "http://api.test.wdyy.com.cn/order/getOrders";
//    String requestUrl = "http://localhost:8086/order/getOrders";
        String json="[{'pageIndex':'0','pageSize':'100'}]";
        String appKey = "b7ae2c076b12bb6d2b31a7edd7b39527";
        String appSecret = "c99cb7ec9718a950aa649a83552c4a42";
        Map<String, String> appParamMap = new HashMap<String, String>();
        if(true){
            appParamMap.put("appKey",appKey);//3db2b76000a1a6b8dc20c3e13f871adf
            appParamMap.put("version","2.0");
            appParamMap.put("signSecret",appSecret);//0d4dd9c88c65201ca94325549c88e8a3
            appParamMap.put("currentDate","1528443518605");    //System.currentTimeMillis()+""
            appParamMap.put("bizParams",json);
            appParamMap.put("sign", DigestUtils.md5Hex(getGisn(appParamMap)));
        }
        appParamMap.put("signSecret",null);
        String result = PostClient.sendByPost(appParamMap, requestUrl);
     //   log.info(result);
     //   yywtoymd(result);
        return result;
    }

    public static void yywtoymd(String sGetyywjson)
    {
        SpzlService ss = new SpzlService();
        JSONObject obj= JSONObject.parseObject(sGetyywjson);
        obj = obj.getJSONObject("data");
        JSONArray orderList = obj.getJSONArray("orderList");
        for (int i = 0;i<orderList.size();i++)
        {
            JSONObject objorder = (JSONObject) orderList.get(i);
            yywddhz hz = JSONObject.toJavaObject(objorder,yywddhz.class);

            User user= new User();
            user.setName(hz.getMph_order_sn());
            ss.ItoUsers(user);
         //   ss.InToDDHZ(hz);
            JSONArray order_detailList = objorder.getJSONArray("order_detailList");
            for (int j= 0;j<order_detailList.size();j++)
            {
                JSONObject objorder_detail = (JSONObject) order_detailList.get(i);
                yywddmx mx = JSONObject.toJavaObject(objorder_detail,yywddmx.class);
                ss.InToDDMX(mx);
            }
        }
    }

}

