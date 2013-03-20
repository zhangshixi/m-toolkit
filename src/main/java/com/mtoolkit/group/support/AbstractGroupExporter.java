package com.mtoolkit.group.support;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.mlogger.Loggers;
import com.mtoolkit.group.GroupExporter;
import com.mtoolkit.group.MessageReceiver;

/**
 * A abstract group exporter implementation.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 12/10/2011
 * @since 	JDK1.5
 */
public abstract class AbstractGroupExporter implements GroupExporter {

	/** initialize flag */
	private volatile boolean _initialized = false;
	
	/** a thread-safe MessageReceiver holder */
	private List<MessageReceiver> _messageReceivers = new CopyOnWriteArrayList<MessageReceiver>();
	
	/** logger */
	protected static final Loggers LOGGER = Loggers.getLoggers(GroupExporter.class);
	
	/**
	 * Initializes group exporter.
	 * 
	 * @throws IOException if an I/O error occurs while initializing.
	 */
	protected abstract void doInit() throws IOException;
	
	/**
	 * Destroys group exporter.
	 * 
	 * @throws IOException if an I/O error occurs while destroying.
	 */
	protected abstract void doDestroy() throws IOException;
	
	/**
	 * {@inheritDoc}
	 * @throws IOException {@inheritDoc}
	 */
	@Override
	public void init() throws IOException {
		if (!_initialized) {
			doInit();
			_initialized = true;
		} else {
			LOGGER.error("GroupExporter has initialized!");
		}
	}

	/**
	 * {@inheritDoc}
	 * @throws IOException {@inheritDoc}
	 */
	@Override
	public void destroy() throws IOException {
		if (_initialized) {
			doDestroy();
			_initialized = false;
		} else {
			LOGGER.error("GroupExporter has destoryed!");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isReady() {
		return _initialized;
	}

	/**
	 * {@inheritDoc}
	 * @param receivers {@inheritDoc}
	 */
	@Override
	public void setMessageReceivers(MessageReceiver[] receivers) {
		if (receivers == null) {
			throw new NullPointerException("receivers");
		}
		
		for (int i = 0; i < receivers.length; i++) {
			if (receivers[i] != null) {
				_messageReceivers.add(receivers[i]);
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public MessageReceiver[] getMessageReceivers() {
		return _messageReceivers.toArray(new MessageReceiver[_messageReceivers.size()]);
	}

	/**
	 * {@inheritDoc}
	 * @param  receiver {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public boolean addMessageReceiver(MessageReceiver receiver) {
		if (receiver == null) {
			throw new NullPointerException("receiver");
		} else {
			return _messageReceivers.add(receiver);
		}
	}

	/**
	 * {@inheritDoc}
	 * @param  receiver {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public boolean removeMessageReceiver(MessageReceiver receiver) {
		if (receiver == null) {
			throw new NullPointerException("receiver");
		} else {
			return _messageReceivers.remove(receiver);
		}
	}

}
