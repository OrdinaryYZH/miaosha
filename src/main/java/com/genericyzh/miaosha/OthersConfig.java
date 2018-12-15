package com.genericyzh.miaosha;

import org.apache.logging.log4j.core.config.Order;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.FilterType;

/**
 * @author genericyzh
 * @date 2018/12/16 1:12
 */
@Configuration
@Order(value = 2)
@ComponentScan(excludeFilters = @ComponentScan.Filter(
        type = FilterType.REGEX,
        pattern = "com.genericyzh.miaosha.redis.*"))
@DependsOn("RedisPoolFactory")
public class OthersConfig {
}
