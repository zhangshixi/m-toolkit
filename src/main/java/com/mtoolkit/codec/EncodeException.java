package com.mtoolkit.codec;

/**
 * Could not encode the data exception.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 21/10/2011
 * @since 	JDK1.5
 */
public class EncodeException extends Exception {

    private static final long serialVersionUID = 1759769435524661244L;

    public EncodeException() {
    }

    public EncodeException(String message) {
        super(message);
    }

    public EncodeException(Throwable cause) {
        super(cause);
    }

    public EncodeException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
