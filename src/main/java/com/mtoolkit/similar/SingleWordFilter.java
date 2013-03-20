package com.mtoolkit.similar;

/**
 * 单一词过滤器。
 * @version 1.00 2011-2-25, 10:20:45
 * @since 1.5
 * @author Michael
 */
public class SingleWordFilter implements WordFilter {

    /**
     * 若给定的词word为null或者其长度length的值小于2，将过滤掉此词。
     * @param word 给定的词。
     * @return 是否需要被过滤。
     *  <code>true</code>表示不需要被过滤掉，<code>false</code>表示需要被过滤掉。
     */
    public boolean accept(String word) {
        if (word == null || word.length() < 2) {
            return false;
        } else {
            return true;
        }
    }
    
}
