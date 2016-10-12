package cn.zkw.common.rabbitmq.service.impl;

import cn.zkw.common.rabbitmq.common.SysConfig;
import cn.zkw.common.rabbitmq.service.TaskService;
import com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.apache.log4j.Logger;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.ChannelCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Created with IntelliJ IDEA.
 * User: zkw
 * Date: 1/4/15
 * Time: 1:30 下午
 * To change this template use File | Settings | File Templates.
 */
@Service
public class TaskServiceImpl implements TaskService {

    private static final Logger logger = Logger.getLogger(TaskServiceImpl.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private SysConfig config;

    private static Gson gson;
    private static BASE64Encoder encoder;
    private static BASE64Decoder decoder;


    static {
        try {
            gson = new Gson();
            encoder = new BASE64Encoder();
            decoder = new BASE64Decoder();
        } catch (Exception e) {
            logger.error("Gson build error: ", e);
        }
    }

    @Override
    public void ingest(String content) {
        try {
            //TODO
            System.out.println(content);
        } catch (Exception e) {
            logger.error("unknown error happend " + e.getMessage());
        }
        finally {
        }
    }

    @Override
    public void query(String content) {
        try {
            this.sendResponse(content);
        } catch (Exception e) {
            logger.error("unknown error happend " + e.getMessage());
        }
        finally {
        }
    }

    private void sendResponse(String response) {
        logger.info("Send respons = " + response);
        final String queueName = config.getMqResultQueueName();
        final String key = config.getMqResultQueueName();
        try {
            rabbitTemplate.execute(new ChannelCallback<Object>() {
                @Override
                public Object doInRabbit(Channel channel) throws Exception {
                    AMQP.Queue.DeclareOk declareOk = channel.queueDeclarePassive(queueName);
                    channel.queueBind(declareOk.getQueue(), config.getMqExchange(), key);
                    return null;
                }
            });
            rabbitTemplate.convertAndSend(config.getMqExchange(), key, encoder.encode(response.getBytes()));
            logger.info("Send success");
        } catch (AmqpException e) {
            logger.error("Send Response Error", e);
        }
    }

}
