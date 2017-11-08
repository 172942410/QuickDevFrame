
package com.perry.utils;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class TimeUtils {

    private static final String TAG = "TimeUtils";

    public static void main(String[] args) {
        System.out.println(getFormatDate("HH:mm:ss"));
    }

    /**
     * @param format 指定的日期格式<br>
     *               eg:<br>
     *               "yyyy-MM-dd HH:mm:ss"<br>
     *               "yyyy-MM-dd"<br>
     *               "yyyyMMddHHmmss"<br>
     *               "HH:mm:ss"<br>
     * @return
     */
    public static String getFormatDate(String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

    /**
     * 获得当前日期时间
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getFormatDate1() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

    /**
     * 获得当前日期时间
     *
     * @return yyyyMMddHHmmss
     */
    public static String getFormatDate3() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

    /**
     * 获得当前日期时间
     *
     * @return yyyyMMddHHmmss
     */
    public static String getFormatDate2() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss_SSSS");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

    /**
     * 将日期格式 yyyy-M-d 转化为yyyyMMdd
     *
     * @param date yyyy-M-d
     * @return yyyyMMdd
     */
    public static String getFormatDate3(String date) {
        if(date == null || date.length() == 0 ||date.length()<3){
            return "";
        }

        String year = date.split("-")[0];
        String month = date.split("-")[1];
        String day = date.split("-")[2];
        if (month.length() == 1) {
            month = "0" + month;
        }
        if (day.length() == 1) {
            day = "0" + day;
        }
        return year + month + day;

    }

    /**
     * 将日期格式 yyyyMMdd 转化为 yyyy-MM-dd
     *
     * @param date yyyyMMdd
     * @return yyyy-MM-dd
     */
    public static String getFormatDate4(String date) {
        if(date == null||date.length()<8){
            return "8888-88-88";
        }
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6, 8);
        return year + "-" + month + "-" + day;
    }

    /**
     * 将日期格式 YYYYMMDDHHMMSS 转化为 YYYY-MM-DD HH:MM:SS
     *
     * @param date YYYYMMDDHHMMSS
     * @return YYYY-MM-DD HH:MM:SS
     */
    public static String getFormatDate5(String date) {
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6, 8);
        String hour = date.substring(8, 10);
        String min = date.substring(10, 12);
        String sec = date.substring(12, 14);
        return year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + sec;
    }

    /**
     * 将日期格式 yyyyMMdd 转化为 yyyy/MM/dd
     *
     * @param date yyyyMMdd
     * @return yyyy/MM/dd
     */
    public static String getFormatDate6(String date) {
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6, 8);
        return year + "/" + month + "/" + day;
    }
    /**
     * 将日期格式 yyyyMMdd 转化为 yyyy年MM月dd日
     *
     * @param date yyyyMMdd
     * @return yyyy年MM月dd日
     */
    public static String getFormatDate7(String date) {
        if(date == null||date.length()<8){
            return "8888-88-88";
        }
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6, 8);
        return year + "年" + month + "月" + day+"日";
    }
    /**
     * 获得当前时间
     *
     * @return HH:mm
     */
    public static String getFormatTime1() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

    /**
     * 获得当前时间
     *
     * @return HH:mm:ss
     */
    public static String getFormatTime2() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

    /**
     * 将时间格式 HH:mm 转化为 HHmm
     *
     * @param time HH:mm
     * @return HHmm
     */
    public static String getFormatTime3(String time) {
        return time.replaceAll(":", "");
    }

    /**
     * 将时间格式 HHmm 转化为 HH:mm
     *
     * @param time HHmm
     * @return HH:mm
     */
    public static String getFormatTime4(String time) {
        String hour = "00";
        String min = "00";
        if (time.length() == 4) {
            hour = time.substring(0, 2);
            min = time.substring(2, 4);
        }
        return hour + ":" + min;
    }

    /**
     * 根据指定的时间戳，返回指定格式的日期时间
     *
     * @param time    时间戳
     * @param format 指定的日期格式<br>
     *                   eg:<br>
     *                   "yyyy-MM-dd HH:mm:ss"<br>
     *                   "yyyy-MM-dd"<br>
     *                   "yyyyMMddHHmmss"<br>
     *                   "HH:mm:ss"<br>
     *               yyyy年MM月dd日 HH:mm<br>
     * @return
     */
    public static String getFormatTime(long time, String format) {
        Date date = new Date(time);
        String strs = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            strs = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strs;

    }

    /**
     * @param day 输入日期格式 yyyy-MM-dd
     * @return 获取指定日期往前或者往后几天的日期
     * @leaveDay 差距的天数 -1 or +1
     */
    public static String getFormatLeaveDay(String day, int leaveDay) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ;
        Date date = null;
        try {
            date = simpleDateFormat.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.DATE, leaveDay);
        date = calendar.getTime();
        return simpleDateFormat.format(date);
    }

    /**
     * @param day 输入日期格式 yyyy-MM-dd
     * @return 获取指定日期往前或者往后几天的日期 (输出格式为:xx月xx日)
     * @leaveDay 差距的天数 -1 or +1
     */
    public static String getFormatLastDay(String day, int leaveDay) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleFormat = new SimpleDateFormat("MM月dd日");
        ;
        Date date = null;
        try {
            date = simpleDateFormat.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.DATE, leaveDay);
        date = calendar.getTime();
        return simpleFormat.format(date);
    }

    /**
     * @param day 输入日期格式 yyyy-MM-dd
     * @return 获得前一天的日期
     */
    public static String getFormatBeforeDay(String day) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(day);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1); // 得到前一天
        date = calendar.getTime();
        return simpleDateFormat.format(date);
    }

    /**
     * @param day 输入日期格式 yyyy-MM-dd
     * @return 获得后一天的日期
     */
    public static String getFormatNextDay(String day) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(day);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, +1); // 得到后一天
        date = calendar.getTime();
        return simpleDateFormat.format(date);
    }

    /**
     * 获得输入日期的星期
     *
     * @param inputDate 需要转换的日期 yyyy-MM-dd
     * @return 星期×
     */
    public static String getWeekDay(String inputDate) {
        // String weekStrArr1[] = {"周日","周一","周二","周三","周四","周五","周六"};
        String weekStrArr1[] = {
                "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"
        };
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int outWeek = calendar.get(Calendar.DAY_OF_WEEK);// 返回的是1-7的整数，1为周日，2为周一，以此类推。
        return weekStrArr1[outWeek - 1];
    }

    /**
     * 检测时间是否在某个时间段内
     *
     * @param timeSlot 时间段 00：00--24：00
     * @param time     需要检测的时间 00：23
     * @return
     */
    public static boolean isInsideTime(String timeSlot, String time) {
        String startTime = timeSlot.split("--")[0];
        String endTime = timeSlot.split("--")[1];
        boolean isGreaterStart = compareTime(time, startTime);
        boolean isLessEnd = compareTime(endTime, time);
        if (isGreaterStart && isLessEnd) {
            return true;
        }
        return false;
    }

    /**
     * 比较两个时间的大小
     *
     * @param time1 00：23
     * @param time2 00：25
     * @return time1大于等于time2 为 true,time1小于time2 为 false
     */
    public static boolean compareTime(String time1, String time2) {
        if (time1.equals("24:00") || time2.equals("00:00")) {
            return true;
        }
        if (time2.equals("24:00") || time1.equals("00:00")) {
            return false;
        }
        // DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat df = new SimpleDateFormat("HH:mm");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(df.parse(time1));
            c2.setTime(df.parse(time2));
        } catch (ParseException e) {
            System.err.println("格式不正确");
        }
        int result = c1.compareTo(c2);

        if (result < 0) {
            return false;
        } else if (result >= 0) {
            return true;
        }
        return true;
    }

    /**
     * 比较两个日期的大小
     *
     * @param date1 2012-5-11
     * @param date2 2012-5-11
     * @return date1大于等于date2 为 true,date1小于date2 为 false
     */
    public static boolean compareDate(String date1, String date2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(df.parse(date1));
            c2.setTime(df.parse(date2));
        } catch (ParseException e) {
            System.err.println("格式不正确");
        }
        int result = c1.compareTo(c2);

        if (result < 0) {
            return false;
        } else if (result >= 0) {
            return true;
        }
        return true;
    }
    /**
     * 比较两个日期的大小
     *
     * @param date1 20120511
     * @param date2 20120511
     * @return date1大于等于date2 为 true,date1小于date2 为 false
     */
    public static int compareDateNum(String date1, String date2) {
        if(date1 == null || date2 == null){
            return -1;
        }
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(df.parse(date1));
            c2.setTime(df.parse(date2));
        } catch (ParseException e) {
            System.err.println("格式不正确");
        }
        int result = c1.compareTo(c2);
