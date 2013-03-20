package com.mtoolkit.group;

import java.io.IOException;

/**
 * Message group exporter interface.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 12/10/2011
 * @since 	JDK1.5
 */
public interface GroupExporter {
	
	/**
	 * Initializes group exporter.
	 * 
	 * @throws IOException if an I/O error occurs while initializing.
	 */
	public void init() throws IOException;
	
	/**
	 * Destroys group exporter.
	 * 
	 * @throws IOException if an I/O error occurs while destroying.
	 */
	public void destroy() throws IOException;
	
	/**
	 * Tests whether the group exporter is ready.
	 * 
	 * @return {@code true} if and only if the group exporter has initialized;
	 * 		   {@code false} otherwise.
	 */
	public boolean isReady();

	/**
	 * Gets message publisher.
	 * 
	 * @return message publisher.
	 */
    public MessagePublisher getMessagePublisher();

    /**
     * Sets message receivers.
     * 
     * @param receivers message receivers.
     */
    public void setMessageReceivers(MessageReceiver[] receivers);
    
    /**
     * Gets all of message receivers.
     * 
     * @return all of message receivers.
     */
    public MessageReceiver[] getMessageReceivers();
    
    /**
     * Adds a receiver to message receivers.
     * 
     * @param receiver message receiver.
     * 
     * @return {@code true} if and only if add the receiver succeed;
     * 		   {@code false} otherwise.
     */
    public boolean addMessageReceiver(MessageReceiver receiver);
    
    /**
     * Removes a receiver from message receivers.
     * 
     * @param receiver message receiver.
     * 
     * @return {@code true} if and only if remove the receiver succeed;
     * 		   {@code false} otherwise.
     */
    public boolean removeMessageReceiver(MessageReceiver receiver);
    
}
