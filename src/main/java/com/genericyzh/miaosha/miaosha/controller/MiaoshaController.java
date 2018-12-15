package com.genericyzh.miaosha.miaosha.controller;

import com.genericyzh.miaosha.access.AccessLimit;
import com.genericyzh.miaosha.access.UserContext;
import com.genericyzh.miaosha.common.exception.BusinessException;
import com.genericyzh.miaosha.common.result.ResultBean;
import com.genericyzh.miaosha.good.model.MiaoshaGood;
import com.genericyzh.miaosha.good.service.GoodService;
import com.genericyzh.miaosha.miaosha.service.MiaoshaService;
import com.genericyzh.miaosha.order.service.OrderService;
import com.genericyzh.miaosha.rabbitmq.MQSender;
import com.genericyzh.miaosha.rabbitmq.MiaoshaMessage;
import com.genericyzh.miaosha.redis.key.GoodKey;
import com.genericyzh.miaosha.redis.key.MiaoshaKey;
import com.genericyzh.miaosha.redis.key.OrderKey;
import com.genericyzh.miaosha.user.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import static com.genericyzh.miaosha.common.result.ResultCode.SUCCESS;
import static com.genericyzh.miaosha.redis.RedisClient.execute;

/**
 * @author genericyzh
 * @date 2018/10/10 20:25
 */
@Controller
@RequestMapping("miaosha")
public class MiaoshaController {

    @Autowired
    MiaoshaService miaoshaService;

    @Autowired
    OrderService orderService;

    @Autowired
    GoodService goodService;

    @Autowired
    MQSender sender;

    @GetMapping(value = "/verifyCode")
    @ResponseBody
    public ResultBean<String> getMiaoshaVerifyCod(HttpServletResponse response,
                                                  @RequestParam("goodId") long goodsId) throws IOException {
        BufferedImage image = miaoshaService.createVerifyCode(UserContext.getUser(), goodsId);
        OutputStream out = response.getOutputStream();
        ImageIO.write(image, "JPEG", out);
        out.flush();
        out.close();
        return null;
    }

    @AccessLimit(seconds = 5, maxCount = 5, needLogin = true)
    @RequestMapping(value = "/path", method = RequestMethod.GET)
    @ResponseBody
    public String getMiaoshaPath(
            @RequestParam("goodsId") long goodsId,
            @RequestParam(value = "verifyCode", defaultValue = "0") int verifyCode) {
        UserInfo user = UserContext.getUser();
        boolean flag = miaoshaService.checkVerifyCode(user, goodsId, verifyCode);
        if (!flag) {
            throw new BusinessException("验证失败");
        }
        return miaoshaService.createMiaoshaPath(user, goodsId);
    }

    /**
     * QPS:1306
     * 5000 * 10
     * QPS: 2114
     */
    @RequestMapping(value = "/{path}/do_miaosha", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean<Integer> miaosha(
            @RequestParam("goodsId") long goodsId,
            @PathVariable("path") String path) {
        if (goodsId < 0) {
            throw new IllegalArgumentException("goodsId参数格式不对");
        }
        miaoshaService.checkBeforeMiaosha(goodsId, path);
        //入队
        MiaoshaMessage mm = new MiaoshaMessage();
        mm.setUser(UserContext.getUser());
        mm.setGoodsId(goodsId);
        sender.sendMiaoshaMessage(mm);
        return ResultBean.builder(SUCCESS).set("排队中", 1).build();//排队中
    }

    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    @ResponseBody
    public Boolean reset() {
        List<MiaoshaGood> goods = goodService.listMiaoshaGoods();
        for (MiaoshaGood good : goods) {
            good.setStockCount(10);
            execute(jedis -> jedis.set(GoodKey.miaoshaGoodsStock.appendPrefix(good.getId().toString()), "10"));
//            redisService.set(GoodsKey.getMiaoshaGoodsStock, "" + good.getId(), 10);
        }
        ScanParams scanParams = new ScanParams();
        scanParams.match(OrderKey.MiaoshaOrderByUidGid.getPrefix() + "*");
        scanParams.count(1000);
        ScanResult<String> result = execute(jedis -> jedis.scan("1", scanParams));
        execute(jedis -> jedis.del(result.getResult().toArray(new String[result.getResult().size()])));

        scanParams.match(MiaoshaKey.isGoodsOver + "*");
        scanParams.count(1000);
        ScanResult<String> result2 = execute(jedis -> jedis.scan("1", scanParams));
        execute(jedis -> jedis.del(result2.getResult().toArray(new String[result2.getResult().size()])));

        miaoshaService.reset(goods);
        return true;
    }


}
