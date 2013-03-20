package com.mtoolkit.group.support;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.mtoolkit.group.Message;
import com.mtoolkit.group.MessagePublisher;
import com.mtoolkit.group.MessageReceiver;

/**
 * 基于JMS的消息群发工厂的实现。
 * @version 1.00 2010-10-7, 14:33:59
 * @since JDK 1.5
 * @author ZhangShixi
 */
public class JmsGroupExporter extends AbstractGroupExporter {

    @Override
    public MessagePublisher getMessagePublisher() {
        return null;
    }

    @Override
    protected void doInit() throws IOException {
    }

    @Override
    protected void doDestroy() throws IOException {
    }

    
//    //==== public constants ====================================================
//    /**
//     * 默认消息执行线程数，默认开启一个线程执行接收消息的发送操作。
//     */
//    public static final int DEFAULT_EXECUTIVE_THREAD_NUMBER = 1;
//
//    //==== setter/getter methods ===============================================
//    /**
//     * 获取主题地址。
//     * @return 主题地址。
//     */
//    public Topic getTopic() {
//        return _topic;
//    }
//
//    /**
//     * 设置主题地址。
//     * @param topic 主题地址。
//     */
//    public void setTopic(Topic topic) {
//        this._topic = topic;
//    }
//
//    /**
//     * 获取执行接收消息的发送操作的线程数。
//     * @return 执行接收消息的发送操作的线程数。
//     * @see #setExecutiveThreadNumber(int)
//     */
//    public int getExecutiveThreadNumber() {
//        return _executiveThreadNumber;
//    }
//
//    /**
//     * 设置执行接收消息的发送操作的线程数。
//     * 默认大小为{@link #DEFAULT_EXECUTIVE_THREAD_NUMBER}。
//     * @param _executiveThreadNumber 执行接收消息的发送操作的线程数。
//     * @see #DEFAULT_EXECUTIVE_THREAD_NUMBER 默认执行接收消息发送操作的线程数。
//     */
//    public void setExecutiveThreadNumber(int _executiveThreadNumber) {
//        this._executiveThreadNumber = _executiveThreadNumber;
//    }
//
//    /**
//     * 获取主题连接工厂。
//     * @return 主题连接工厂。
//     */
//    public TopicConnectionFactory getTopicConnectionFactory() {
//        return _topicConnectionFactory;
//    }
//
//    /**
//     * 设置主题连接工厂。
//     * @param topicConnectionFactory 主题连接工厂。
//     */
//    public void setTopicConnectionFactory(TopicConnectionFactory topicConnectionFactory) {
//        this._topicConnectionFactory = topicConnectionFactory;
//    }
//
//    //==== public methods ======================================================
//    /**
//     * 初始化方法。
//     * @throws com.codingfarmer.remote.RemoteException
//     */
//    @Override
//    protected void doInit() throws RemoteException {
//        if (isReady()) {
//            LOGGER.warn("Jms group executor has initialized.");
//            return;
//        }
//
//        checkTopic(_topic);
//        checkTopicConnectionFactory(_topicConnectionFactory);
//
//        initPublishJmsGroup();
//        initReceiveJmsGroup();
//
//        initMessageExecutiveThreadPool();
//        startGroupMessageReceiveListener();
//    }
//
//    /**
//     * 销毁方法。
//     * @throws com.codingfarmer.remote.RemoteException
//     */
//    @Override
//    protected void doDestroy() throws RemoteException {
//        if (!isReady()) {
//            LOGGER.warn("Jms group executor is not inilialized or has destroyed.");
//            return;
//        }
//
//        try {
//            _publishPublisher.close();
//            _publishSession.close();
//            _receiveSubscriber.close();
//        } catch (JMSException ex) {
//            LOGGER.error(ex.getMessage(), ex);
//        }
//
//        _executiveThreadPool.shutdown();
//    }
//
//    /**
//     * 获取群发消息的消息发布者。
//     * @return 群发消息的消息发布者。
//     */
//    @Override
//    public MessagePublisher getMessagePublisher() {
//        return new JmsGroupMessagePublisher();
//    }
//
//    /**
//     * 批量添加消息接收者。
//     * @param receivers 消息接收者列表。
//     */
//    @Override
//    public void setMessageReceivers(MessageReceiver[] receivers) {
//        if (receivers == null || receivers.length == 0) {
//            LOGGER.warn("Add message receivers is null or empty.");
//            return;
//        }
//
//        registerMessageReceivers(receivers);
//    }
//
//    //==== private methods =====================================================
//    /**
//     * 检查主题地址的合法性，如果主题地址为null，将抛出非法参数异常。
//     * @param topic 主题地址。
//     * @throws java.lang.IllegalArgumentException 非法参数异常。
//     */
//    private void checkTopic(Topic topic) throws IllegalArgumentException {
//        if (topic == null) {
//            throw new IllegalArgumentException("topic is not seted.");
//        }
//    }
//
//    /**
//     * 检查主题连接工厂的合法性，如果主题连接工厂为空，将抛出非法参数异常。
//     * @param topicConnectionFactory 主题连接工厂。
//     * @throws java.lang.IllegalArgumentException 非法参数异常。
//     */
//    private void checkTopicConnectionFactory(
//            TopicConnectionFactory topicConnectionFactory)
//            throws IllegalArgumentException {
//        if (topicConnectionFactory == null) {
//            throw new IllegalArgumentException("topic connection factory is not seted.");
//        }
//    }
//
//    /**
//     * 初始化执行接收消息发送操作的线程池。
//     */
//    private void initMessageExecutiveThreadPool() {
//        _executiveThreadPool = Executors.newFixedThreadPool(_executiveThreadNumber);
//    }
//
//    /**
//     * 注册消息接收的监听器，开启接收消息，并将接收到的消息发送给所有在册的消息接收者。
//     * @throws com.codingfarmer.remote.RemoteException JMS异常。
//     */
//    private void startGroupMessageReceiveListener() throws RemoteException {
//        try {
//            _receiveSubscriber.setMessageListener(new JmsGroupReceiveMessageListener());
//        } catch (JMSException ex) {
//            LOGGER.error(ex.getMessage(), ex);
//            throw new RemoteException(ex.getMessage(), ex);
//        }
//    }
//
//    private void initPublishJmsGroup() throws RemoteException {
//        TopicConnection publishConnection = null;
//        try {
//            publishConnection = _topicConnectionFactory.createTopicConnection();
//            _publishSession = publishConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
//            _publishPublisher = _publishSession.createPublisher(_topic);
//        } catch (JMSException ex) {
//            LOGGER.error(ex.getMessage(), ex);
//            throw new RemoteException(ex.getMessage(), ex);
//        } finally {
//            close(publishConnection);
//        }
//    }
//
//    private void initReceiveJmsGroup() throws RemoteException {
//        TopicConnection receiveConnection = null;
//        TopicSession receiveSession = null;
//        try {
//            receiveConnection = _topicConnectionFactory.createTopicConnection();
//            receiveSession = receiveConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
//            _receiveSubscriber = receiveSession.createSubscriber(_topic);
//        } catch (JMSException ex) {
//            LOGGER.error(ex.getMessage(), ex);
//            throw new RemoteException(ex.getMessage(), ex);
//        } finally {
//            close(receiveSession, receiveConnection);
//        }
//    }
//
//    /**
//     * 关闭主题连接。
//     * @param connection 主题连接。
//     * @throws com.codingfarmer.remote.RemoteException 关闭JMS资源异常。
//     */
//    private void close(TopicConnection connection) throws RemoteException {
//        close(null, connection);
//    }
//
//    /**
//     * 关闭相应的资源。
//     * @param session 主题会话。
//     * @param connection 主题连接。
//     * @throws com.codingfarmer.remote.RemoteException 关闭JMS资源异常。
//     */
//    private void close(TopicSession session, TopicConnection connection)
//            throws RemoteException {
//        try {
//            if (session != null) {
//                session.close();
//            }
//            if (connection != null) {
//                connection.close();
//            }
//        } catch (JMSException ex) {
//            LOGGER.error(ex.getMessage(), ex);
//            throw new RemoteException(ex.getMessage(), ex);
//        }
//    }
//
//    /**
//     * 注册给定的消息接收者列表。
//     * @param receivers 消息接收者列表。
//     */
//    private synchronized void registerMessageReceivers(MessageReceiver[] receivers) {
//        if (this._receivers == null) {
//            this._receivers = receivers;
//        } else {
//            MessageReceiver[] allReceivers =
//                    new MessageReceiver[_receivers.length + receivers.length];
//
//            System.arraycopy(_receivers, 0, allReceivers, 0, _receivers.length);
//            System.arraycopy(receivers, 0, allReceivers, _receivers.length, receivers.length);
//
//            this._receivers = allReceivers;
//        }
//    }
//
//    //==== private inner classes ===============================================
//    /**
//     * 基于JMS群发消息的消息接收监听器。
//     */
//    private class JmsGroupReceiveMessageListener implements MessageListener {
//
//        @Override
//        public void onMessage(javax.jms.Message message) {
//            if (message instanceof ObjectMessage) {
//                ObjectMessage objectMessage = (ObjectMessage) message;
//                Message publishMessage = null;
//                try {
//                    publishMessage = (Message) objectMessage.getObject();
//                } catch (JMSException ex) {
//                    LOGGER.error(ex.getMessage(), ex);
//                    return;
//                }
//
//                if (publishMessage != null) {
//                    sendMessageToReceivers(publishMessage);
//                }
//            }
//        }
//
//        /**
//         * 新开一个线程，将消息发送给注册的每个消息接收者。
//         * @param message 发送的消息。
//         */
//        private void sendMessageToReceivers(Message message) {
//            LOGGER.debug("Begin to receive publish message ...");
//
//            _executiveThreadPool.submit(new PublishMessageThread(message, _receivers));
//        }
//    }
//
//    /**
//     * 基于JMS的群发消息的消息发布者实现。
//     */
//    private class JmsGroupMessagePublisher implements MessagePublisher {
//
//        /**
//         * 群发消息的发布方法。
//         * @param message 消息。
//         * @throws java.io.IOException 群发消息异常。
//         */
//        @Override
//        public void publish(Message message) throws IOException {
//            checkReady(isReady());
//            checkMessage(message);
//
//            try {
//                _publishPublisher.publish(_publishSession.createObjectMessage(message));
//            } catch (JMSException ex) {
//                LOGGER.error(ex.getMessage(), ex);
//                throw new IOException(ex.getMessage(), ex);
//            }
//        }
//
//        /**
//         * 检查是否已经准备就绪，如果未准备就绪，将抛出非法参数异常。
//         * @param ready 是否已经准备就绪。
//         * @throws java.lang.IllegalArgumentException 非法参数异常。
//         */
//        private void checkReady(boolean ready) throws IllegalArgumentException {
//            if (!ready) {
//                throw new IllegalArgumentException(
//                        "socket group exporter has not init ready.");
//            }
//        }
//
//        /**
//         * 检查要群发的消息的合法性，如果为null，将抛出非法参数异常。
//         * @param message 要群发的消息。
//         * @throws java.lang.IllegalArgumentException 非法参数异常。
//         */
//        private void checkMessage(Message message) throws IllegalArgumentException {
//            if (message == null) {
//                throw new IllegalArgumentException(
//                        "socket group exporter publish message is null.");
//            }
//        }
//    }
//    //==== private attributes ==================================================
//    /**
//     * 消息执行线程数。
//     */
//    private int _executiveThreadNumber = DEFAULT_EXECUTIVE_THREAD_NUMBER;
//    /**
//     * 主题地址。
//     */
//    private Topic _topic;
//    /**
//     * 主题连接工厂。
//     */
//    private TopicConnectionFactory _topicConnectionFactory;
//    /**
//     *
//     */
//    private TopicSession _publishSession;
//    /**
//     * 
//     */
//    private TopicPublisher _publishPublisher;
//    /**
//     * 主题接收订阅器。
//     */
//    private TopicSubscriber _receiveSubscriber;
//    /**
//     * 消息接受者列表。
//     */
//    private MessageReceiver[] _receivers;
//    /**
//     * 执行接收消息发送操作的线程池。
//     */
//    private static ExecutorService _executiveThreadPool;
}
