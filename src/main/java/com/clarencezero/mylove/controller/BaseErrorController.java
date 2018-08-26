package com.clarencezero.mylove.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "error")
public class BaseErrorController implements ErrorController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String getErrorPath() {
        logger.info("进入自定义错误控制器");
        return "redirect:/index.html";
    }
    @RequestMapping
    public String error() {
        return getErrorPath();
    }

}
