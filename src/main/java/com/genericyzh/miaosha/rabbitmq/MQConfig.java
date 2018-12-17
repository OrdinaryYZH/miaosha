package com.genericyzh.miaosha.rabbitmq;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@Configuration
public class MQConfig {

    public static final String MIAOSHA_QUEUE = "miaosha.queue";

    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * 定制化amqp模版      可根据需要定制多个
     * 此处为模版类定义 Jackson消息转换器
     * ConfirmCallback接口用于实现消息发送到RabbitMQ交换器后接收ack回调   即消息发送到exchange  ack
     * ReturnCallback接口用于实现消息发送到RabbitMQ 交换器，但无相应队列与交换器绑定时的回调  即消息发送不到任何一个队列中  ack
     *
     * @return the amqp template
     */
//    @Bean
//    public AmqpTemplate rabbitTemplate() {
//        Logger log = LoggerFactory.getLogger(RabbitTemplate.class);
//        // 使用jackson 消息转换器
//        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
//        rabbitTemplate.setEncoding("UTF-8");
//        // 消息发送失败返回到队列中，yml需要配置 publisher-returns: true
//        rabbitTemplate.setMandatory(true);
//        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
//            String correlationId = message.getMessageProperties().getCorrelationId();
//            log.debug("消息：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}", correlationId, replyCode, replyText, exchange, routingKey);
//        });
//        // 消息确认，yml需要配置 publisher-confirms: true
//        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
//            if (ack) {
//                log.debug("消息发送到exchange成功,id: {}", correlationData.getId());
//            } else {
//                log.debug("消息发送到exchange失败,原因: {}", cause);
//            }
//        });
//        return rabbitTemplate;
//    }

    /**
     * 这是啥？？？
     *
     * @return
     */
//    @Bean
//    public DefaultMessageHandlerMethodFactory myHandlerMethodFactory() {
//        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
//        factory.setMessageConverter(new MappingJackson2MessageConverter());
//        return factory;
//    }

//    @Bean
//    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        // factory.setPrefetchCount(5);
//        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//        return factory;
//    }

    @Bean
    public Queue miaoshaQueue() {
        return new Queue(MIAOSHA_QUEUE, true);
    }

}
