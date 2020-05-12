package com.gxl.study.AQS.controller;

import com.gxl.study.AQS.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gouxi
 * @description
 * @since 2020/5/11
 */
@RestController
public class PublicController {

    @Autowired
    private TradeService tradeService;

    @RequestMapping("/order")
    public String order(){
        return tradeService.decStockNoLock();
    }

}
