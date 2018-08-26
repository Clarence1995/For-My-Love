package com.clarencezero.mylove.controller;

import com.clarencezero.mylove.entity.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("home")
public class ImageController {
    private final Logger logger =  LoggerFactory.getLogger((this.getClass()));

    private String loveName;

    // 20180717 首页获取照片
    @GetMapping("firstPage")
    public ResponseResult firstPage() {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(200);
        responseResult.setMsg("请求成功");
        return responseResult;
    }
}

