//        Log.e(TAG,"date1:"+date1+",date2:"+date2+"相差的结果是result:"+result);
        return result;
    }
    /**
     * 根据指定的时间戳，返回指定格式的日期时间
     *
     * @param time 时间戳
     *             eg:<br>
     *             "yyyy-MM-dd HH:mm:ss"<br>
     *             "yyyy-MM-dd"<br>
     *             "yyyyMMddHHmmss"<br>
     *             "HH:mm:ss"<br>
     * @return 01月02号 周五
     */
    public static String getFormatTimeWeek(long time) {
        String weekStrArr1[] = {
                "周日", "周一", "周二", "周三", "周四", "周五", "周六"
        };
        Date date = new Date(time);
        String strs = "";
        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
            strs = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int outWeek = calendar.get(Calendar.DAY_OF_WEEK);// 返回的是1-7的整数，1为周日，2为周一，以此类推。

        return strs + " " + weekStrArr1[outWeek - 1];

    }

    /**
     * @param dateStr 2016-01-01
     * @return 0101 周五
     */
    public static String getFormatTimeWeek(String dateStr) {
        Log.e(TAG,"dateStr:"+dateStr);
        if(dateStr == null ||dateStr.length()==0){
            return "";
        }
        Date date =  getFormatDay(dateStr);
        String weekStrArr1[] = {
                "周日", "周一", "周二", "周三", "周四", "周五", "周六"
        };
        String strs = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
            strs = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int outWeek = calendar.get(Calendar.DAY_OF_WEEK);// 返回的是1-7的整数，1为周日，2为周一，以此类推。

        return strs + " " + weekStrArr1[outWeek - 1];
    }

    /**
     * @param day 输入日期格式 yyyy-MM-dd
     * @return
     */
    public static Date getFormatDay(String day) {
        SimpleDateFormat simpleDateFormat;
        if(day.length() == 10) {
             simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        }else if(day.length() == 8){
            //
             simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        }else{
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        }
        Date date = null;
        try {
            date = simpleDateFormat.parse(day);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.add(Calendar.DATE, +1); // 得到后一天
//        date = calendar.getTime();
//        return simpleDateFormat.format(date);
    }

    /**
     * 根据指定的时间戳，返回指定格式的日期时间
     *
     * @param date       时间对象
     * @param formatType 指定的日期格式<br>
     *                   eg:<br>
     *                   "yyyy-MM-dd HH:mm:ss"<br>
     *                   "yyyy-MM-dd"<br>
     *                   "yyyyMMddHHmmss"<br>
     *                   "HH:mm:ss"<br>
     * @return
     */
    public static String getFormatDate(Date date, String formatType) {
        String strs = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(formatType);
            strs = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strs;

    }

    /**
     *
     * @param dateStr yyyy年MM月dd日
     * @return yyyyMMdd
     */
    public static String getFormatDateStr(String dateStr) {
        String strs = "";
        Date date;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
             date = sdf.parse(dateStr);
//            strs = sdf.format(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
            return strs;
        }
        strs = getFormatDate(date,"yyyyMMdd");
        return strs;

    }
    /**
     *
     * @param dateStr yyyy年MM月dd日
     * @param formatType   yyyy年MM月dd日  yyyy-MM-dd
     * @return yyyyMMdd
     */
    public static String getFormatDateStr(String dateStr,String formatType) {
        String strs = "";
        Date date;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(formatType);
            date = sdf.parse(dateStr);
//            strs = sdf.format(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
            return strs;
        }
        strs = getFormatDate(date,"yyyyMMdd");
        return strs;

    }
    /**
     * 根据指定的时间戳，返回指定格式的日期时间
     *
     * @param date 时间对象
     * @return yyyy-MM-dd
     */
    public static String getFormatDate(Date date) {
        String strs = "";
        strs = getFormatDate(date, "yyyy-MM-dd");
        return strs;

    }
}
