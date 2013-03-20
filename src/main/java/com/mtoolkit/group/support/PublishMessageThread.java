package com.mtoolkit.group.support;

import java.io.IOException;

import com.mlogger.Loggers;
import com.mtoolkit.group.GroupExporter;
import com.mtoolkit.group.Message;
import com.mtoolkit.group.MessageReceiver;

/**
 * Publish message thread.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 12/10/2011
 * @since 	JDK1.5
 */
class PublishMessageThread implements Runnable {

	private Message _message;
	private MessageReceiver[] _receivers;
	private static final Loggers LOGGER = Loggers.getLoggers(GroupExporter.class);
	
	/**
	 * Creates a message publish thread with specified message and receivers.
	 * 
	 * @param message 
	 * @param receivers
	 */
	public PublishMessageThread(Message message, MessageReceiver[] receivers) {
		_message = message;
		_receivers = receivers;
	}
	
	/**
	 * Publish the message to each receiver.
	 */
	@Override
	public void run() {
		if (_message == null || _receivers == null || _receivers.length == 0) {
			return;
		}
		
		for (int i = 0; i < _receivers.length; i++) {
			try {
				_receivers[i].receive(_message);
			} catch (IOException e) {
				LOGGER.error(e.getMessage(), e);
				continue;
			}
		}
	}
	
}