package com.base.common.utils.string;

import java.util.Arrays;
import java.util.function.Predicate;

/**
 * 字符串工具
 * @author hzh 2018/8/2 13:21
 * @since 1.8
 */
public class StringUtils {

    public enum Match {
        OR, AND;
    }

    /**
     * 字符串判空断言
     */
    private static Predicate<String> isEmpty = string -> null == string || "null".equals(string) || "".equals(string);

    /**
     * 判断string是否为空
     * @param string
     * @return
     */
    public static boolean isEmpty(String string) {
        return isEmpty.test(string);
    }

    /**
     * 判断String集合是否为空
     * @param match
     * @param strings
     * @return
     */
    private static boolean isEmpty(Match match, String... strings) {
        Predicate<String[]> allEmpty = stringArray -> Arrays.stream(stringArray).allMatch(isEmpty);
        Predicate<String[]> anyEmpty = stringArray -> Arrays.stream(stringArray).anyMatch(isEmpty);
        switch (match) {
            case AND:
                return allEmpty.test(strings);
            case OR:
                return anyEmpty.test(strings);
            default:
                return allEmpty.test(strings);
        }
    }

    /**
     * 判断String集合是否全为空
     * @param strings
     * @return
     */
    public static boolean isAllEmpty(String... strings) {
        return isEmpty(Match.AND, strings);
    }

    /**
     * 判断String集合是否有一个为空
     * @param strings
     * @return
     */
    public static boolean isAnyEmpty(String... strings) {
        return isEmpty(Match.OR, strings);
    }
}
