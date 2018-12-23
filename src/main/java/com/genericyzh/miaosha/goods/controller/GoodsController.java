package com.genericyzh.miaosha.goods.controller;

import com.genericyzh.miaosha.access.UserContext;
import com.genericyzh.miaosha.goods.model.vo.GoodsDetailVO;
import com.genericyzh.miaosha.goods.model.vo.GoodsVO;
import com.genericyzh.miaosha.goods.service.GoodsService;
import com.genericyzh.miaosha.redis.RedisClient;
import com.genericyzh.miaosha.redis.key.GoodKey;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    RedisClient redisClient;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;


    @RequestMapping(value = "/to_list", produces = "text/html")
    @ResponseBody
    public String list(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("user", UserContext.getUser());
        //取缓存
        String cacheHtml = redisClient.execute(jedis -> jedis.get(GoodKey.goodsList.getPrefix()));
        if (!StringUtils.isEmpty(cacheHtml)) {
            return cacheHtml;
        }
        List<GoodsVO> goodsList = goodsService.listGoodsVo();
        model.addAttribute("goodsList", goodsList);
//    	 return "goods_list";
        WebContext ctx = new WebContext(request, response, request.getServletContext(),
                request.getLocale(), model.asMap());
        //手动渲染
        String html = thymeleafViewResolver.getTemplateEngine().process("goods_list", ctx);
        if (!StringUtils.isEmpty(html)) {
            redisClient.execute(jedis -> jedis.set(GoodKey.goodsList.getPrefix(), html, "nx", "ex", 60));
        }
        return html;
    }

    @RequestMapping(value = "/detail/{goodsId}")
    @ResponseBody
    public GoodsDetailVO detail(
            @PathVariable("goodsId") long goodsId) {
        GoodsDetailVO.MiaoshaGoodsDetail goods = goodsService.getMiaoshaGoodDetail(goodsId);
        long startAt = goods.getStartDate().getTime();
        long endAt = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();
        int miaoshaStatus = 0;
        int remainSeconds = 0;
        if (now < startAt) {//秒杀还没开始，倒计时
            miaoshaStatus = 0;
            remainSeconds = (int) ((startAt - now) / 1000);
        } else if (now > endAt) {//秒杀已经结束
            miaoshaStatus = 2;
            remainSeconds = -1;
        } else {//秒杀进行中
            miaoshaStatus = 1;
            remainSeconds = 0;
        }
        GoodsDetailVO vo = new GoodsDetailVO();
        vo.setGoods(goods);
        vo.setUser(UserContext.getUser());
        vo.setRemainSeconds(remainSeconds);
        vo.setMiaoshaStatus(miaoshaStatus);
        return vo;
    }


}
