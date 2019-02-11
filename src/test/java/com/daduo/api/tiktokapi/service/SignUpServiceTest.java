package com.daduo.api.tiktokapi.service;

import org.joda.time.LocalDateTime;
import org.junit.Test;

public class SignUpServiceTest {
    @Test
    public void test() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDateTime minus = now.minusMinutes(10);
        boolean id = minus.isBefore(dateTime);
        System.out.println(id);
    }
}