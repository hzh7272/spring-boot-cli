package com.base.common.utils.time;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

/**
 * 时间工具
 * @author hzh 2018/8/2 13:32
 * @since 1.8
 */
public class TimeUtils {

    private static final String UTC_ZONE_ID = "UTC";

    /**
     * 日期格式化枚举
     */
    public enum Format {
        YYYY_MM_DD, YYYY_MM, MM_DD, YY_MM_DD, YYYYMMDD, YYYY_MM_DD_HH_MM, YYYY_MM_DD_HH_MM_SS,
        YYYYMMDDHHMMSSSSS, YYYYMMDDHHMMSS, MMDDHHMM, HH_MM_SS
    }

    private static Function<Format, DateTimeFormatter> dateTimeFormatterFunction = format -> {
        String pattern = null;

        switch (format) {
            case YYYY_MM_DD:
                pattern = "yyyy-MM-dd";
                break;
            case YYYY_MM:
                pattern = "yyyy-MM";
                break;
            case MM_DD:
                pattern = "MM-dd";
                break;
            case YY_MM_DD:
                pattern = "yy-MM-dd";
                break;
            case YYYYMMDD:
                pattern = "yyyy/MM/dd";
                break;
            case YYYY_MM_DD_HH_MM:
                pattern = "yyyy-MM-dd HH:mm";
                break;
            case YYYY_MM_DD_HH_MM_SS:
                pattern = "yyyy-MM-dd HH:mm:ss";
                break;
            case YYYYMMDDHHMMSSSSS:
                pattern = "yyyy/MM/dd HH:mm:ss:SSS";
                break;
            case YYYYMMDDHHMMSS:
                pattern = "yyyy/MM/dd HH:mm:ss";
                break;
            case MMDDHHMM:
                pattern = "MM/dd HH:mm";
                break;
            case HH_MM_SS:
                pattern = "HH:mm:ss";
                break;
            default:
                pattern = "yyyy-MM-dd HH:mm:ss";
                break;
        }
        return DateTimeFormatter.ofPattern(pattern);
    };

    public static DateTimeFormatter dateTimeFormatter(Format format) {
        return dateTimeFormatterFunction.apply(format);
    }

    /**
     * 获取当前时间戳
     * @return
     */
    public static long timeMillis() {
        return System.currentTimeMillis();
    }

    public static String currentDate() {
        return getNowZonedDateTime(ZoneId.systemDefault(), Format.YYYY_MM_DD);
    }

    public static String currentDate(ZoneId zoneId) {
        return getNowZonedDateTime(zoneId, Format.YYYY_MM_DD);
    }

    public static String currentTime() {
        return getNowZonedDateTime(ZoneId.systemDefault(), Format.HH_MM_SS);
    }

    public static String currentTime(ZoneId zoneId) {
        return getNowZonedDateTime(zoneId, Format.HH_MM_SS);
    }

    public static String currentDateTime(ZoneId zoneId) {
        return getNowZonedDateTime(zoneId, Format.YYYY_MM_DD_HH_MM);
    }

    public static String getNowZonedDateTime(ZoneId zoneId, Format format) {
        return getNowZonedDateTime(zoneId).format(dateTimeFormatter(format));
    }

    public static ZonedDateTime getNowZonedDateTime(ZoneId zoneId) {
        return ZonedDateTime.now(zoneId);
    }

    public static LocalDateTime getNowLocalDateTime(ZoneId zoneId, Format format) {
        return LocalDateTime.now(zoneId);
    }

    public String format(ZonedDateTime zonedDateTime, Format format) {
        return zonedDateTime.format(dateTimeFormatter(format));
    }

    public String format(ZonedDateTime zonedDateTime, String pattern) {
        return zonedDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public String format(LocalDateTime localDateTime, Format format) {
        return localDateTime.format(dateTimeFormatter(format));
    }

    public String format(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }
}
