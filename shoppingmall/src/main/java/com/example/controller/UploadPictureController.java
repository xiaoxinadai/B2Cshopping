package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/sys")
public class UploadPictureController {

    @RequestMapping("/uploadPicture")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleFileUpload(@RequestParam("file") MultipartFile file) {
        Map<String, Object> result = new HashMap<>();
        if (!file.isEmpty()) {
            try {
                // 保存文件到服务器
                String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/images/product/";
                String filePath = uploadDir + file.getOriginalFilename();
                File directory = new File(filePath).getParentFile();
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                System.out.println("filePath------------------"+filePath);
                file.transferTo(new File(filePath));

                // 返回成功信息
                result.put("code", 0);
                result.put("msg", "上传成功");
                Map<String, String> data = new HashMap<>();
                data.put("src", "/images/product/" + file.getOriginalFilename());
                result.put("data", data);
                return new ResponseEntity<>(result, HttpStatus.OK);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 返回失败信息
        result.put("code", 1);
        result.put("msg", "上传失败");
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
}
