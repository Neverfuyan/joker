package com.wj.springcloud.controller;

import com.wj.springcloud.service.FileService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ：actor
 * @date ：Created in 2022/2/23 14:00
 * @description：文件上传
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping("/file")
public class FileController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;


    @PostMapping("/upload")
    public String upload(MultipartFile file, @RequestParam(value = "bucketName") String bucketName) {
        if (StringUtils.isBlank(bucketName)) {
            logger.error("存储bucket名称为空，无法上传");
            return "存储bucket名称为空，无法上传";
        }
        if (!fileService.upload(file, bucketName)) {
            logger.error("文件上传异常");
            return "文件上传异常";
        }
        return "文件上传成功";
    }




}
