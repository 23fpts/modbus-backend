package com.thc.adcpressureinapi.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author thc
 * @Title:
 * @Package com.thc.adcpressureinapi.utils
 * @Description:
 * @date 2021/7/7 7:16 下午
 */
public class CommonUtil {

    public static void main(String[] args) throws ParseException {
        Date date = param2Date(21, 7, 7, 19, 24, 21);
        System.out.println(date);
    }

    // 根据6个值生成Date类型的数据
    public static Date param2Date(Integer year, Integer mon, Integer day, Integer hour, Integer min, Integer sec) throws ParseException {
        String dateString = "20" + year + "-";
        if (mon<10) {
            dateString += "0" + mon;
        } else {
            dateString += "" + mon;
        }
        dateString +="-";
        if (day<10) {
            dateString += "0" + day;
        } else {
            dateString += "" +day;
        }
        dateString +=" ";
        if (hour<10) {
            dateString += "0" + hour;
        } else {
            dateString += "" + hour;
        }
        dateString +=":";

        if (min<10) {
            dateString += "0" + min;
        } else {
            dateString += "" + min;
        }

        dateString +=":";

        if (sec<10) {
            dateString += "0" + sec;
        } else {
            dateString += "" + sec;
        }

        System.out.println(dateString);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateTime = simpleDateFormat.parse(dateString);


        return dateTime;

    }
}
