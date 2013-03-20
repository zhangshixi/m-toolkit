package com.mtoolkit.similar;

/**
 * 词汇过滤器。
 * @version 1.00 2011-2-25, 10:20:45
 * @since 1.5
 * @author Michael
 */
public interface WordFilter {

    /**
     * 判断给定的词汇是否需要被过滤掉。
     * @param word 给定的词汇。
     * @return 是否需要被过滤。
     *      <code>true</code>表示接收这个词，<code>false</code>表示不接收。
     */
    public boolean accept(String word);
    
}
