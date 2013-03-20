package com.mtoolkit.matcher;

import java.io.Serializable;

/**
 * @version 1.00 2010-01-14, 10:42:52
 * @since JDK 1.5
 * @author ZhangShixi
 */
public class Word implements Serializable {

    private static final long serialVersionUID = -4622143355145649281L;
    
    private String _word;
    private int[] _positions;
    
    public Word() {
    }
    
    public Word(String word, int[] positions) {
        _word = word;
        _positions = positions;
    }

    public String getWord() {
        return _word;
    }
    
    public void setWord(String word) {
        _word = word;
    }
    
    public int[] getPositions() {
        return _positions;
    }
    
    public void setPositions(int[] positions) {
        _positions = positions;
    }
    
}
