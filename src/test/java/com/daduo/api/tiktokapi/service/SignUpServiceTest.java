package com.daduo.api.tiktokapi.service;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SignUpServiceTest {
    @Test
    public void test() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2019-12-01");

        BigDecimal a = new BigDecimal(10);
        BigDecimal b = new BigDecimal(10);

        System.out.println(b.compareTo(a));
    }
}