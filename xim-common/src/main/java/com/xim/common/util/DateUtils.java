package com.xim.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * date 工具
 * @author noodle
 * @date 2019/6/26 20:47
 */
public class DateUtils {

    private static final SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );

    public static String date(Date date){
        return format.format(new Date());
    }
}
