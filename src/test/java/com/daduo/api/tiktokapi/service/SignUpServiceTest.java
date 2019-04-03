package com.daduo.api.tiktokapi.service;

import org.junit.Test;

import java.util.Calendar;

public class SignUpServiceTest {
    @Test
    public void test() {
        int id = 191234;

        int newid = id % 10000;
        System.out.println(newid);

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        id = (year - 2000) * 10000 + newid + 1;

        System.out.println(id);
    }
}