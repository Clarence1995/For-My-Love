package com.clarencezero.mylove;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.clarencezero.mylove.mapper")
@ComponentScan
public class FormyloverApplication {

    public static void main(String[] args) {
        SpringApplication.run(FormyloverApplication.class, args);
    }
}
