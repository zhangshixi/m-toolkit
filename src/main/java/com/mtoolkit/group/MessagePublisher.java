package com.mtoolkit.group;

import java.io.IOException;

/**
 * Message publisher.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 12/10/2011
 * @since 	JDK1.5
 */
public interface MessagePublisher {

    /**
     * Publish message.
     * 
     * @param  message message.
     * 
     * @throws IOException if an I/O error occurs whiling publish message.
     */
    public void publish(Message message) throws IOException;
    
}
