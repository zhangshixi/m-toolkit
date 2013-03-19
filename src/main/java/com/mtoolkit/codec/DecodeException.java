package com.mtoolkit.codec;

/**
 * Could not decode the encoded data exception.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 21/10/2011
 * @since 	JDK1.5
 */
public class DecodeException extends Exception {

    private static final long serialVersionUID = -4841482769352578320L;

    public DecodeException() {
    }

    public DecodeException(String message) {
        super(message);
    }

    public DecodeException(Throwable cause) {
        super(cause);
    }

    public DecodeException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
