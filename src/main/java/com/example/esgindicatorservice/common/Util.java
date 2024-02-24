package com.example.esgindicatorservice.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static String nowTimeStampAsStr(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss.SSSSSS");
        return sdf.format(new Date()).toString();
    }

    public static String dateAsStr(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");
        return sdf.format(new Date()).toString();
    }

    public static Date nowAsDate(){
        return new Date();
    }

    public static String nowAsDateTimeStrBQ(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
        return sdf.format(new Date()).toString();
    }

    public static String dateAsNumeric(Date dt){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(dt).toString();
    }

    public static String nowAsNumeric(){
        String dtAsNum = Util.nowTimeStampAsStr();
        dtAsNum = dtAsNum.replace(":", "");
        dtAsNum = dtAsNum.replace("-", "");
        dtAsNum = dtAsNum.replace(".", "");
        dtAsNum = dtAsNum.replace(" ", "");
        return dtAsNum;
    }

    public static Logger Logger(Object obj){
        return LoggerFactory.getLogger(obj.getClass());
    }

}