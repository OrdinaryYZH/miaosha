package com.genericyzh.miaosha.miaosha.service.impl;

import com.genericyzh.miaosha.access.UserContext;
import com.genericyzh.miaosha.common.exception.BusinessException;
import com.genericyzh.miaosha.goods.model.MiaoshaGoods;
import com.genericyzh.miaosha.goods.service.GoodsService;
import com.genericyzh.miaosha.miaosha.model.MiaoshaOrder;
import com.genericyzh.miaosha.miaosha.service.MiaoshaService;
import com.genericyzh.miaosha.order.model.OrderInfo;
import com.genericyzh.miaosha.order.service.OrderService;
import com.genericyzh.miaosha.rabbitmq.MiaoshaMessage;
import com.genericyzh.miaosha.redis.key.GoodKey;
import com.genericyzh.miaosha.redis.key.MiaoshaKey;
import com.genericyzh.miaosha.user.model.UserInfo;
import com.genericyzh.miaosha.utils.MD5Util;
import com.genericyzh.miaosha.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static com.genericyzh.miaosha.redis.RedisClient.execute;

/**
 * @author genericyzh
 * @date 2018/10/10 20:32
 */
@Service
@DependsOn("RedisPoolFactory")
public class MiaoshaServiceImpl implements MiaoshaService {

    @Autowired
    OrderService orderService;

    @Autowired
    GoodsService goodsService;

    private HashMap<Long, Boolean> localOverMap = new HashMap<>();

