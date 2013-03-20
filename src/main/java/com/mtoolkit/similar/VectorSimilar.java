package com.mtoolkit.similar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
//import jeasy.analysis.MMAnalyzer;

/**
 * 使用空间向量相似度比较算法来比较文本的相似度。
 * 算法说明.A文章为当前文章,B文章为对照文章.
 * 1.分别计算A文章和B文章中有意义的词语的词频,比如A文章分词后出现的词语为{"人民","强大","优秀"},
 *    B文章的分词结果为{"强大","文明","理论","生存"}.
 * 2.将A文章的分词结果和B文章的分词结果进行合并取他们的并集AB={"人民","强大","优秀","文明","理论","生存}.
 * 3.根据AB分别在A文章和B文章中找出这个词的出现频率,分别生成A`和B`两个向量.这里假设A文章中"人民","强大",
 *    "优秀"分别出现了1,3,2次.B文章中"强大","文明","理论","生存"分别出现了1,4,3,2次.最终A`和B`分别等于
 *    {1,3,2,0,0,0},{0,1,0,4,3,2}.
 * 4.根据A`和B`计算文章相似度.计算方式如下.
 *    A` * B` = 1*0 + 3*1 + 2*0 + 0*4 + 0*3 + 0*2 = 3
 *    分别计算A`和B`的模|A`|,|B`|.sqrt表示开平方根.
 *    |A`| = sqrt(1^2 + 3^2 + 2^2 + 0^2 + 0^2 + 0^2)
 *    |B`| = sqrt(0^2 + 1^2 + 0^2 + 4^2 + 3^2 + 2^2)
 *
 * 最后相似度评分为=(A` * B`) / |A`| * |B`|.
 * 
 *
 * @version 1.00 2011-2-18, 10:44:02
 * @since 1.5
 * @author Michael
 */
public class VectorSimilar implements Similar<String> {

    //参照文本词组。
    private String[] collatorWords;
    //词过滤器列表。
    private final WordFilter[] wordFilters;
    //分词分隔符。
    private static final String SEPARATOR = " ";
    //分词器。
//    private static final MMAnalyzer ANALYZER = new MMAnalyzer();

    /**
     * 根据给定的参照文本构造一个相似度比较器。
     * @param reference 参照文本
     * @throws java.lang.IllegalArgumentException
     *      若给定的参照文本为null或者空字符串，将抛出此非法参数异常。
     */
    public VectorSimilar(String reference) {
        this(reference, new WordFilter[0]);
    }

    /**
     * 根据给定的参照文本和词过滤器构造一个相似度比较器。
     * @param reference 参照文本
     * @param wordFilters 词过滤器。
     * @throws java.lang.IllegalArgumentException
     *      若给定的参照文本为null或者空字符串，将抛出此非法参数异常。
     */
    public VectorSimilar(String reference, WordFilter... wordFilters) {
        checkTextParameter(reference);
        updateReference(reference);
        this.wordFilters = wordFilters;
    }

    /**
     * 文本的相似度比较。
     * @param comparator 比较文本
     * @return 比较文本与参照文本的相似度值。
     * @throws java.lang.IllegalArgumentException
     *      若给定的比较文本为null或者空字符串，将抛出此非法参数异常。
     */
    @Override
    public double contrast(String comparator) {
        checkTextParameter(comparator);

        String[] comparatorWords;
        try {
            comparatorWords = analyzeWords(comparator);
        } catch (IOException ex) {
            throw new RuntimeException(
                    "Collator or Comparator text can not be segmented by analyzer.");
        }
        String[] words = unionWords(collatorWords, comparatorWords);
        words = filterWords(words);

        Map<String, Integer> collatorWF = countWordFrequancy(collatorWords);
        Map<String, Integer> comparatorWF = countWordFrequancy(comparatorWords);

        int[] collatorVector = calculateVector(words, collatorWF);
        int[] comparatorVector = calculateVector(words, comparatorWF);

        double similar = contrastSimilar(collatorVector, comparatorVector);

        return trim(similar);
    }

    /**
     * 设置参照特，
     * @param reference 参照物。
     */
    public void setReference(String reference) {
        updateReference(reference);
    }

    /**
     * 更新当前的参数物。
     * @param reference 需要列新的参照特。
     */
    private void updateReference(String reference) {
        try {
            this.collatorWords = analyzeWords(reference);
        } catch (IOException ex) {
            throw new RuntimeException(
                    "Collator or Comparator text can not be segmented by analyzer.");
        }

    }

