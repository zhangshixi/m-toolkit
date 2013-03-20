package com.mtoolkit.group.support;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.mtoolkit.group.Message;
import com.mtoolkit.group.MessagePublisher;
import com.mtoolkit.util.ConversionUtil;

/**
 * A MulticastSocket group exporter implementation.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 12/10/2011
 * @since 	JDK1.5
 */
public class SocketGroupExporter extends AbstractGroupExporter {
	
	// ---- private parameters --------------------------------------------------
	private InetAddress 	_multicastGroup;
	private MulticastSocket _publishSocket;
	private MulticastSocket _receiveSocket;
	private ExecutorService _executeThreadPool;
	
	private int 			_receivePort 	   = DEFAULT_RECEIVE_PORT;
	private int				_timeToLive		   = DEFAULT_TIME_TO_LIVE;
	private boolean 		_loopbackMode 	   = DEFAULT_LOOPBACK_MODE;
	private int 			_receiveSoTimeout  = DEFAULT_RECEIVE_SOTIMEOUT;
	private int				_receiveBufferSize = DEFAULT_RECEIVE_BUFFER_SIZE;
	private String 			_multicastAddress  = DEFAULT_MULTICAST_ADDRESS;
	
	// ---- public constants ----------------------------------------------------
	/** default receive port number */
	public static final int 	DEFAULT_RECEIVE_PORT 	  	= 8611;
	/** default time to live value */
	public static final int		DEFAULT_TIME_TO_LIVE	  	= 16;
	/** default receive so timeout */
	public static final int 	DEFAULT_RECEIVE_SOTIMEOUT 	= 0;
	/** default receive buffer size */
	public static final int 	DEFAULT_RECEIVE_BUFFER_SIZE = 4 * 1024;
	/** default multicast socket address */
	public static final String  DEFAULT_MULTICAST_ADDRESS 	= "224.0.0.1";
	/** default loopback mode value */
	public static final boolean DEFAULT_LOOPBACK_MODE 	  	= true;
	
	/**
	 * Creates a MulticastSocket group exporter with {@link #DEFAULT_MULTICAST_ADDRESS}.
	 */
	public SocketGroupExporter() {
		try {
			_multicastGroup = InetAddress.getByName(_multicastAddress);
		} catch (UnknownHostException e) {
			// do not occurs if DEFAULT_MULTICAST_ADDRESS is correct.
		}
	}
	
	/**
	 * {@inheritDoc}
	 * @throws IOException {@inheritDoc}
	 */
	@Override
	protected void doInit() throws IOException {
		// in order!
		initReceiveMulticastSocket();
		initMessageReceiveListener();
		initPublishMulticastSocket();
		
		LOGGER.info("SocketGroupExporter has initialized!");
	}

	/**
	 * {@inheritDoc}
	 * @throws IOException {@inheritDoc}
	 */
	@Override
	protected void doDestroy() throws IOException {
		// in order!
		closePublishMulticastSocket();
		closeMessageReceiveListener();
		closeReceiveMulticastSocket();
		
		LOGGER.info("SocketGroupExporter has destroyed!");
	}

	/**
	 * {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public MessagePublisher getMessagePublisher() {
		return new SocketMessagePublisher();
	}
	
	// ---- public getter/setter methods ---------------------------------------
	/**
	 * Gets multicast socket receive port number.
	 * If not set this value, will return {@link #DEFAULT_RECEIVE_PORT}.
	 *  
	 * @return multicast socket receive port number.
	 * 
	 * @see	   #DEFAULT_RECEIVE_PORT
	 */
	public int getReceivePort() {
		return _receivePort;
	}
	
	/**
	 * Sets MulticastSocket receive port number.
	 * 
	 * @param receivePort MulticastSocket receive port number.
	 */
	public void setReceivePort(int receivePort) {
		if (!isReady()) {			
			_receivePort = receivePort;
		}
	}
	
	/**
	 * Gets time to live value of publish multicast socket.
	 * If not set this value, will return {@link #DEFAULT_TIME_TO_LIVE}.
	 *  
	 * @return time to live value of publish multicast socket.
	 * 
	 * @see	   #DEFAULT_TIME_TO_LIVE	 	
	 */
	public int getTimeToLive() {
		return _timeToLive;
	}
	
	/**
	 * Sets time to live value to publish multicast socket.
	 * 
	 * @param timeToLive new time to live value.
	 */
	public void setTimeToLive(int timeToLive) {
		if (!isReady()) {
			_timeToLive = timeToLive;
		}
	}
	
	/**
	 * Tests whether receive multicast socket is loopback mode.
	 * If not set this value, will return {@link #DEFAULT_LOOPBACK_MODE}.
	 * 
	 * @return {@code true} if and only if receive multicast socket is loopback mode;
	 * 		   {@code false} otherwise.
	 * 
	 * @see	   #DEFAULT_LOOPBACK_MODE
	 */
	public boolean isLoopbackMode() {
		return _loopbackMode;
	}
	
	/**
	 * Sets receive multicast socket loopback mode.
	 * 
	 * @param loopbackMode receive multicast socket loopback mode.
	 */
	public void setLoopbackMode(boolean loopbackMode) {
		if (!isReady()) {
			_loopbackMode = loopbackMode;
		}
	}
	
	/**
	 * Gets receive multicast socket so timeout value.
	 * If not set this value, will return {@link #DEFAULT_RECEIVE_SOTIMEOUT}.
	 * 
	 * @return receive multicast socket so timeout value.
	 * 
	 * @see	   #DEFAULT_RECEIVE_SOTIMEOUT
	 */
	public int getReceiveSoTimeout() {
		return _receiveSoTimeout;
	}
	
