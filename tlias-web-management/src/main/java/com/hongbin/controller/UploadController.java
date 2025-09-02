package com.hongbin.controller;

import com.hongbin.pojo.Result;
import com.hongbin.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliOSSUtils aliOSSUtils;

    //本地存储文件的方式
//    @PostMapping("/upload")
//    public Result upload(String username, Integer age, @RequestParam(name = "image") MultipartFile file) throws Exception {
////        log.info("upload start: {},{},{}", username, age, file);
//
//        String originalFileName = file.getOriginalFilename();
////        int index = originalFileName.lastIndexOf(".");
////        String extName = originalFileName.substring(index);
////        String newFileName = UUID.randomUUID().toString() + extName;
//
//        String newFileName = UUID.randomUUID().toString() + originalFileName.substring(originalFileName.lastIndexOf("."));
//
//        log.info("新的文件名: {}", newFileName);
//        file.transferTo(new File("D:\\temp\\" + newFileName));
//
//        return Result.success();
//    }

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传，文件名：{}", image.getOriginalFilename());

        //调用ali cloud OSS 工具类，将文件上传
        String url = aliOSSUtils.upload(image);
        log.info("文件上传完成，文件访问的url: {}", url);


        return Result.success(url);
    }

}
