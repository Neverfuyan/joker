package com.wj.springcloud.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author ：actor
 * @date ：Created in 2022/2/23 14:08
 * @description：
 * @modified By：
 * @version: $
 */
public interface FileService {


    boolean upload(MultipartFile file, String bucketName);
}
