package com.wj.springcloud.service.impl;

import com.wj.springcloud.service.FileService;
import com.wj.springcloud.utils.MinIoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ：actor
 * @date ：Created in 2022/2/23 14:09
 * @description：文件上传实现类
 * @modified By：
 * @version: $
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private MinIoUtil minIoUtil;

    @Override
    public boolean upload(MultipartFile file, String bucketName) {
        return minIoUtil.upload(file,bucketName);
    }
}
