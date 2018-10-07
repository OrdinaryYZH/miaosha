package com.genericyzh.miaosha.good.controller;

import com.genericyzh.miaosha.access.UserContext;
import com.genericyzh.miaosha.good.model.vo.GoodVo;
import com.genericyzh.miaosha.good.service.GoodService;
import com.genericyzh.miaosha.redis.RedisClient;
import com.genericyzh.miaosha.redis.key.GoodKey;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodController {

    @Autowired
    private GoodService goodService;

    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;


    @RequestMapping(value = "/to_list", produces = "text/html")
    @ResponseBody
    public String list(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("user", UserContext.getUser());
        //取缓存
        String cacheHtml = RedisClient.execute(jedis -> jedis.get(GoodKey.getGoodsList.getPrefix()));
        if (!StringUtils.isEmpty(cacheHtml)) {
            return cacheHtml;
        }
        List<GoodVo> goodsList = goodService.listGoodsVo();
        model.addAttribute("goodsList", goodsList);
//    	 return "goods_list";
        WebContext ctx = new WebContext(request, response, request.getServletContext(),
                request.getLocale(), model.asMap());
        //手动渲染
        String html = thymeleafViewResolver.getTemplateEngine().process("goods_list", ctx);
        if (!StringUtils.isEmpty(html)) {
            RedisClient.execute(jedis -> jedis.set(GoodKey.getGoodsList.getPrefix(), html, "nx", "ex", 60));
        }
        return html;
    }

}