    /**
     * 参数的合法性检查。
     * @param text 参照文本。
     * @throws java.lang.IllegalArgumentException 非法参数异常。
     *      若给定的文本为null或者空字符串，将抛出此非法参数异常。
     */
    private void checkTextParameter(String text) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Contrast text must not be null or empty.");
        }
    }

    /**
     * 分词。
     * @param text 要分词的文本。
     * @return 文本中的词组列表。
     * @throws java.lang.IOException 若给定的文本不能进行分词，将抛出此异常。
     */
    private String[] analyzeWords(String text) throws IOException {
        String result = null;
//        result = ANALYZER.segment(text, SEPARATOR);

        return result.split(SEPARATOR);
    }

    /**
     * 过滤词汇。
     * @param words 要过率的词汇列表。
     * @return 过滤后的词汇列表。
     */
    private String[] filterWords(String[] words) {
        if (wordFilters == null || wordFilters.length == 0) {
            return words;
        }

        List<String> validWords = new ArrayList<String>(words.length);
        for (String word : words) {
            if (!isFilterWord(word)) {
                validWords.add(word);
            }
        }

        return validWords.toArray(new String[validWords.size()]);
    }

    /**
     * 判断给定的词汇是否需要被过滤掉。
     * @param word 给定的词汇。
     * @return 是否需要被过滤。
     *      <code>true</code>表示需要被过滤掉，<code>false</code>表示不需要被过滤掉。
     */
    private boolean isFilterWord(String word) {
        for (WordFilter filter : wordFilters) {
            if (!filter.accept(word)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 统计词频。
     * @param words 待统计的词组。
     * @return 词组出现的频次。其中key为词组，value为该词组出现的频次。
     */
    private Map<String, Integer> countWordFrequancy(String[] words) {
        Map<String, Integer> wordFrequancy = new HashMap<String, Integer>();

        Integer frequancy = null;
        for (String word : words) {
            frequancy = wordFrequancy.get(word);
            if (frequancy == null) {
                wordFrequancy.put(word, Integer.valueOf(1));
            } else {
                wordFrequancy.put(word, Integer.valueOf(frequancy.intValue() + 1));
            }
        }

        return wordFrequancy;
    }

    /**
     * 合并两个词组，即取两个词组的并集。
     * @param collatorWords 参照词组列表。
     * @param comparatorWords 比较词组列表。
     * @return 两个词组的并集。
     */
    private String[] unionWords(String[] collatorWords, String[] comparatorWords) {
        Set<String> unionWords = new HashSet<String>(Arrays.asList(collatorWords));
        unionWords.addAll(Arrays.asList(comparatorWords));

        return unionWords.toArray(new String[unionWords.size()]);
    }

    /**
     * 计算词频向量。
     * @param words 要计算的词并集列表。
     * @param wordFrequancy 给定文本的词频列表，其中KEY表示词，VALUE表示该词在文本中出现的次数。
     * @return 列表中的词在匹配文本中出现的对应词频列表。
     */
    private int[] calculateVector(
            String[] words, Map<String, Integer> wordFrequancy) {
        int[] vector = new int[words.length];

        for (int i = 0; i < words.length; i++) {
            if (wordFrequancy.get(words[i]) != null) {
                vector[i] = wordFrequancy.get(words[i]).intValue();
            } else {
                vector[i] = 0;
            }
        }

        return vector;
    }

    /**
     * 比较相似度。
     * 算法为：参照向量collatorVector和比较向量comparatorVector
     * 在多维空间中的向量积除以两个向量模的乘积。
     * @param collatorVector 参照向量。
     * @param comparatorVector 比较向量。
     * @return 参照向量和比较向量在空间夹角的余弦值。
     */
    private double contrastSimilar(int[] collatorVector, int[] comparatorVector) {
        //计算向量积。
        double vectorProduct = 0.0D;
        for (int i = 0; i < collatorVector.length; i++) {
            vectorProduct += collatorVector[i] * comparatorVector[i];
        }

        //分别计算向量的模。
        double collatorVectorMode = calculateVectorMode(collatorVector);
        double comparatorVectorMode = calculateVectorMode(comparatorVector);

        return vectorProduct / (collatorVectorMode * comparatorVectorMode);
    }

    /**
     * 计算向量模。
     * @param vector 要计算的向量。
     * @return 向量的模。
     */
    private double calculateVectorMode(int[] vector) {
        double mode = 0.0D;
        for (int value : vector) {
            mode += value * value;
        }

        return Math.sqrt(mode);
    }

    /**
     * 修剪相似度比较结果。
     * 此结果为两个空间向量夹角的余弦值，故范围必定在[0.0D, 1.0D]之间。
     * @param similar 相似度值。
     * @return 修正后的相似度的值，此值范围为：[0.0D, 1.0D]。
     */
    private double trim(double similar) {
        if (similar < 0.0D) {
            return 0.0D;
        } else if (similar > 1.0D) {
            return 1.0D;
        } else {
            return similar;
        }
    }
}
