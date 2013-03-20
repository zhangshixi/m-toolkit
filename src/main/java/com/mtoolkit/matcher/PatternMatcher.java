package com.mtoolkit.matcher;

/**
 * 模式匹配接口。
 * @version 1.00 2010-12-23, 22:53:01
 * @since JDK 1.5
 * @author ZhangShixi
 */
public interface PatternMatcher {

    /**
     * 模式匹配。
     * @param pattern 要匹配的模式。
     * @return 匹配结果。
     */
    public Word[] match(String pattern);
    
}
