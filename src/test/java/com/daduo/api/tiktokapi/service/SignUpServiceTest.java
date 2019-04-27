package com.daduo.api.tiktokapi.service;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class SignUpServiceTest {
    @Test
    public void test() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2019-12-01");

        Random random = new Random();
        int a = random.nextInt(10);


        System.out.println(a);
        System.out.println(a > 2);
    }
}