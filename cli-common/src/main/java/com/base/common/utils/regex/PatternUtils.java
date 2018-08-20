package com.base.common.utils.regex;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.regex.Pattern;

/**
 * 正则工具类
 * @version 1.0
 * @author hzh 2018/8/20 13:52
 */
public class PatternUtils {

    private PatternUtils() {}

    /**
     * 匹配结果
     * @version 1.0
     * @author hzh
     */
    class MatchResult {
        String content;
        boolean isMatch;

        private MatchResult(String content, boolean isMatch) {
            this.content = content;
            this.isMatch = isMatch;
        }

        /**
         * 不匹配事件
         * @param supplier 自定义不匹配事件提供者
         * @return 返回不匹配事件提供者的值
         * @author hzh
         */
        public String orElseGet(Supplier<String> supplier) {
            return this.isMatch ? this.content : supplier.get();
        }

        /**
         * 设置不匹配返回默认值
         * @param value 默认值
         * @return 返回设置的默认值
         * @author hzh
         */
        public String orElse(String value) {
            return this.isMatch? this.content : value;
        }

        /**
         * 获取匹配内容
         * @return 返回匹配内容
         * @author hzh
         */
        public String get() {
            return this.isMatch ? this.content : null;
        }

        /**
         * 如果匹配事件
         * @param consumer 返回事件消费者
         * @author hzh
         */
        public void ifMatch(Consumer<String> consumer) {
            if (null != this.content && this.isMatch) {
                consumer.accept(this.content);
            }
        }
    }

    /**
     * 正则匹配
     */
    private static BiFunction<String, String, Boolean> matchFunction = (content, regex) -> Pattern.matches(regex, content);

    /**
     * 匹配整数字符串
     * @param content 匹配内容
     * @return 匹配结果
     * @author hzh
     */
    public static boolean isIntegerNumber(String content) {
        return matchFunction.apply(content, "-\\d*|\\d*");
    }

    /**
     * 匹配小数字符串
     * @param content 匹配内容
     * @return 匹配结果
     * @author hzh
     */
    public static boolean isFloatNumber(String content) {
        return matchFunction.apply(content, "-\\d*\\.\\d{1,9}|\\d*\\.\\d{1,9}");
    }

    /**
     * 配置数字字符串
     * @param content 匹配内容
     * @return 匹配结果
     * @author hzh
     */
    public static boolean isNumber(String content) {
        return isFloatNumber(content) || isIntegerNumber(content);
    }

    /**
     * 配置数字字符串
     * @param content 匹配内容
     * @return 匹配结果对象
     * @author hzh
     */
    public static MatchResult matchNumber(String content) {
        return new PatternUtils().new MatchResult(content, isNumber(content));
    }

    /**
     * 匹配大陆手机号码
     * @param content 手机号码
     * @return 返回匹配结果
     * @author hzh
     */
    public static boolean isChinaMobileNumber(String content) {
        return matchFunction.apply(content, "[1][1-9]\\d{9}");
    }

    /**
     * 匹配大陆座机号码
     * @param content 座机号码
     * @return 返回匹配结果
     * @author hzh
     */
    public static boolean isChinaTelNumber(String content) {
        return matchFunction.apply(content, "[0][0-9]{2,3}[-][2-8]\\d{6,7}");
    }

    /**
     * 匹配香港电话号码
     * @param content 香港电话号码
     * @return 返回匹配结果
     * @author hzh
     */
    public static boolean isHongKongPhoneNumber(String content) {
        return matchFunction.apply(content, "[2,3,6,7,8,9]\\d{7}");
    }
}
