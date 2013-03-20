package com.mtoolkit.group;

import java.io.IOException;

/**
 * Message receiver.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 12/10/2011
 * @since 	JDK1.5
 */
public interface MessageReceiver {

    /**
     * Receives message.
     * 
     * @param  message message.
     * 
     * @throws IOException if an I/O error occurs whiling receive message.
     */
    public void receive(Message message) throws IOException;
}
