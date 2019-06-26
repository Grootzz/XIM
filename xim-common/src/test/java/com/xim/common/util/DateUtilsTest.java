package com.xim.common.util;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class DateUtilsTest {

    @Test
    public void date() {
        System.out.println(DateUtils.date(new Date()));
    }
}