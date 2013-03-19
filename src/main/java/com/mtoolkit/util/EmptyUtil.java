package com.mtoolkit.util;

import java.util.Collection;
import java.util.Map;

/**
 * 判断给定的资源是否为空的帮助类。
 * 
 * @author michael
 * @version $Id: EmptyUtil.java, v 0.1 2012-9-26 下午1:58:21 michael Exp $
 */
public class EmptyUtil {

    /**
     * 私有构造器，不允许构造此工具类的实例。
     */
    private EmptyUtil() {
    }

    public static boolean isNullEmpty(String source) {
        return source == null || source.trim().isEmpty();
    }
    public static boolean isNotNullEmpty(String source) {
    	return source != null && !source.trim().isEmpty();
    }

    public static boolean isNullEmpty(Collection<?> source) {
        return source == null || source.isEmpty();
    }
    public static boolean isNotNullEmpty(Collection<?> source) {
        return source != null && !source.isEmpty();
    }
    
    public static boolean isNullEmpty(Map<?, ?> source) {
        return source == null || source.isEmpty();
    }
    public static boolean isNotNullEmpty(Map<?, ?> source) {
        return source != null && !source.isEmpty();
    }

    public static boolean isNullEmpty(Object[] source) {
        return source == null || source.length == 0;
    }
    public static boolean isNotNullEmpty(Object[] source) {
        return source != null && source.length > 0;
    }

    public static boolean isNullEmpty(Class<?>[] source) {
        return source == null || source.length == 0;
    }
    public static boolean isNotNullEmpty(Class<?>[] source) {
        return source != null && source.length > 0;
    }

    public static boolean isNullEmpty(boolean[] source) {
        return source == null || source.length == 0;
    }
    public static boolean isNotNullEmpty(boolean[] source) {
        return source != null && source.length > 0;
    }

    public static boolean isNullEmpty(byte[] source) {
        return source == null || source.length == 0;
    }
    public static boolean isNotNullEmpty(byte[] source) {
        return source != null && source.length > 0;
    }

    public static boolean isNullEmpty(char[] source) {
        return source == null || source.length == 0;
    }
    public static boolean isNotNullEmpty(char[] source) {
        return source != null && source.length > 0;
    }

    public static boolean isNullEmpty(double[] source) {
        return source == null || source.length == 0;
    }
    public static boolean isNotNullEmpty(double[] source) {
        return source != null && source.length > 0;
    }

    public static boolean isNullEmpty(float[] source) {
        return source == null || source.length == 0;
    }
    public static boolean isNotNullEmpty(float[] source) {
        return source != null && source.length > 0;
    }
    
    public static boolean isNullEmpty(long[] source) {
        return source == null || source.length == 0;
    }
    public static boolean isNotNullEmpty(long[] source) {
        return source != null && source.length > 0;
    }

    public static boolean isNullEmpty(int[] source) {
        return source == null || source.length == 0;
    }
    public static boolean isNotNullEmpty(int[] source) {
        return source != null && source.length > 0;
    }
    
    public static boolean isNullEmpty(short[] source) {
        return source == null || source.length == 0;
    }
    public static boolean isNotNullEmpty(short[] source) {
        return source != null && source.length > 0;
    }
    
}