    /**
     * 系统初始化
     * 注意该方法依赖RedisPoolFactory的静态属性，如果不显式声明@DependsOn的话，RedisPoolFactory是不会先初始化的
     */
    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        List<MiaoshaGoods> goodVOS = goodsService.listMiaoshaGoods();
        if (goodVOS == null) {
            return;
        }
        for (MiaoshaGoods goods : goodVOS) {
            execute(jedis -> jedis.set(GoodKey.miaoshaGoodsStock.appendPrefix(String.valueOf(goods.getId())),
                    String.valueOf(goods.getStockCount())));
            localOverMap.put(goods.getId(), false);
        }
    }

    @Override
    public long getMiaoshaResult(Long userId, long goodsId) {
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(String.valueOf(userId), goodsId);
        if (order != null) {//秒杀成功
            return order.getOrderId();
        } else {
            boolean isOver = getGoodsOver(goodsId);
            if (isOver) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    @Override
    public void setGoodsOver(Long goodsId) {

    }

    @Override
    public boolean getGoodsOver(long goodsId) {
        return false;
    }

    @Override
    public boolean checkPath(UserInfo user, long goodsId, String path) {
        if (user == null || path == null) {
            return false;
        }
        String pathOld = execute(jedis -> jedis.get(MiaoshaKey.miaoshaPath.appendPrefix(user.getId(), String.valueOf(goodsId))));
        return path.equals(pathOld);
    }

    @Override
    public String createMiaoshaPath(UserInfo user, long goodsId) {
        if (user == null || goodsId <= 0) {
            return null;
        }
        String str = MD5Util.md5(UUIDUtil.uuid() + "123456");
        execute(jedis -> jedis.setex(MiaoshaKey.miaoshaPath.appendPrefix(user.getId(), String.valueOf(goodsId)),
                MiaoshaKey.miaoshaPath.expireSeconds(),
                str));
        return str;
    }

    @Override
    public BufferedImage createVerifyCode(UserInfo user, long goodsId) {
        if (user == null || goodsId <= 0) {
            return null;
        }
        int width = 80;
        int height = 32;
        //create the image
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        // set the background color
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0, 0, width, height);
        // draw the border
        g.setColor(Color.black);
        g.drawRect(0, 0, width - 1, height - 1);
        // create a random instance to generate the codes
        Random rdm = new Random();
        // make some confusion
        for (int i = 0; i < 50; i++) {
            int x = rdm.nextInt(width);
            int y = rdm.nextInt(height);
            g.drawOval(x, y, 0, 0);
        }
        // generate a random code
        String verifyCode = generateVerifyCode(rdm);
        g.setColor(new Color(0, 100, 0));
        g.setFont(new Font("Candara", Font.BOLD, 24));
        g.drawString(verifyCode, 8, 24);
        g.dispose();
        //把验证码存到redis中
        int rnd = calc(verifyCode);
        execute(jedis -> jedis.setex(MiaoshaKey.miaoshaVerifyCode.appendPrefix(user.getId(), String.valueOf(goodsId)),
                MiaoshaKey.miaoshaVerifyCode.expireSeconds(),
                String.valueOf(rnd)
        ));
        //输出图片
        return image;
    }

    @Override
    public boolean checkVerifyCode(UserInfo user, long goodsId, int verifyCode) {
        if (user == null || goodsId <= 0) {
            return false;
        }
        Integer codeOld = execute(jedis -> jedis.get(MiaoshaKey.miaoshaVerifyCode.appendPrefix(user.getId(), String.valueOf(goodsId))), Integer.class);
        if (codeOld == null) {
            throw new BusinessException("验证码过期");
        }
        if (codeOld != verifyCode) {
            throw new BusinessException("验证码不正确");
        }
        execute(jedis -> jedis.del(MiaoshaKey.miaoshaVerifyCode.appendPrefix(user.getId())));
        return true;
    }

    @Override
    public void checkBeforeMiaosha(long goodsId, String path) {
        UserInfo user = UserContext.getUser();
        //验证path
        boolean check = checkPath(user, goodsId, path);
        if (!check) {
            throw new BusinessException("验证路径错误");
        }
        //内存标记，减少redis访问
        boolean over = localOverMap.get(goodsId);
        if (over) {
            throw new BusinessException("商品已经秒杀完毕");
        }
        //判断是否已经秒杀到了
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
        if (order != null) {
            throw new BusinessException("不能重复秒杀");
        }
        //预减库存
        long stock = execute(jedis -> jedis.decr(GoodKey.miaoshaGoodsStock.appendPrefix(String.valueOf(goodsId))));
        if (stock < 0) {
            localOverMap.put(goodsId, true);
            throw new BusinessException("商品已经秒杀完毕");
        }
    }

    @Override
    public OrderInfo miaosha(MiaoshaMessage message) {
        UserInfo user = message.getUser();
        long goodId = message.getGoodsId();

        MiaoshaGoods miaoshaGoods = goodsService.getMiaoshaGood(goodId);
        // 检查库存
        int stock = miaoshaGoods.getStockCount();
        if (stock <= 0) {
            throw new BusinessException("已经卖完了");
        }
        //判断是否已经秒杀到了
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodId);
        if (order != null) {
            throw new BusinessException("您已经买过该商品了，不能重复秒杀");
        }
        //减库存 下订单 写入秒杀订单
        boolean success = goodsService.reduceStock(goodId);
        if (success) {
            //order_info maiosha_order
            return orderService.createOrder(user, miaoshaGoods);
        } else {
            setGoodsOver(miaoshaGoods.getId());
            return null;
        }
    }

    @Override
    public void reset(List<MiaoshaGoods> goodsList) {
        goodsService.resetStock(goodsList);
        orderService.deleteOrders();
    }

    private static int calc(String exp) {
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript");
            return (Integer) engine.eval(exp);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static char[] ops = new char[]{'+', '-', '*'};

    /**
     * + - *
     */
    private String generateVerifyCode(Random rdm) {
        int num1 = rdm.nextInt(10);
        int num2 = rdm.nextInt(10);
        int num3 = rdm.nextInt(10);
        char op1 = ops[rdm.nextInt(3)];
        char op2 = ops[rdm.nextInt(3)];
        String exp = "" + num1 + op1 + num2 + op2 + num3;
        return exp;
    }
}
