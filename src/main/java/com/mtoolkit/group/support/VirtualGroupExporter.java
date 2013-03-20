package com.mtoolkit.group.support;

import java.io.IOException;

import com.mtoolkit.group.Message;
import com.mtoolkit.group.MessagePublisher;

/**
 * A virtual group exporter implementation.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 12/10/2011
 * @since 	JDK1.5
 */
public class VirtualGroupExporter extends AbstractGroupExporter {

	/** message publisher */
	private MessagePublisher _meMessagePublisher;
	
	/**
	 * Creates a virtual group exporter implementation instance.
	 */
	public VirtualGroupExporter() {
		_meMessagePublisher = new MessagePublisher() {
			
			@Override
			public void publish(Message message) throws IOException {
				// not publish any messages.
			}
		};
	}
	
	/**
	 * {@inheritDoc}
	 * @throws IOException {@inheritDoc}
	 */
	@Override
	protected void doInit() throws IOException {
	}
	
	/**
	 * {@inheritDoc}
	 * @throws IOException {@inheritDoc}
	 */
	@Override
	protected void doDestroy() throws IOException {
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isReady() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 * @return a virtual message publisher, not publish any message.
	 */
	@Override
	public MessagePublisher getMessagePublisher() {
		return _meMessagePublisher;
	}

}
