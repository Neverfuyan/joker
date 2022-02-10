package com.wj.springcloud.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 导入导出excel
 */
@Slf4j
public class EasyExcelUtil {

    /**
     * 根据自定义对象读取excel
     * @param <T>   实体类泛型
     * @param excel 文件流
     * @param clazz 实体类
     * @return 导入的数据集合
     */
    public static <T> ArrayList<T> readerExcel(MultipartFile excel, Class<T> clazz) throws Exception {
        if (excel.isEmpty()) {
            throw new Exception("请选择excel文件");
        }
        String fileName = excel.getOriginalFilename();
        log.info("Excel文件解析：文件名 = " + fileName);
        if (fileName == null || (!fileName.toLowerCase().endsWith(".xls") && !fileName.toLowerCase().endsWith(".xlsx"))) {
            throw new Exception("文件格式错误");
        }
        try(InputStream fileStream = new BufferedInputStream(excel.getInputStream())) {
            GeneralExcelListener<T> excelListener = new GeneralExcelListener<>();
            EasyExcel.read(fileStream, clazz, excelListener)
                    .autoTrim(true)
                    .sheet()
                    .doRead();
            return excelListener.getDatas();
        } catch (Exception e) {
            log.error("导入失败, 请检查导入数据的准确性", e);
            throw new Exception("导入失败, 请检查导入数据的准确性");
        }
    }

    /**
     * 根据自定义对象读取excel并指定读取起始行
     * @param <T>       实体类泛型
     * @param excel     文件流
     * @param rowNum    从第几行开始读
     * @param clazz     实体类
     * @return 导入的数据集合
     */
    public static <T> ArrayList<T> readExcelWithRowNum(MultipartFile excel, Integer rowNum, Class<T> clazz) throws Exception {
        if (excel.isEmpty()) {
            throw new Exception("请选择excel文件");
        }
        String fileName = excel.getOriginalFilename();
        log.info("Excel文件解析：文件名 = " + fileName);
        if (fileName == null || (!fileName.toLowerCase().endsWith(".xls") && !fileName.toLowerCase().endsWith(".xlsx"))) {
            throw new Exception("文件格式错误");
        }
        try(InputStream fileStream = new BufferedInputStream(excel.getInputStream())) {
            GeneralExcelListener<T> excelListener = new GeneralExcelListener<>();
            EasyExcel.read(fileStream, clazz, excelListener)
                    .autoTrim(true)
                    .sheet()
                    .headRowNumber(rowNum)
                    .doRead();
            return excelListener.getDatas();
        } catch (Exception e) {
            log.error("导入失败, 请检查导入数据的准确性", e);
            throw new Exception("导入失败, 请检查导入数据的准确性");
        }
    }

    /**
     * 根据自定义对象读取excel并指定sheet列表读取
     * sheet列表数据格式必须一样
     * @param excel     excel文件
     * @param sheetNums sheet集合
     * @param clazz     实体类
     * @param <T>       实体类泛型
     * @return 导入的数据集合
     */
    public static <T> ArrayList<T> readExcelWithSheets(MultipartFile excel, List<Integer> sheetNums, Class<T> clazz) throws Exception {
        if (excel.isEmpty()) {
            throw new Exception("请选择excel文件");
        }
        String fileName = excel.getOriginalFilename();
        log.info("Excel文件解析：文件名 = " + fileName);
        if (fileName == null || (!fileName.toLowerCase().endsWith(".xls") && !fileName.toLowerCase().endsWith(".xlsx"))) {
            throw new Exception("文件格式错误");
        }
        try(InputStream fileStream = new BufferedInputStream(excel.getInputStream())) {
            GeneralExcelListener<T> excelListener = new GeneralExcelListener<>();
            ExcelReader excelReader = EasyExcel.read(fileStream,clazz,excelListener).autoTrim(true).build();
            List<ReadSheet> readSheets = new ArrayList<>();
            for (Integer sheetNum : sheetNums) {
                ReadSheet readSheet = EasyExcel.readSheet(sheetNum).build();
                readSheets.add(readSheet);
            }
            excelReader.read(readSheets);
            excelReader.finish();
            return excelListener.getDatas();
        } catch (Exception e) {
            log.error("导入失败, 请检查导入数据的准确性", e);
            throw new Exception("导入失败, 请检查导入数据的准确性");
        }
    }

