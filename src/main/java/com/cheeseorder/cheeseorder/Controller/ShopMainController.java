package com.cheeseorder.cheeseorder.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ShopMainController {



    @GetMapping("/Shop/{ShopId}")
    private String MainShopView(@PathVariable(name = "ShopId") String ShopId) {


        return "MainShopView";
    }
}
