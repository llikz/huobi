package com.hyphenate.easeui.utils;

import android.text.TextUtils;
import android.util.Log;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by weedys on 2016/1/20.
 */
public class ChatDateUtil {

    private static String SIMPLE_FORMAT2 = "yyyy-MM-dd";
    private static String ALL_DATE_FORMAT = "yyyy-MM-dd HH:mm";
    private static String SIMPLE_FORMAT = "yyyyMMddHHmmss";
    private static String SIMPLE_DAY_FORMAT = "yyyyMMdd";
    private static String SIMPLE_TIME_FORMAT = "HH:mm";

    public static String getSimpleTimeFormat(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat(SIMPLE_FORMAT);
        Date d = new Date(time);
        return sdf.format(d);
    }

    public static String getSimpleDayFormat(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat(SIMPLE_DAY_FORMAT);
        Date d = new Date(time);
        return sdf.format(d);
    }

    public static String getSimpleTimeFormat(String time) {
        SimpleDateFormat sdf1 = new SimpleDateFormat(SIMPLE_FORMAT);
        try {
            Date d = sdf1.parse(time);
            SimpleDateFormat sdf = new SimpleDateFormat(SIMPLE_FORMAT2);
            return sdf.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    public static String getAllTimeFormat(String time) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat(SIMPLE_FORMAT);
        try {
            Date d = sdf1.parse(time);
            SimpleDateFormat sdf = new SimpleDateFormat(ALL_DATE_FORMAT);
            return sdf.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    public static String getAllTimeFormat(long time) {
        if (time <= 0) {
            return "0";
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat(ALL_DATE_FORMAT);
        return sdf1.format(new Date(time));
    }
    public static String getAllSimpleTimeFormat(long time) {
        if (time <= 0) {
            return "0";
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat(SIMPLE_FORMAT2);
        return sdf1.format(new Date(time));
    }

    public static long getTimeByMillis(String date) {
        long millis = 0;

        if (TextUtils.isEmpty(date))
            return -1;

        SimpleDateFormat dff;
        if (date.length() > 14) {
            dff = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.CHINA);
        } else {
            dff = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
        }
        dff.setTimeZone(TimeZone.getTimeZone("GMT+08"));

        try {
            Date d = dff.parse(date);
            millis = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return millis;
    }

    /**
     * days * 24 * 60 * 60 * 1000
     */
    public static long getMillisByDays(String days) {
        return (long) Double.parseDouble(days) * getMillisByHours(String.valueOf(24));
    }

    /**
     * hours * 60 * 60 * 1000
     */
    public static long getMillisByHours(String hours) {
        return (long) Double.parseDouble(hours) * getMillisByMinutes(String.valueOf(60));
    }

    /**
     * minutes * 60 * 1000
     */
    public static long getMillisByMinutes(String minutes) {
        return (long) Double.parseDouble(minutes) * getMillisBySeconds(String.valueOf(60));
    }

    /**
     * seconds * 1000;
     */
    public static long getMillisBySeconds(String seconds) {
        return (long) Double.parseDouble(seconds) * 1000;
    }

    public static String getTimeByMillis(long millis, String split) {
        long time = millis / 1000;

        long second = time % 60;
        long minute = time / 60 % 60;
        long hour = time / 60 / 60;

        DecimalFormat format = new DecimalFormat("00");
        return format.format(hour) + split + format.format(minute) + split + format.format(second);
    }

    public static String getTimeByMillis(long millis) {
        long time = millis / 1000;

        long second = time % 60;
        long minute = time / 60 % 60;
        long hour = time / 60 / 60;

        DecimalFormat format = new DecimalFormat("00");
        return format.format(hour) + "时" + format.format(minute) + "分" + format.format(second) + "秒";
    }

    public static String getTimeByMinute(long time, String split) {
        long second = time % 60;
        long minute = time / 60 % 60;
        long hour = time / 60 / 60;
        DecimalFormat format = new DecimalFormat("00");
        return format.format(hour) + split + format.format(minute) + split + format.format(second);
    }

    public static String getToDayStr() {
        SimpleDateFormat sdf = new SimpleDateFormat(SIMPLE_FORMAT2);
        return sdf.format(new Date(System.currentTimeMillis()));
    }
    public static String getListDays(String longDate) {
        if(TextUtils.isEmpty(longDate))
        {
             return "";
        }
       int day= Integer.valueOf(getSimpleDayFormat(Long.valueOf(longDate)));
       int now=Integer.valueOf(getSimpleDayFormat(System.currentTimeMillis()));
        Log.i("day","day:"+day+"==now:"+now);
        int c=now-day;
        if(c==1){
            return "昨天";
        }else if(c==0){
            return "今天";
        }else{
            String patter="MM-dd";
            SimpleDateFormat sd=new SimpleDateFormat(patter);
            long ds=Long.valueOf(longDate);
            String date1=sd.format(new Date(ds))+" "+getWeek(ds);
            return date1;
        }

    }
    public static String getMultSimpleDays(String longDate) {
        if(TextUtils.isEmpty(longDate))
        {
            return "";
        }
        int day= Integer.valueOf(getSimpleDayFormat(Long.valueOf(longDate)));
        int now=Integer.valueOf(getSimpleDayFormat(System.currentTimeMillis()));
        Log.i("day","day:"+day+"==now:"+now);
        int c=now-day;
        if(c==1){
            return "昨天";
        }else if(c==2){
            return "前天";
        }else if(c==0){
            SimpleDateFormat df=new SimpleDateFormat(SIMPLE_TIME_FORMAT);
            long dlong=Long.valueOf(longDate);
            return df.format(new Date(dlong));
        }else{
            String patter="MM-dd";
            SimpleDateFormat sd=new SimpleDateFormat(patter);
            long ds=Long.valueOf(longDate);
            String date1=sd.format(new Date(ds));
            return date1;
        }

    }
    public static String getListDayOnly(String longDate) {
        if(TextUtils.isEmpty(longDate))
        {
            return "";
        }
        int day= Integer.valueOf(getSimpleDayFormat(Long.valueOf(longDate)));
        int now=Integer.valueOf(getSimpleDayFormat(System.currentTimeMillis()));
//        LogUtil.show("day:"+day+"==now:"+now);
        int c=now-day;
        if(c==1){
            return "昨天";
        }else if(c==0){
            return "今天";
        }else{
            String patter="MM-dd";
            SimpleDateFormat sd=new SimpleDateFormat(patter);
            long ds=Long.valueOf(longDate);
            String date1=sd.format(new Date(ds));
            return date1;
        }

    }
    public static String getWeek(long longDate){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date(longDate));
        int dayInDay=calendar.get(Calendar.DAY_OF_WEEK);
        switch (dayInDay){
            case Calendar.MONDAY:
                return "星期一";
            case Calendar.TUESDAY:
                return "星期二";
            case Calendar.WEDNESDAY:
                return "星期三";
            case Calendar.THURSDAY:
                return "星期四";
            case Calendar.FRIDAY:
                return "星期五";
            case Calendar.SATURDAY:
                return "星期六";
            case Calendar.SUNDAY:
                return "星期日";
            default:
                return "星期一";
        }
    }
}
