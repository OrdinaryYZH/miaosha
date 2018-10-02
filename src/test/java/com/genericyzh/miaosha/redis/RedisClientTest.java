package com.genericyzh.miaosha.redis;

import com.genericyzh.miaosha.utils.SerializeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author genericyzh
 * @date 2018/10/2 17:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisClientTest implements ApplicationContextAware {

    private ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Test
    public void execute() throws Exception {
        // 简单的set(String, String)
        RedisClient.execute(jedis -> jedis.set("hello2", "world"));
    }

    @Test
    public void serials() throws Exception {
        // 设置Bean序列化后到redis中
        User alice = new User("Alice", 10);
        RedisClient.execute(jedis -> jedis.set("user1", SerializeUtil.beanToString(alice)));
    }

    @Test
    public void deSerials() throws Exception {
        // 反序列化
        User user1 = RedisClient.execute(jedis -> jedis.get("user1"), User.class);
        System.out.println(user1);
    }

}