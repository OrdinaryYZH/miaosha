package com.genericyzh.miaosha;

import org.apache.logging.log4j.core.config.Order;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author genericyzh
 * @date 2018/12/16 1:12
 */
@Configuration("RedisConfiguration")
@Order(value = 1)
@ComponentScan(value = "com.genericyzh.miaosha.redis.*")
public class RedisConfig {
}
