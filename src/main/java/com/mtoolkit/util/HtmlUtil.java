package com.mtoolkit.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * HTML utility.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.00, 07/18/2012
 * @since 	JDK1.5
 */
public class HtmlUtil {
	
	/**
     * Private constructor, not permit to construct the instance.
     */
    private HtmlUtil() {
    }
    
    private static final char BEGIN = '<';
    private static final char END = '>';
    private static final char SPACE = ' ';
    private static final char DIVIDE = '/';
    private static final Map<String, String> HTML_TAGS;

    //静态初始化Html标签元素。
    static {
        HTML_TAGS = new HashMap<String, String>();
        HTML_TAGS.put("a", "/a");
        HTML_TAGS.put("p", "/p");
        HTML_TAGS.put("ul", "/ul");
        HTML_TAGS.put("li", "/li");
        HTML_TAGS.put("br", "/br");
        HTML_TAGS.put("img", "/img");
        HTML_TAGS.put("div", "/div");
        HTML_TAGS.put("span", "/span");
        HTML_TAGS.put("label", "/label");
    }

    //---- public methods ------------------------------------------------------
    /**
     * 删除源字符串<code>source</code>中的指定的Html标签。
     *
     * 如果给定的源字符串<code>source</code>为null或者空字符，或者
     * 给定的要删除的Html标签列表<code>deleteTags</code>为null或空数组时，
     * 将不做任何处理，直接返回源字符串；否则删除掉源字符串中指定的Html标签元素。
     * 例如：
     * <p>String source = "<a href=\"url\">经济通</a><div style=\"s\">有限</div>公司";
     *    String[] deleteTags = new String[]{"div"};
     *    String result = deleteHtmlTags(source, deleteTags);
     *    那么result为：<a href=\"url\">经济通</a>有限公司
     *
     * @param source 待处理的源字符串。
     * @param deleteTags 要删除的Html标签列表。
     * @return 从源字符串中删除掉指定的Html标签后的字符串。
     */
    public static String deleteHtmlTags(String source, String[] deleteTags) {
        if (source == null || source.isEmpty()
                || deleteTags == null || deleteTags.length == 0) {
            return source;
        }

        String temp = null;
        StringBuilder buff = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            char point = source.charAt(i);
            if (point == BEGIN) { //可能的html标签开始符。
                int end = source.indexOf(END, i);
                if (end == -1) { //没有标签结束符。
                    buff.append(source.substring(i));
                } else {
                    temp = source.substring(i + 1, end);
                    if (startsWithDeleteHtmlTag(temp, deleteTags)) {
                        i = end; //以指定的html标签开始或结束的直接跳过。
                    } else {
                        buff.append(point); //虚假的"<>"包含体，追加。
                    }
                }
            } else {
                buff.append(point);
            }
        }

