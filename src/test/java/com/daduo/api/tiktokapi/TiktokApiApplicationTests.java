package com.daduo.api.tiktokapi;

//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Test;

import java.io.IOException;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class TiktokApiApplicationTests {

    @Test
    public void contextLoads() throws IOException {
        Long id = Long.parseLong("1550732917442", 10);
        System.out.println(id);
    }

}

