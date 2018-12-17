package com.genericyzh.miaosha.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MQSender {

    private static Logger log = LoggerFactory.getLogger(MQSender.class);

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendMiaoshaMessage(MiaoshaMessage miaoshaMessage) {
        log.info("send message:" + miaoshaMessage);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(MQConfig.MIAOSHA_QUEUE, miaoshaMessage, correlationData);
    }

//	public void send(Object message) {
//		String msg = RedisService.beanToString(message);
//		log.info("send message:"+msg);
//		rabbitTemplate.convertAndSend(MQConfig.QUEUE, msg);
//	}
//	
//	public void sendTopic(Object message) {
//		String msg = RedisService.beanToString(message);
//		log.info("send topic message:"+msg);
//		rabbitTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE, "topic.key1", msg+"1");
//		rabbitTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE, "topic.key2", msg+"2");
//	}
//	
//	public void sendFanout(Object message) {
//		String msg = RedisService.beanToString(message);
//		log.info("send fanout message:"+msg);
//		rabbitTemplate.convertAndSend(MQConfig.FANOUT_EXCHANGE, "", msg);
//	}
//	
//	public void sendHeader(Object message) {
//		String msg = RedisService.beanToString(message);
//		log.info("send fanout message:"+msg);
//		MessageProperties properties = new MessageProperties();
//		properties.setHeader("header1", "value1");
//		properties.setHeader("header2", "value2");
//		Message obj = new Message(msg.getBytes(), properties);
//		rabbitTemplate.convertAndSend(MQConfig.HEADERS_EXCHANGE, "", obj);
//	}


}
