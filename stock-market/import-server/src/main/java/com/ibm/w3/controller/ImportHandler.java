package com.ibm.w3.controller;
import com.ibm.w3.entity.CompanyEntity;
import com.ibm.w3.entity.StockExchangeEntity;
import com.ibm.w3.entity.StockPriceEntity;
import com.ibm.w3.repository.CompanyRepository;
import com.ibm.w3.repository.StockExchangeRepository;
import com.ibm.w3.repository.StockPriceRepository;
import com.ibm.w3.views.ImportSummaryView;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ibm.w3.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
public class ImportHandler {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private StockExchangeRepository exchangeRepository;
    @Autowired
    private StockPriceRepository stockPriceRepository;
    @Autowired
    private ExcelUtil excelUtil;
    @GetMapping("/index")
    public String index() {
        return "index";
    }
    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadExcel(HttpServletRequest request)
            throws IOException {
        System.out.println("download");
        ResponseEntity<InputStreamResource> response = excelUtil.downloadExcel();
        return response;
    }

    @PostMapping("/upload")
    @ResponseBody
    public ImportSummaryView upload (@RequestParam("file") MultipartFile file) throws IOException, ParseException {
        List<StockPriceEntity> list = new ArrayList<>();
//
//        String filePath = "static/";
//        fileName = filePath + UUID.randomUUID() + fileName;
        InputStream inputStream = file.getInputStream();
        Workbook workbook = WorkbookFactory.create(inputStream);
        inputStream.close();
        //工作表对象
        Sheet sheet = workbook.getSheetAt(0);
        //总行数
        int rowLength = sheet.getLastRowNum();
        System.out.println("总行数有多少行"+rowLength);
        //工作表的列
        Row row = sheet.getRow(1);
        //总列数
        int colLength = row.getLastCellNum();
        System.out.println("总列数有多少列"+colLength);
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        //importSummary
        ImportSummaryView importSummary = new ImportSummaryView();
        importSummary.setNumofimport(rowLength);
        System.out.println("数字："+((long)row.getCell(0).getNumericCellValue()));
        //this.companyDao.findByCompanycode(row.getCell(0).getStringCellValue().trim());
        CompanyEntity company = companyRepository.getById((long) row.getCell(0).getNumericCellValue());
        System.out.println(company);
        importSummary.setCompanyname(company.getCompanyName());
        String exchangeName = row.getCell(1).getStringCellValue().trim();
        StockExchangeEntity exchangeEntity = exchangeRepository.getByName(exchangeName);
        System.out.println("exchangeEntity"+exchangeEntity);
        importSummary.setStockexchange(exchangeName);
        String fromDate = ""+new Date(row.getCell(3).getDateCellValue().getTime())+" "+new Time(timeFormat.parse(row.getCell(4).getStringCellValue().trim()).getTime());
        System.out.println("fromDate"+fromDate);
        importSummary.setFromdate(fromDate);
        String toDate = ""+new Date(sheet.getRow(rowLength).getCell(3).getDateCellValue().getTime())+" "+new Time(timeFormat.parse(sheet.getRow(rowLength).getCell(4).getStringCellValue().trim()).getTime());
        System.out.println("toDate"+toDate);
        importSummary.setTodate(toDate);
        System.out.println(importSummary);

        for (int i = 1; i < rowLength+1; i++) {
            row = sheet.getRow(i);
            StockPriceEntity stockPriceEntity = new StockPriceEntity();
            stockPriceEntity.setCompany(company);
            stockPriceEntity.setExchange(exchangeEntity);
            stockPriceEntity.setExchangeName(exchangeName);
            stockPriceEntity.setCompanyName(company.getCompanyName());
            stockPriceEntity.setPrice((float) row.getCell(2).getNumericCellValue());
            Date date = new Date(row.getCell(3).getDateCellValue().getTime());

            Time time = new Time(timeFormat.parse(row.getCell(4).getStringCellValue().trim()).getTime());
            date.setHours(time.getHours());
            date.setMinutes(time.getMinutes());
            date.setSeconds(time.getSeconds());
//            date.setTime(time.getTime());
            stockPriceEntity.setDate(date);

            System.out.println(stockPriceEntity);
            list.add(stockPriceEntity);
        }
        stockPriceRepository.saveAll(list);
        return importSummary;
    }
}
