package com.clarencezero.mylove.handleglobalexception;

import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class ClaExceptionController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Spring Security 登录异常处理
     * @param exce
     * @param resp
     * @throws IOException
     */
//    @ExceptionHandler({RuntimeException.class})
    @ExceptionHandler(value = BadCredentialsException.class)
    @ResponseStatus(HttpStatus.OK)
    public void processException(RuntimeException exce, HttpServletResponse resp) throws IOException {
        exce.printStackTrace();
        logger.error("[SPRING SECURITY] Runtime Exception");
        resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("status", 0);
        jsonObject.addProperty("msg", "登录异常 " + exce.getMessage());
        out.write(jsonObject.toString());
        out.flush();
        out.close();
    }
}
