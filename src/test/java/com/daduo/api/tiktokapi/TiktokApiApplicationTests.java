package com.daduo.api.tiktokapi;

//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class TiktokApiApplicationTests {

    @Test
    public void contextLoads() throws IOException {
        File targetFile = new File("tmp/task/2.png");
        if (!targetFile.exists()) {
//                targetFile.mkdir();
            targetFile.getParentFile().mkdirs();
            targetFile.createNewFile();
        }
    }

}

