package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Lenovo on 2017/6/2.
 */
public class DateUtil {
    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
    private static Calendar cale = Calendar.getInstance();
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 日期格式yyyy-MM-dd HH:mm:ss字符串常量
     */
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String HOUR_FORMAT = "HH:mm:ss";

    public static Timestamp getTime(String str) {
        SimpleDateFormat sf = new SimpleDateFormat(DATETIME_FORMAT);
        Date date = formatDate(str);
        Timestamp t1 = new Timestamp(date.getTime());
        return t1;
    }

    public static Timestamp getTime(String str, String format) {
        try {
            SimpleDateFormat sf = new SimpleDateFormat(format);
            Date date = sf.parse(str.trim());
            Timestamp t1 = new Timestamp(date.getTime());
            return t1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前系统时间,以java.sql.Timestamp类型返回.
     *
     * @return 当前时间
     */
    public static Timestamp getTimestamp() {
        Timestamp d = new Timestamp(System.currentTimeMillis());
        return d;
    }

    /**
     * 获取当前日期时间 字符串类型
     *
     * @return
     */
    public static final String NowStr() {
        return DatetimetoStr(new Date());
    }

    public static final String DatetimetoStr(Date date) {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpledateformat.format(date);
    }

    /**
     * 获取日期与当前日期相差多少秒
     *
     * @param dateStr
     * @return
     * @author TTL
     */
    public static long differDate(String dateStr) throws Exception {
        if (dateStr == null || dateStr.equals("")) {
            return 0l;
        }
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = sdf.parse(dateStr);
        c.setTime(date1);
        long day1 = c.getTimeInMillis();
        Date currentDate = new Date();
        c.setTime(currentDate);
        long day2 = c.getTimeInMillis();
        Double differDate2 = (day1 - day2) * 1.0 / 1000 / 60 / 60 / 24;
        return (long) (differDate2 * 60 * 60 * 24);
    }

    /**
     * 获得服务器当前日期及时间，以格式为：yyyy-MM-dd HH:mm:ss的日期字符串形式返回
     *
     * @return
     * @author
     * @date Mar 11, 2012
     */
    public static String getDateTime(String format) {
        try {
            SimpleDateFormat datetime_format = new SimpleDateFormat(format);
            return datetime_format.format(cale.getTime());
        } catch (Exception e) {
            logger.debug("DateUtil.getDateTime():" + e.getMessage());

            return "";
        }
    }

    /**
     * 得到来源日期字符串
     *
     * @param date 日期类型
     * @param s    格式化类型(例如:yyyy-MM-dd)
     * @return
     */
    public static String getDateStr(Date date, String s) {
        SimpleDateFormat simpledateformat;
        return (simpledateformat = new SimpleDateFormat(s)).format(date);
    }

    /**
     * 获得服务器当前日期的年份
     *
     * @return
     * @author
     * @date Mar 11, 2012
     */
    public static String getYear() {
        try {
            return String.valueOf(cale.get(Calendar.YEAR));
        } catch (Exception e) {
            logger.debug("DateUtil.getYear():" + e.getMessage());
            return "";
        }
    }


    /**
     * 获得服务器当前日期的月份
     *
     * @return
     * @author
     * @date Mar 11, 2012
     */
    public static String getMonth() {
        try {
            java.text.DecimalFormat df = new java.text.DecimalFormat();
            df.applyPattern("00;00");
            return df.format((cale.get(Calendar.MONTH) + 1));
        } catch (Exception e) {
            logger.debug("DateUtil.getMonth():" + e.getMessage());
            return "";
        }
    }

    /**
     * 获得服务器在当前月中天数
     *
     * @return
     * @author
     * @date Mar 11, 2012
     */
    public static String getDay() {
        try {
            return String.valueOf(cale.get(Calendar.DAY_OF_MONTH));
        } catch (Exception e) {
            logger.debug("DateUtil.getDay():" + e.getMessage());
            return "";
        }
    }


    /**
     * 比较两个日期相差的天数
     *
     * @param date1
     * @param date2
     * @return
     * @author
     * @date Mar 11, 2012
     */
    public static int getMargin(String date1, String date2) {
        int margin;
        try {
            SimpleDateFormat date_format = new SimpleDateFormat(DATE_FORMAT);
            ParsePosition pos = new ParsePosition(0);
            ParsePosition pos1 = new ParsePosition(0);
            Date dt1 = date_format.parse(date1, pos);
            Date dt2 = date_format.parse(date2, pos1);
            long l = dt1.getTime() - dt2.getTime();
            margin = (int) (l / (24 * 60 * 60 * 1000));
            return margin;
        } catch (Exception e) {
            logger.debug("DateUtil.getMargin():" + e.toString());
            return 0;
        }
    }

    /**
     * 比较两个日期相差的天数
     *
     * @param date1
     * @param date2
     * @return
     * @author
     * @date Mar 11, 2012
     */
    public static double getDoubleMargin(String date1, String date2) {
        double margin;
        try {
            SimpleDateFormat date_format = new SimpleDateFormat(DATE_FORMAT);
            ParsePosition pos = new ParsePosition(0);
            ParsePosition pos1 = new ParsePosition(0);
            Date dt1 = date_format.parse(date1, pos);
            Date dt2 = date_format.parse(date2, pos1);
            long l = dt1.getTime() - dt2.getTime();
            margin = (l / (24 * 60 * 60 * 1000.00));
            return margin;
        } catch (Exception e) {
            logger.debug("DateUtil.getMargin():" + e.toString());
            return 0;
        }
    }

    /**
     * 比较两个日期相差的月数
     *
     * @param date1
     * @param date2
     * @return
     * @author
     * @date Mar 11, 2012
     */
    public static int getMonthMargin(String date1, String date2) {
        int margin;
        try {
            margin = (Integer.parseInt(date2.substring(0, 4)) - Integer.parseInt(date1.substring(0, 4))) * 12;
            margin += (Integer.parseInt(date2.substring(4, 7).replaceAll("-0", "-")) - Integer.parseInt(date1.substring(4, 7).replaceAll("-0", "-")));
            return margin;
        } catch (Exception e) {
            logger.debug("DateUtil.getMargin():" + e.toString());
            return 0;
        }
    }

    /**
     * 返回日期加X秒后的日期
     *
     * @param date
     * @param i
     * @return
     * @author
     * @date Mar 11, 2012
     */
    public static String addSeconds(String date, int i) {
        try {
            DateFormat df = new SimpleDateFormat(DATETIME_FORMAT);
            Date CurrnetDate = df.parse(date);// 解析成日期格式
            CurrnetDate.setTime(CurrnetDate.getTime() + i * 1000);
            return df.format(CurrnetDate);
        } catch (Exception e) {
            return getDateTime(DATETIME_FORMAT);
        }
    }

    /**
     * 返回日期加X天后的日期
     *
     * @param date
     * @param i
     * @return
     * @author
     * @date Mar 11, 2012
     */
    public static String addDay(String date, int i) {
        try {
            SimpleDateFormat date_format = new SimpleDateFormat(DATE_FORMAT);
            GregorianCalendar gCal = new GregorianCalendar(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(5, 7)) - 1, Integer.parseInt(date.substring(8, 10)));
            gCal.add(GregorianCalendar.DATE, i);
            return date_format.format(gCal.getTime());
        } catch (Exception e) {
            logger.debug("DateUtil.addDay():" + e.toString());
            return getDate();
        }
    }

    /**
     * 获得服务器当前日期，以格式为：yyyy-MM-dd的日期字符串形式返回
     *
     * @return
     * @author
     * @date Mar 11, 2012
     */
    public static String getDate() {
        try {
            SimpleDateFormat date_format = new SimpleDateFormat(DATE_FORMAT);
            return date_format.format(cale.getTime());
        } catch (Exception e) {
            logger.debug("DateUtil.getDate():" + e.getMessage());
            return "";
        }
    }

    /**
     * 返回日期加X月后的日期
     *
     * @param date
     * @param i
     * @return
     * @author
     * @date Mar 11, 2012
     */
    public static String addMonth(String date, int i) {
        try {
            SimpleDateFormat date_format = new SimpleDateFormat(DATE_FORMAT);
            GregorianCalendar gCal = new GregorianCalendar(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(5, 7)) - 1, Integer.parseInt(date.substring(8, 10)));
            gCal.add(GregorianCalendar.MONTH, i);
            return date_format.format(gCal.getTime());
        } catch (Exception e) {
            logger.debug("DateUtil.addMonth():" + e.toString());
            return getDate();
        }
    }