    /**
     * 根据自定义对象读取excel并自定义sheets读取
     * sheet列表数据格式必须一样
     * @param excel excel文件
     * @param sheets 自定义sheet
     * @param clazz 实体类
     * @param <T>   实体类泛型
     * @return 导入的数据集合
     */
    public static <T> ArrayList<T> readExcelWithCustomSheet(MultipartFile excel, List<ReadSheet> sheets, Class<T> clazz) throws Exception {
        if (excel.isEmpty()) {
            throw new Exception("请选择excel文件");
        }
        String fileName = excel.getOriginalFilename();
        log.info("Excel文件解析：文件名 = " + fileName);
        if (fileName == null || (!fileName.toLowerCase().endsWith(".xls") && !fileName.toLowerCase().endsWith(".xlsx"))) {
            throw new Exception("文件格式错误");
        }
        if (sheets == null){
            throw new Exception("请指定sheet");
        }
        try(InputStream fileStream = new BufferedInputStream(excel.getInputStream())) {
            GeneralExcelListener<T> excelListener = new GeneralExcelListener<>();
            ExcelReader excelReader = EasyExcel.read(fileStream,clazz,excelListener).autoTrim(true).build();
            excelReader.read(sheets);
            excelReader.finish();
            return excelListener.getDatas();
        } catch (Exception e) {
            log.error("导入失败, 请检查导入数据的准确性", e);
            throw new Exception("导入失败, 请检查导入数据的准确性");
        }
    }


    /**
     * 导出excel
     * @param fileName 导出的文件名
     * @param response 响应
     * @param request  请求
     * @param lists    导出的数据
     * @param <T>      导出的实体类泛型
     */
    public static <T> void writerExcel(String fileName, HttpServletResponse response, HttpServletRequest request, List<T> lists, Class<T> clazz,List<String> headList) {
        String sheetName = fileName;
        try {
            String userAgent = request.getHeader("User-Agent");

            if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
                // 针对IE或者以IE为内核的浏览器：
                fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            } else {
                // 非IE浏览器的处理:
                fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            }
            response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", fileName + ".xlsx"));
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", -1);
        } catch (UnsupportedEncodingException e1) {
            log.error("导出excel未知编码异常", e1);
        }
        try {
            EasyExcel.write(response.getOutputStream(), clazz)
                    .head(head(headList))
                    .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                    .sheet(sheetName)
                    .doWrite(lists);
        } catch (IOException e) {
            log.error("导出excel文件异常", e);
        }
    }

    /**
     * 导出带模板文件的excel
     * @param fileName     导出文件名
     * @param response     响应
     * @param request      请求
     * @param lists        导出的数据
     * @param <T>          导出的实体类泛型
     * @param clazz        导出的实体类
     * @param templatePath 模版文件路径
     */
    public static <T> void writerExcelByTemplate(String fileName, HttpServletResponse response, HttpServletRequest request, List<T> lists, Class<T> clazz, String templatePath) {

        String sheetName = fileName;
        try {
            String userAgent = request.getHeader("User-Agent");
            if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
                fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            } else {
                fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            }
            response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", fileName + ".xlsx"));
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", -1);
        } catch (UnsupportedEncodingException e1) {
            log.error("导出excel未知编码异常", e1);
        }
        try {
            EasyExcel.write(response.getOutputStream(), clazz)
                    .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                    .withTemplate(templatePath)
                    .sheet(0, sheetName)
                    .doWrite(lists);
        } catch (IOException e) {
            log.error("导出excel文件异常", e);
        }
    }

    /**
     * 导出模板信息（自定义表头）
     * @param fileName  导出文件名
     * @param response  响应
     * @param request   请求
     * @param heads     表头信息
     */
    public static void writerExcelWithHead(String fileName, HttpServletResponse response, HttpServletRequest request, List<String> heads) {
        String sheetName = fileName;
        try {
            String userAgent = request.getHeader("User-Agent");
            if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
                fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            } else {
                fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            }
            response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", fileName + ".xlsx"));
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", -1);
        } catch (UnsupportedEncodingException e1) {
            log.error("导出excel未知编码异常", e1);
        }
        try {
            EasyExcel.write(response.getOutputStream(), null)
                    .head(head(heads))
                    .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                    .sheet(sheetName)
                    .doWrite(new ArrayList());
        } catch (IOException e) {
            log.error("导出excel文件异常", e);
        }
    }

    /**
     * 获取表头
     * @param strings 表头信息
     * @return 表头
     */
    private static List<List<String>> head(List<String> strings) {
        List<List<String>> list = new ArrayList<>();
        for (String string : strings) {
            List<String> head = new ArrayList<>();
            head.add(string);
            list.add(head);
        }
        return list;
    }

}