	/**
	 * Sets receive multicast socket so timeout value.
	 * 
	 * @param receiveSoTimeout receive multicast socket so timeout value.
	 */
	public void setReceiveSoTimeout(int receiveSoTimeout) {
		if (!isReady()) {
			_receiveSoTimeout = receiveSoTimeout;
		}
	}
	
	/**
	 * Gets receive multicast socket buffer size.
	 * If not set this value, will return {@link #DEFAULT_RECEIVE_BUFFER_SIZE}.
	 * 
	 * @return receive multicast socket buffer size.
	 * 
	 * @see	   #DEFAULT_RECEIVE_BUFFER_SIZE
	 */
	public int getReceiveBufferSize() {
		return _receiveBufferSize;
	}
	
	/**
	 * Sets receive multicast socket buffer size.
	 * 
	 * @param receiveBufferSize receive multicast socket buffer size.
	 */
	public void setReceiveBufferSize(int receiveBufferSize) {
		if (!isReady()) {
			_receiveBufferSize = receiveBufferSize;
		}
	}

	/**
	 * Gets multicast address.
	 * If not set this value, will return {@link #DEFAULT_MULTICAST_ADDRESS}.
	 * 
	 * @return multicast address.
	 * 
	 * @see	   #DEFAULT_MULTICAST_ADDRESS
	 */
	public String getMulticastAddress() {
		return _multicastAddress;
	}

	/**
	 * Sets multicast address.
	 * 
	 * @param  multicastAddress multicast address.
	 * 
	 * @throws NullPointerException if {@code multicastAddress} is null.
	 * @throws UnknownHostException if {@code multicastAddress} is not a valid multicast address.
	 */
	public void setMulticastAddress(String multicastAddress) 
		   throws UnknownHostException {
		if (!isReady()) {
			if (multicastAddress == null) {
				throw new NullPointerException("multicastAddress");
			}
			_multicastAddress = multicastAddress;
			_multicastGroup = InetAddress.getByName(multicastAddress);
		}
	}
	
	// ---- private methods -------------------------------------------------
	private void initPublishMulticastSocket() throws IOException {
		_publishSocket = new MulticastSocket();
		_publishSocket.setTimeToLive(_timeToLive);
		
		LOGGER.debug("Publish MulticastSocket initialized!");
	}
	
	private void initReceiveMulticastSocket() throws IOException {
		_receiveSocket = new MulticastSocket(_receivePort);
		_receiveSocket.setSoTimeout(_receiveSoTimeout);
		_receiveSocket.setLoopbackMode(_loopbackMode);
		_receiveSocket.setReceiveBufferSize(_receiveBufferSize);
		_receiveSocket.joinGroup(_multicastGroup);
		
		LOGGER.debug("Receive MulticastSocket initialized!");
	}

	private void initMessageReceiveListener() {
		_executeThreadPool = Executors.newCachedThreadPool();
		_executeThreadPool.submit(new SocketMessageReceiveListener());
		
		LOGGER.debug("Message reveice listener started!");
	}
	
	private void closePublishMulticastSocket() {
		_publishSocket.close();
		
		LOGGER.debug("Publish MulticastSocket closed!");
	}

	private void closeReceiveMulticastSocket() throws IOException {
		try {
			_receiveSocket.leaveGroup(_multicastGroup);
		} finally {
			_receiveSocket.close();
		}
		
		LOGGER.debug("Receive MulticastSocket closed!");
	}

	private void closeMessageReceiveListener() {
		_executeThreadPool.shutdown();
		
		LOGGER.debug("Message reveice listener stoped!");
	}
	
	// ---- private inner classes ------------------------------------------
	/**
	 * MulticastSocket message publisher.
	 */
	private class SocketMessagePublisher implements MessagePublisher {

		@Override
		public void publish(Message message) throws IOException {
			if (!isReady()) {
				throw new IllegalStateException("Not initialized!");
			}
			if (message == null) {
				throw new NullPointerException("message");
			}
			
			byte[] msg = ConversionUtil.object2Bytes(message);
			if (msg.length > _receiveBufferSize) {
				throw new IOException(
					  "receiveBufSize(" + _receiveBufferSize + ")" + 
					  " < messageLength(" + msg.length +")");
			}
			
			DatagramPacket publishPacket = new DatagramPacket(
					msg, msg.length, _multicastGroup, _receivePort);
			
			_publishSocket.send(publishPacket);
		}
	}
	
	/**
	 * MulticastSocket message receive listener.
	 */
	private class SocketMessageReceiveListener implements Callable<Object> {

		@Override
		public Object call() throws Exception {
			byte[] receiveBuf = null;
			DatagramPacket receivePacket = null;
			Message receivedMessage = null;
			
			while(true) {
				receiveBuf = new byte[_receiveBufferSize];
				receivePacket = new DatagramPacket(receiveBuf, receiveBuf.length);
				
				_receiveSocket.receive(receivePacket);
				
				receivedMessage = getReceivedMessage(receivePacket.getData());
				if (receivedMessage != null) {
					receiveBuf = null;
					_executeThreadPool.submit(new PublishMessageThread(
							receivedMessage, getMessageReceivers()));
				}
			}
		}
		
		/**
		 * Gets received message from received byte data array.
		 */
		private Message getReceivedMessage(byte[] receivedData) {
			Object message = null;
			try {
				message = ConversionUtil.bytes2Object(receivedData);
			} catch (IOException e) {
				LOGGER.error(e.getMessage(), e);
			} catch (ClassNotFoundException e) {
				LOGGER.error(e.getMessage(), e);
			}
			
			if (message instanceof Message) {
				return (Message) message;
			} else {
				return null;
			}
		}
	}
	
}
