package cn.zkw.common.rabbitmq.mq;

import cn.zkw.common.rabbitmq.service.TaskService;
import com.rabbitmq.client.Channel;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhu_kewei on 16-9-22.
 */
public class QueryConsumer implements ChannelAwareMessageListener {
    private final static Logger log = Logger.getLogger(QueryConsumer.class);

    @Autowired
    private TaskService taskService;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        String content = new String(message.getBody());
        log.info("Consumer Received Message = " + content);
        taskService.query(content);
        long tag = message.getMessageProperties().getDeliveryTag();
        log.info("Ack Message Tag = " + tag);
        channel.basicAck(tag, false);
    }
}
