package com.clarencezero.mylove.controller;

import com.clarencezero.mylove.entity.ImageBean;
import com.clarencezero.mylove.mapper.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/index")
public class IndexController {

    @Autowired
    private ImageMapper imageMapper;
    @RequestMapping(value = "/hello")
    @ResponseBody
    public String index() {
        List<ImageBean> resultList =  imageMapper.getAllImage();
        return "Hello My 0214";
    }

}
