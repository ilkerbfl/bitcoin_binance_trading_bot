package com.bitcoin.web.rest;

import com.bitcoin.service.OrderService;
import com.bitcoin.wrapper.bittrexJson2JavaObjects.publicApiObjects.MarketSummary;
import com.bitcoin.wrapper.bittrexJson2JavaObjects.publicApiObjects.OrderBook;
import com.bitcoin.wrapper.dto.BuyOrderTotalSellOrderTotal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by İlker ÇATAK on 12/8/17.
 */
@RestController
@RequestMapping("bittrex")
public class OrderController {

//    private final Logger log = LoggerFactory.getLogger(OrderController.class);
//
//    private OrderService orderService;
//
//    public OrderController(OrderService orderService) {
//        this.orderService=orderService;
//    }
//
//    //Marketin tüm orderların döner
//    @GetMapping("/getOrderBook/{marketName}")
//    public OrderBook getOrdersByMarketName(@PathVariable("marketName") String marketName){
//        return orderService.getOrdersByMarketName(marketName);
//    }
//
//    //parametrede gelen sayı kadar derinlikte orderları karşılaştırır döner
//    @GetMapping("/getFirstOrders/{marketName}")
//    public BuyOrderTotalSellOrderTotal getOrdersQuantityByMarketNameAndCount(@PathVariable("marketName") String marketName, @RequestParam(value = "count", required = true) int count){
//        return  orderService.getOrdersQuantityByMarketNameAndCount(marketName,count);
//    }
//
//    @GetMapping("/getPopularMarketsFirstOrders")
//    public List<BuyOrderTotalSellOrderTotal> getPopularMarketsFirstOrders(@RequestParam(value = "count") int count){
//        return orderService.getPopularMarketsFirstOrders(count);
//    }
//
//    @GetMapping("/getBalances")
//    public String getBalances(){
//        return orderService.getBalances();
//    }
//
//    @GetMapping("/compareBuySellOrderCount")
//    public List<MarketSummary> compareBuySellOrderCount(){
//        return orderService.compareBuySellOrderCount();
//    }
}
