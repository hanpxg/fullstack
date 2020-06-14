package com.ibm.w3.controller;

import com.ibm.w3.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
@RestController
public class ImportDataHandler {
    @Autowired
    private ExcelUtil excelUtil;
    @GetMapping("/index")
    public void index() {
        System.out.println("index");
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
    public String upload (@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();

        String filePath = "classpath:/static/";
        fileName = filePath + UUID.randomUUID() + fileName;
        System.out.println(fileName);
        File dest = new File(fileName);
        if(!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            return "Upload Successful";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Upload Failed";
    }
}
