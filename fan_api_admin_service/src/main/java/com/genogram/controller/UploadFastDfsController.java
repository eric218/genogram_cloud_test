package com.genogram.controller;

import com.genogram.service.IUploadFastDfsService;
import com.genogram.unit.Response;
import com.genogram.unit.ResponseUtlis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 *
 * @Author: wang,wei
 * @Date: 2018-11-11
 * @Time: 12:37
 * @return:
 * @Description:
 *
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/fan")
public class UploadFastDfsController {
    /*@Autowired
    private IUploadFastDfsService uploadFastDfsService;
    *//**
     * 文件上传
     * @Author: wang,wei
     * @Date: 2018-11-11
     * @Time: 13:30
     *
     *
     * @param file
     * @return:
     * @Description:
     *
     *//*
    @RequestMapping(value ="/uploadFastdfs",method = RequestMethod.POST)
    public Response<Map> uploadFastdfs(MultipartFile file){
        return ResponseUtlis.success(uploadFastDfsService.uploadFastDfs(file));
    }*/
}
