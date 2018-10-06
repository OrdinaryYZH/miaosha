package com.genericyzh.miaosha.good;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @GetMapping("to_list")
    @ResponseBody
    public Object toList() {
        return null;
    }

}