    /**
     * 返回日期加X年后的日期
     *
     * @param date
     * @param i
     * @return
     * @author
     * @date Mar 11, 2012
     */
    public static String addYear(String date, int i) {
        try {
            SimpleDateFormat date_format = new SimpleDateFormat(DATE_FORMAT);
            GregorianCalendar gCal = new GregorianCalendar(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(5, 7)) - 1, Integer.parseInt(date.substring(8, 10)));
            gCal.add(GregorianCalendar.YEAR, i);
            return date_format.format(gCal.getTime());
        } catch (Exception e) {
            logger.debug("DateUtil.addYear():" + e.toString());
            return "";
        }
    }

    /**
     * 返回某年某月中的最大天
     *
     * @param iyear
     * @param imonth
     * @return
     * @author
     * @date Mar 11, 2012
     */
    public static int getMaxDay(int iyear, int imonth) {
        int day = 0;
        try {
            if (imonth == 1 || imonth == 3 || imonth == 5 || imonth == 7 || imonth == 8 || imonth == 10 || imonth == 12) {
                day = 31;
            } else if (imonth == 4 || imonth == 6 || imonth == 9 || imonth == 11) {
                day = 30;
            } else if ((0 == (iyear % 4)) && (0 != (iyear % 100)) || (0 == (iyear % 400))) {
                day = 29;
            } else {
                day = 28;
            }
            return day;
        } catch (Exception e) {
            logger.debug("DateUtil.getMonthDay():" + e.toString());
            return 1;
        }
    }

    /**
     * 将指定字符串格式的日期与当前时间比较
     *
     * @param strDate 需要比较时间
     * @return <p>
     * int code
     * <ul>
     * <li>-1 当前时间 < 比较时间</li>
     * <li>0 当前时间 = 比较时间</li>
     * <li>>=1当前时间 > 比较时间</li>
     * </ul>
     * </p>
     * @author DYLAN
     * @date Feb 17, 2012
     */
    public static int compareToCurTime(String strDate) {
        if (StringUtils.isBlank(strDate)) {
            return -1;
        }
        Date curTime = cale.getTime();
        String strCurTime = null;
        try {
            SimpleDateFormat date_format = new SimpleDateFormat(DATE_FORMAT);
            strCurTime = date_format.format(curTime);
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("[Could not format '" + strDate + "' to a date, throwing exception:" + e.getLocalizedMessage() + "]");
            }
        }
        if (StringUtils.isNotBlank(strCurTime)) {
            return strCurTime.compareTo(strDate);
        }
        return -1;
    }


    /**
     * 取得当前时间的日戳
     *
     * @return
     * @author
     * @date Mar 11, 2012
     */
    @SuppressWarnings("deprecation")
    public static String getTimestampStr() {
        Date date = cale.getTime();
        String timestamp = "" + (date.getYear() + 1900) + date.getMonth() + date.getDate() + date.getMinutes() + date.getSeconds() + date.getTime();
        return timestamp;
    }

    /**
     * 取得指定时间的日戳
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String getTimestamp(Date date) {
        String timestamp = "" + (date.getYear() + 1900) + date.getMonth() + date.getDate() + date.getMinutes() + date.getSeconds() + date.getTime();
        return timestamp;
    }

    /**
     * 取得指定时间的日戳
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    public static long getTimestamp(String date_str) {
        try {
            SimpleDateFormat date_format = new SimpleDateFormat(DATETIME_FORMAT);
            Date date = date_format.parse(date_str);
            long timestamp = date.getTime();
            return timestamp;
        } catch (Exception e) {
            logger.debug("DateUtil.getTimestamp():" + e.toString());
            return 0l;
        }
    }

    /**
     * 根据字符串格式去转换相应格式的日期和时间
     *
     * @param date 必要参数
     * @return java.util.Date
     * @throws ParseException 如果参数格式不正确会抛出此异常
     */

    public static Date formatDate(String date) {
        SimpleDateFormat simple = null;
        switch (date.trim().length()) {
            case 19:// 日期+时间
                simple = new SimpleDateFormat(DATETIME_FORMAT);
                break;
            case 10:// 仅日期
                simple = new SimpleDateFormat(DATE_FORMAT);
                break;
            case 8:// 仅时间
                simple = new SimpleDateFormat(HOUR_FORMAT);
                break;
            default:
                break;
        }
        try {
            if (simple == null) {
                return null;
            } else {
                return simple.parse(date.trim());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 对Map中的日期类型进行格式化
     *
     * @param map
     * @param format
     * @return
     */
    public static Map mapDateToString(Map map, String format) {
        if (!StringUtils.hasText(format)) {
            format = DATETIME_FORMAT;
        }
        Set s = map.entrySet();
        Iterator i = s.iterator();
        while (i.hasNext()) {
            Map.Entry o = (Map.Entry) i.next();
            if (StringUtils.hasText(StringUtils.trim(o.getValue()))) {
                if (o.getValue().getClass().getSimpleName().equals("Date")) {
                    map.put(o.getKey(), DateUtil.getDateStr((Date) o.getValue(), format));
                }
            }
        }
        return map;
    }

    /**
     * 对比两个日期大小
     *
     * @param DATE1
     * @param DATE2
     * @return
     */
    public static int compareDate(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 根据一个日期，返回是星期几的字符串
     *
     * @param date
     * @return
     */
    public static String getWeek(Date date) {
        // 再转换为时间
        //Date date = strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // int hour=c.get(Calendar.DAY_OF_WEEK);
        // hour中存的就是星期几了，其范围 1~7
        // 1=星期日 7=星期六，其他类推
        return new SimpleDateFormat("EEEE").format(c.getTime());
    }


    /**
     * 获取本周的数组日期
     *
     * @return
     */
    public static Date[] getWeekDay() {
        Calendar calendar = Calendar.getInstance();
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DAY_OF_WEEK, -1);
        }
        Date[] dates = new Date[7];
        for (int i = 0; i < 7; i++) {
            dates[i] = calendar.getTime();
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }

    /**
     * 得到日期
     *
     * @param sdate
     * @return
     */
    public static String getWeekStr(String sdate) {
        Date date = strToDate(sdate);
        String str = getWeek(date);
        if ("1".equals(str)) {
            str = "星期日";
        } else if ("2".equals(str)) {
            str = "星期一";
        } else if ("3".equals(str)) {
            str = "星期二";
        } else if ("4".equals(str)) {
            str = "星期三";
        } else if ("5".equals(str)) {
            str = "星期四";
        } else if ("6".equals(str)) {
            str = "星期五";
        } else if ("7".equals(str)) {
            str = "星期六";
        }
        return str;
    }

    public static String getWeekStrNum(String sdate) {
        Date date = strToDate(sdate);
        String str = getWeek(date);
        if ("星期日".equals(str)) {
            str = "Sun";
        } else if ("星期一".equals(str)) {
            str = "Mon";
        } else if ("星期二".equals(str)) {
            str = "Tue";
        } else if ("星期三".equals(str)) {
            str = "Wed";
        } else if ("星期四".equals(str)) {
            str = "Thu";
        } else if ("星期五".equals(str)) {
            str = "Fri";
        } else if ("星期六".equals(str)) {
            str = "Sat";
        }
        return str;
    }

    public static String getWeektxt(String sdate) {
        Date date = strToDate(sdate);
        String str = getWeek(date);
        if ("星期日".equals(str)) {
            str = "Sunday";
        } else if ("星期一".equals(str)) {
            str = "Monday";
        } else if ("星期二".equals(str)) {
            str = "Tuesday";
        } else if ("星期三".equals(str)) {
            str = "Wednesday";
        } else if ("星期四".equals(str)) {
            str = "Thursday";
        } else if ("星期五".equals(str)) {
            str = "Friday";
        } else if ("星期六".equals(str)) {
            str = "Saturday";
        }
        return str;
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }
}
