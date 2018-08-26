package com.clarencezero.mylove.service;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

@Component
public class QiNiuService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value(value = "${clarencezero.accessKey}")
    private String accessKey;   // ak
    @Value(value = "${clarencezero.secretKey}")
    private String secretKey;   // sk
    @Value(value = "${clarencezero.bucket}")
    private String bucket;      // mylove
    @Value(value = "${clarencezero.origin}")
    private String origin;      // url



    /**
     * 七牛上传
     *
     * @param filePath
     * @return
     */
    public String qiniuUpload(String filePath) {
        //构造一个带指定Zone对象的配置类 zone2华南
        Configuration cfg = new Configuration(Zone.zone2());

        UploadManager uploadManager = new UploadManager(cfg);

        String localFilePath = filePath;
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key     = null;
        Auth   auth    = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return origin + putRet.key;
        } catch (QiniuException ex) {
            Response r = ex.response;
            logger.warn(r.toString());
            try {
                logger.warn(r.bodyString());
                return r.bodyString();
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return null;
    }

    public String getUpToken() {
        Auth   auth    = Auth.create(accessKey, secretKey);
        return auth.uploadToken(bucket, null, 3600, new StringMap().put("insertOnly", 1));
    }

    public String qiniuBase64Upload(String data64) {

        String key     = renamePic(".png");
        Auth   auth    = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        //服务端http://up-z2.qiniup.com
        String      url = "http://up-z2.qiniup.com/putb64/-1/key/" + UrlSafeBase64.encodeToString(key);
        RequestBody rb  = RequestBody.create(null, data64);
        Request request = new Request.Builder().
                url(url).
                addHeader("Content-Type", "application/octet-stream")
                .addHeader("Authorization", "UpToken " + getUpToken())
                .post(rb).build();
        System.out.println(request.headers());
        OkHttpClient     client   = new OkHttpClient();
        okhttp3.Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(response);
        return origin + key;
    }

    /**
     * 以时间戳重命名
     *
     * @param fileName
     * @return
     */
    public String renamePic(String fileName) {
        String extName = fileName.substring(fileName.lastIndexOf("."));
        return System.currentTimeMillis() + extName;
    }

    /**
     * 是否是图片
     * @param request
     * @param file
     * @return
     */
    public String isValidImage(HttpServletRequest request, MultipartFile file) {
        // 最大文件大小
        long maxSize = 5242880;
        // 定义允许上传的文件扩展名
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");

        if (!ServletFileUpload.isMultipartContent(request)) {
            return "请选择文件";
        }

        if (file.getSize() > maxSize) {
            return "上传文件大小超过5MB限制";
        }
        //检查扩展名
        String fileName = file.getOriginalFilename();
        String fileExt  = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if (!Arrays.<String>asList(extMap.get("image").split(",")).contains(fileExt)) {
            return "上传文件扩展名是不允许的扩展名\n只允许" + extMap.get("image") + "格式";
        }
        return "valid";
    }

    public String checkExt(String fileName, String dirName) {
        // 定义允许上传的文件扩展名
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");
        extMap.put("flash", "swf,flv");
        extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");

        // 检查扩展名
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if (!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)) {
            return "上传文件扩展名是不允许的扩展名\n只允许" + extMap.get(dirName) + "格式";
        }
        return "valid";
    }
}
