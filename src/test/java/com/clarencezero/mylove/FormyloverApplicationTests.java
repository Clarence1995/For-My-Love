package com.clarencezero.mylove;

import com.clarencezero.mylove.service.QiNiuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FormyloverApplicationTests {
    @Autowired
    QiNiuService qiNiuService;

    @Test
    public void contextLoads() {
        throw new RuntimeException("hello");
    }

}
