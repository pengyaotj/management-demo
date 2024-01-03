package com.example.management_demo.controller;

import com.example.management_demo.pojo.Result;
import com.example.management_demo.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private AliOSSUtils aliOSSUtils;

//    @PostMapping
//    public Result upload(String username, Integer age, MultipartFile image) throws Exception {
//        log.info("文件上传: {}{}{}",username,age,image);
//        // 获取原始文件名，划掉，获取拓展名
//        String originFilename = image.getOriginalFilename();
//        int index = originFilename.lastIndexOf(".");
//        String extendName = originFilename.substring(index);
//
//        // 构造唯一文件名 - uuid通用唯一识别码
//        String newFilename = UUID.randomUUID().toString() + extendName;
//
//        // 将文件存储在服务器的磁盘目录中
//        image.transferTo(new File("D:\\Images\\"+newFilename));
//
//        return Result.success();
//    }

    @PostMapping
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传，文件名: {}",image.getOriginalFilename());

        // 调用阿里云OSS工具类
        String url = aliOSSUtils.upload(image);
        log.info("文件上传完成，文件url: {}",url);

        return Result.success(url);
    }
}