        return buff.toString();
    }

    /**
     * 截取给定的源字符串的子字符串。
     *
     * 截取的子字符串是从源字符串的开始位置0开始，长度为<code>length</code>的子字符串。
     * 长度的计算方式为：每个字符都代表一个长度，不管中英文或其他字符。
     * 如果给定的源字符串中包含Html标签，那么Html标签元素及相关属性将不作为长度计数，只计标签内容。
     * 如果给定的源字符串<code>source</code>为null或者空字符，或者
     * 给定的截取长度<code>length</code>小于等于0，或者大于等源字符串的长度时，
     * 将不做任何处理，直接返回源字符串；否则忽略Html元素标签并截取指定长度的子字符串。
     * 例如：
     * <p>String source = "<a href=\"url\">经济通</a><div style=\"s\">有限</div>公司";
     *    int length = 4;
     *    String result = subHtmlString(source, length);
     *    那么result为：<a href=\"url\">经济通</a><div style=\"s\">有</div>
     * 
     * 对此方法的调用等同与调用重载方法<code>subHtmlString(source, length, false)</code>。
     *
     * @param source 源字符串。
     * @param length 要截取的子字符串的长度。
     * @return 源字符串从0开始、长度为length的子字符串。
     * @see #subHtmlString(java.lang.String, int, boolean)
     */
    public static String subStringHtml(String source, int length) {
        return subStringHtml(source, length, false);
    }

    /**
     * 截取给定的源字符串的子字符串。
     *
     * 截取的子字符串是从源字符串的开始位置0开始，长度为<code>length</code>的子字符串。
     * 长度的计算方式为：中文、韩文、日文等字符计2个长度；英文、数字等字符及1个长度。
     * 如果给定的源字符串中包含Html标签，那么Html标签元素及相关属性将不作为长度计数，只计标签内容。
     * 如果给定的源字符串<code>source</code>为null或者空字符，或者
     * 给定的截取长度<code>length</code>小于等于0，或者大于等源字符串的长度时，
     * 将不做任何处理，直接返回源字符串；否则忽略Html元素标签并截取指定长度的子字符串。
     * 对应给定的长度<code>length</code>刚好处于一个中文字符的中间时，那么将返回这个中文字符。
     * 例如：
     * <p>String source = "<a href=\"\">经济通</a><div style=\"s\">有限</div>公司";
     *    int length = 7;
     *    boolean character = true;
     *    String result = subHtmlString(source, length, character);
     *    那么result为：<a href=\"\">经济通</a><div style=\"s\">有</div>
     *
     * @param source 源字符串。
     * @param length 要截取的子字符串的长度。
     * @param character 是否需要按英文字符为基础计数。
     *  <code>true</code>表示以英文为基础计数，英文数字等计1，中文等字符计2；
     *  <code>false</code>表示不以英文为基础计数，不管任何字符都将计数1。
     * @return 源字符串从0开始、长度为length的子字符串。
     */
    public static String subStringHtml(
            String source, int length, boolean character) {
        if (source == null || source.isEmpty() || length <= 0) {
            return source;
        }
        if (!character && length >= source.length()) {
            return source;
        }

        int count = 0; //统计计数器。
        String temp = null; //临时字符串。
        HtmlTag htmlTag = null; //记录标签名称和开始/结束标记的Html标签。
        StringBuilder buff = new StringBuilder(); //结果缓冲区。
        LinkedList<String> stack = new LinkedList<String>(); //Html标签栈。

        for (int i = 0; i < source.length(); i++) {
            if (count >= length) {
                while (!stack.isEmpty()) { //截取结束时，补全Html标签栈中记录的未闭合的元素。
                    buff.append(BEGIN);
                    buff.append(DIVIDE);
                    buff.append(stack.poll());
                    buff.append(END);
                }

                break;
            }

            char point = source.charAt(i);
            if (point == BEGIN) { //可能的Html标签元素开始符。
                int end = source.indexOf(END, i);

                if (end == -1) { //没有Html标签结束符，作为普通字符内容，逐个追加至指定长度。
                    temp = source.substring(i);

                    for (int j = 0; j < temp.length(); j++) {
                        if (count >= length) {
                            break;
                        }
                        point = temp.charAt(j);
                        buff.append(point);
                        count += countCharacter(point, character);
                    }

                } else { //具有Html标签结束符。
                    temp = source.substring(i + 1, end); //标签元素体。
                    htmlTag = startsWithHtmlTag(temp);

                    if (htmlTag == null) { //标签元素体并不是Html标签，追加当前字符并继续。
                        buff.append(point);
                        count += countCharacter(point, character);
                        continue;

                    } else { //是Html标签元素，根据是开始还是结束标签进行入栈或出栈，并跳过。
                        buff.append(source.substring(i, end + 1));
                        if (htmlTag.start) {
                            stack.push(htmlTag.name);
                        } else {
                            stack.poll();
                        }
                        i = end;
                        continue;
                    }
                }

            } else { //不是Html标签元素，追加单个字符。
                buff.append(point);
                count += countCharacter(point, character);
            }
        }

        return buff.toString();
    }

    //---- private methods -----------------------------------------------------
    /*
     * 判断给定的字符串的开头部分是否是指定的要删除的Html标签元素。
     * 如果是，将返回true；不是将返回false。
     */
    private static boolean startsWithDeleteHtmlTag(
            String temp, String[] deleteTags) {
        for (String tag : deleteTags) {
            if (temp.startsWith(tag) || temp.startsWith(DIVIDE + tag)) {
                return true;
            }
        }
        return false;
    }

    /*
     * 根据是否以英文字符为基础，来统计给定的字符长度。
     * 如果character为true，永远返回长度1；
     * 如果character为false，那么中文等字符返回长度2，英文等字符返回长度1。
     */
    private static int countCharacter(char ch, boolean character) {
        if (!character) {
            return 1;
        } else {
            return (ch >= 0 && ch <= 127) ? 1 : 2; //ASCII码所有字符表示在[0,127]之间。
        }
    }

    /*
     * 判断给定的字符串的开头部分是否是Html标签元素。
     * 如果不是将返回null；
     * 如果是，那么将返回一个HtmlTag对象，里面记录了标签名称和是否为开始标签。
     */
    private static HtmlTag startsWithHtmlTag(String temp) {
        int space = temp.indexOf(SPACE);

        String tag = null;
        if (space == -1) {
            tag = temp;
            if (HTML_TAGS.containsValue(tag)) {
                return new HtmlTag(temp.substring(1), false);
            }
        } else {
            tag = temp.substring(0, space);
            if (HTML_TAGS.containsKey(tag)) {
                return new HtmlTag(tag, true);
            }
        }

        return null;
    }

    /*
     * 表示一个Html标签元素。
     */
    private static class HtmlTag {

        private String name;    //标签名称。
        private boolean start;  //是否是开始标签。

        public HtmlTag(String tag, boolean start) {
            this.name = tag;
            this.start = start;
        }
    }
}
