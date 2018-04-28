package cn.grass.gate.utils;

import android.text.TextUtils;

import com.weedys.weedlibrary.utils.LogUtil;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by weedys on 2016/1/20.
 */
public class DateUtil {
    private static String SIMPLE_FORMAT2 = "yyyy-MM-dd";
    private static String SIMPLE_MONTH_DAY_FORMAT = "MM月dd日";
    private static String ALL_DATE_FORMAT = "yyyy-MM-dd HH:mm";
    private static String SIMPLE_FORMAT = "yyyyMMddHHmmss";
    private static String SIMPLE_DAY_FORMAT = "yyyyMMdd";
    private static String SIMPLE_TIME_FORMAT = "HH:mm";
    private static String ALL_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";//返回24小时制

    /**
     * yyyyMMddHHmmss
     */
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

    public static String getMonthDayFormat(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat(SIMPLE_MONTH_DAY_FORMAT);
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


//    public static String getAllTimeFormat(String time) {
//        if (TextUtils.isEmpty(time)) {
//            return "";
//        }
//        SimpleDateFormat sdf1 = new SimpleDateFormat(SIMPLE_FORMAT);
//        try {
//            Date d = sdf1.parse(time);
//            SimpleDateFormat sdf = new SimpleDateFormat(ALL_DATE_FORMAT);
//            return sdf.format(d);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return time;
//    }

    public static String getAllTimeFormat(long time) {
        if (time <= 0) {
            return "0";
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat(ALL_DATE_FORMAT);
        return sdf1.format(new Date(time));
    }

    public static String getWholeTime(long time) {
        if (time <= 0) {
            return "0";
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat(ALL_TIME_FORMAT);
        return sdf1.format(new Date(time));
    }

    public static String getAllTimeFormat(String time) {
        if (TextUtils.isEmpty(time)) {
            return "0";
        }
        long timelong = 0;
        try {
            timelong = Long.valueOf(time);
        } catch (Exception e) {
            timelong = 0;
        }

        SimpleDateFormat sdf1 = new SimpleDateFormat(ALL_DATE_FORMAT);
        return sdf1.format(new Date(timelong));
    }

    /**
     * yyyy-MM-dd
     */
    public static String getAllSimpleTimeFormat(long time) {
        if (time <= 0) {
            return "0";
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat(SIMPLE_FORMAT2);
        return sdf1.format(new Date(time));
    }

    public static String getAllSimpleTimeFormat(String timeStr) {
        if (TextUtils.isEmpty(timeStr)) {
            return "";
        }
        long time = Long.valueOf(timeStr);
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
        if (hour <= 0) {
            return format.format(minute) + split + format.format(second);
        }
        return format.format(hour) + split + format.format(minute) + split + format.format(second);
    }

    public static String getToDayStr() {
        SimpleDateFormat sdf = new SimpleDateFormat(SIMPLE_FORMAT2);
        return sdf.format(new Date(System.currentTimeMillis()));
    }

    public static String getListDays(String longDate) {
        if (TextUtils.isEmpty(longDate)) {
            return "";
        }
        int day = Integer.valueOf(getSimpleDayFormat(Long.valueOf(longDate)));
        int now = Integer.valueOf(getSimpleDayFormat(System.currentTimeMillis()));
        LogUtil.show("day:" + day + "==now:" + now);
        int c = now - day;

        SimpleDateFormat sd = new SimpleDateFormat(SIMPLE_TIME_FORMAT);
        long ds = Long.valueOf(longDate);
        String date1 = sd.format(new Date(ds));
        if (c == 1) {
            return "昨天 " + date1;
        } else if (c == 0) {
            return "今天 " + date1;
        } else {
            SimpleDateFormat sd2 = new SimpleDateFormat(ALL_DATE_FORMAT);
            long ds2 = Long.valueOf(longDate);
            String date2 = sd2.format(new Date(ds2));
            return date2;
        }
    }

    public static String getDays(String longDate) {
        if (TextUtils.isEmpty(longDate)) {
            return "";
        }
        int day = Integer.valueOf(getSimpleDayFormat(Long.valueOf(longDate)));
        int now = Integer.valueOf(getSimpleDayFormat(System.currentTimeMillis()));
        LogUtil.show("day:" + day + "==now:" + now);
        int c = now - day;
        if (c == 1) {
            return "昨天";
        } else if (c == 0) {
            return "今天";
        } else {
            String patter = "MM-dd";
            SimpleDateFormat sd = new SimpleDateFormat(patter);
            long ds = Long.valueOf(longDate);
            String date1 = sd.format(new Date(ds)) + " " + getWeek(ds);
            return date1;
        }
    }

    public static String getListDays(long longDate) {
        int day = Integer.valueOf(getSimpleDayFormat(longDate));
        int now = Integer.valueOf(getSimpleDayFormat(System.currentTimeMillis()));
        LogUtil.show("day:" + day + "==now:" + now);
        int c = now - day;

        SimpleDateFormat sd = new SimpleDateFormat(SIMPLE_TIME_FORMAT);
        String date1 = sd.format(new Date(longDate));
        if (c == 1) {
            return "昨天 " + date1;
        } else if (c == 0) {
            return "今天 " + date1;
        } else {
            SimpleDateFormat sd2 = new SimpleDateFormat(ALL_DATE_FORMAT);
            String date2 = sd2.format(new Date(longDate));
            return date2;
        }
//
//        if (c == 1) {
//            return "昨天";
//        } else if (c == 0) {
//            return "今天";
//        } else {
//            String patter = "MM-dd";
//            SimpleDateFormat sd = new SimpleDateFormat(patter);
//            String date1 = sd.format(new Date(longDate)) + " " + getWeek(longDate);
//            return date1;
//        }
    }

    public static String getMultSimpleDays(String longDate) {
        if (TextUtils.isEmpty(longDate)) {
            return "";
        }
        int day = Integer.valueOf(getSimpleDayFormat(Long.valueOf(longDate)));
        int now = Integer.valueOf(getSimpleDayFormat(System.currentTimeMillis()));
//        LogUtil.show("day:"+day+"==now:"+now);
        int c = now - day;
        if (c == 1) {
            return "昨天";
        } else if (c == 2) {
            return "前天";
        } else if (c == 0) {
            SimpleDateFormat df = new SimpleDateFormat(SIMPLE_TIME_FORMAT);
            return df.format(longDate);
        } else {
            String patter = "MM-dd";
            SimpleDateFormat sd = new SimpleDateFormat(patter);
            long ds = Long.valueOf(longDate);
            String date1 = sd.format(new Date(ds));
            return date1;
        }

    }

    public static String getListDayOnly(String longDate) {
        if (TextUtils.isEmpty(longDate)) {
            return "";
        }
        int day = Integer.valueOf(getSimpleDayFormat(Long.valueOf(longDate)));
        int now = Integer.valueOf(getSimpleDayFormat(System.currentTimeMillis()));
//        LogUtil.show("day:"+day+"==now:"+now);
        int c = now - day;
        if (c == 1) {
            return "昨天";
        } else if (c == 0) {
            return "今天";
        } else {
            String patter = "MM-dd";
            SimpleDateFormat sd = new SimpleDateFormat(patter);
            long ds = Long.valueOf(longDate);
            String date1 = sd.format(new Date(ds));
            return date1;
        }

    }

    public static String getWeek(long longDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(longDate));
        int dayInDay = calendar.get(Calendar.DAY_OF_WEEK);
        switch (dayInDay) {
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

    /**
     * 返回星期几
     *
     * @param longDate
     * @return
     */
    public static String getWeekNum(long longDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(longDate));
        int dayInDay = calendar.get(Calendar.DAY_OF_WEEK);
        switch (dayInDay) {
            case Calendar.MONDAY:
                return "1";
            case Calendar.TUESDAY:
                return "2";
            case Calendar.WEDNESDAY:
                return "3";
            case Calendar.THURSDAY:
                return "4";
            case Calendar.FRIDAY:
                return "5";
            case Calendar.SATURDAY:
                return "6";
            case Calendar.SUNDAY:
                return "0";
            default:
                return "0";
        }
    }


    /**
     * 算往前几天
     *
     * @param days
     * @return
     */
    public static String getFrontDayStr(int days) {
        long front = 0;
        if (days >= 0) {
            front = System.currentTimeMillis() - (long) Arith.mul(Arith.mul(days, 36 * 24), 100000);
        } else {
            days = -days;
            front = System.currentTimeMillis() + (long) Arith.mul(Arith.mul(days, 36 * 24), 100000);
        }
        SimpleDateFormat sdf = new SimpleDateFormat(SIMPLE_DAY_FORMAT);
        LogUtil.show("front:" + front);
        return sdf.format(new Date(front));
    }

    public static String getAfterDayStr(int days) {
        long front = 0;
        if (days < 0) {
            days = -days;
            front = System.currentTimeMillis() - (long) Arith.mul(Arith.mul(days, 36 * 24), 100000);
        } else {
            front = System.currentTimeMillis() + (long) Arith.mul(Arith.mul(days, 36 * 24), 100000);
        }
        SimpleDateFormat sdf = new SimpleDateFormat(SIMPLE_DAY_FORMAT);
        LogUtil.show("after:" + front);
        return sdf.format(new Date(front));
    }

    public static String getBeforeDayStr(long days, int num) {
        SimpleDateFormat sdf = new SimpleDateFormat(ALL_DATE_FORMAT);
        Date dBefore = new Date(days);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dBefore);
        calendar.add(Calendar.DAY_OF_MONTH, num);//往上推一天  30推三十天  365推一年
        Date mBefore = calendar.getTime();
        String date = sdf.format(mBefore);
        return date;
    }


    /**
     * 根据日期获得所在周的日期
     *
     * @param mdate
     * @return
     */
    @SuppressWarnings("deprecation")
    public static List<Date> dateToWeek(Date mdate) {
        int b = mdate.getDay();
        Date fdate;
        List<Date> list = new ArrayList<Date>();
        Long fTime = mdate.getTime() - (b + 1) * 24 * 3600000;
        for (int a = 1; a <= 7; a++) {
            fdate = new Date();
            fdate.setTime(fTime + (a * 24 * 3600000));
            list.add(a - 1, fdate);
        }
        return list;
    }

    /**
     * 将date转成格式：2016年7月6日 星期三
     *
     * @param mdate
     * @return
     */
    public static String dateToFullStr(Date mdate) {
        //格式：2016年7月6日 星期三
        String formatDate = DateFormat.getDateInstance(DateFormat.FULL).format(mdate);
        return formatDate;
    }

    /**
     * 将date转成格式：一天前，一小时前，刚刚等等
     *
     * @param t
     * @return
     */
    public static String getStandardDate(long t) {
        StringBuffer sb = new StringBuffer();

        long time = System.currentTimeMillis() - t;
        long mill = (long) Math.ceil(time / 1000);//秒前

        long minute = (long) Math.ceil(time / 60 / 1000.0f);// 分钟前

        long hour = (long) Math.ceil(time / 60 / 60 / 1000.0f);// 小时

        long day = (long) Math.ceil(time / 24 / 60 / 60 / 1000.0f);// 天前

        if (day - 1 > 0 && day < 3) {
            sb.append(day + "天");
        } else if (day >= 3) {
            sb.append(getAllTimeFormat(t));
        } else if (day - 1 <= 0) {
            if (hour - 1 > 0) {
                if (hour >= 24) {
                    sb.append("1天");
                } else {
                    sb.append(hour + "小时");
                }
            } else if (minute - 1 > 0) {
                if (minute == 60) {
                    sb.append("1小时");
                } else {
                    sb.append(minute + "分钟");
                }
            } else if (mill - 1 > 0) {
                if (mill == 60) {
                    sb.append("1分钟");
                } else {
                    sb.append(mill + "秒");
                }
            } else {
                sb.append("刚刚");
            }
        }

        if (!sb.toString().equals("刚刚") && day < 3) {
            sb.append("前");
        }
        return sb.toString();
    }

}
