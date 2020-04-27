package com.oliwen.controller.system;

import com.oliwen.entity.ResultBody;
import com.oliwen.interceptor.SecurityAnnotation;
import com.oliwen.util.FileUtil;
import com.oliwen.util.StringUtil;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URL;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * @author: liht
 * @date: 2019/1/21 10:00 PM
 * @description:  文件上传
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @RequestMapping(value = "/upload")
    @SecurityAnnotation(auth = false)
    public ResultBody upload(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception{
        response.setContentType("text/html;charset=UTF-8");
        ResultBody body = new ResultBody();
        String uploadFileUrl = getPath("static/upload");
        StringBuffer fileNames = new StringBuffer();
        // 获取多个file
        for (Iterator it = multipartRequest.getFileNames(); it.hasNext();) {
            String key = (String) it.next();
            MultipartFile imgFile = multipartRequest.getFile(key);
            if (imgFile.getOriginalFilename().length() > 0) {
                String originalFilename = imgFile.getOriginalFilename();
                String fileName = StringUtil.getUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
                try {
                    boolean status = FileUtil.saveFileFromInputStream(imgFile.getInputStream(), uploadFileUrl, fileName);
                    if(!status){
                        return body.error("文件上传异常");
                    }
                    fileNames.append(fileName).append(",");
                } catch (Exception e) {
                }
            }
        }
        if(fileNames.length() > 1){
            body.setData(fileNames.deleteCharAt(fileNames.length() - 1));
        }
        return body;
    }

    /**
     * 获取静态资源的绝对路径
     * @param path  相对路径
     * @return
     */
    public String getPath(String path){
       try {
           //类路径下，即resource下
           URL url = ClassLoader.getSystemResource(path);
           if(url == null){
               //项目根目录下
               url = ResourceUtils.getURL(path);
           }
           return url.getPath();
       }catch (Exception e){
           return System.getProperty("user.dir") + File.separator + path;
       }
    }

}
