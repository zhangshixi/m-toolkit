package com.mtoolkit.similar;

/**
 * 比较当前物件和参照物的相似度。
 * @version 1.00 2011-2-18, 10:36:46
 * @version 2.00 2012-04-23 13:34:00
 * @since 1.5
 * @author Michael
 */
public interface Similar<V> {

    /**
     * 和参数物相似度比较。
     * @param comparator 需要比较对的对象.
     * @return 当前对象和已经持有对象的相似度。
     *  该值的取值范围为[0.0,1.0]，值越大，表明两个对象的相似度越高。
     */
    public double contrast(V comparator);

    /**
     * 设置参照物
     * @param reference 参照物。
     */
    public void setReference(V reference);
    
}
