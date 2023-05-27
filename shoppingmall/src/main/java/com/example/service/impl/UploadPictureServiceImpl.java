package com.example.service.impl;

import com.example.service.UploadPictureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class UploadPictureServiceImpl implements UploadPictureService {

    @Override
    public ResponseEntity<Map<String, Object>> handleFileUpload(MultipartFile file) {
        Map<String, Object> result = new HashMap<>();
        if (!file.isEmpty()) {
            try {
                // 保存文件到服务器
                String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/images/product/";
                String substring = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
                Random random = new Random();
                int num = random.nextInt(9000) + 1000;
                String s = String.valueOf(num);
                random = new Random();
                char letter = (char) (random.nextInt(26) + 'a');
                String s1 = String.valueOf(letter);
                String filePath = uploadDir + s1 + s + "." + substring;
                File directory = new File(filePath).getParentFile();
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                System.out.println("filePath------------------"+filePath);
                file.transferTo(new File(filePath));

                //target目录下
                String path = System.getProperty("user.dir") + "/target/classes/static/images/product/";
                String filePath2 = path + s1 + s + "." + substring;
                File directory2 = new File(filePath2).getParentFile();
                if (!directory2.exists()) {
                    directory2.mkdirs();
                }
                File sourceFile = new File(filePath);
                File destFile = new File(filePath2);

                InputStream inputStream = new FileInputStream(sourceFile);
                OutputStream outputStream = new FileOutputStream(destFile);

                int bufferSize = 1024;
                byte[] buffer = new byte[bufferSize];

                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }

                inputStream.close();
                outputStream.close();


                // 返回成功信息
                result.put("code", 0);
                result.put("msg", "上传成功");
                Map<String, String> data = new HashMap<>();
                data.put("src", "/images/product/"+s1+s+"."+substring);
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
