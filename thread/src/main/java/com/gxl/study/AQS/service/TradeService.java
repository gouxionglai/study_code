package com.gxl.study.AQS.service;

import com.gxl.study.AQS.current.CustomLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author gouxi
 * @description
 * @since 2020/5/10
 */
@Service
public class TradeService {

    private Logger logger = LoggerFactory.getLogger(TradeService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    //实际生产就直接用 ReentrntLock
    CustomLock lock = new CustomLock();

    public String decStockNoLock(){
        //synchronized, lock -->reentrantLock
        //加锁
        lock.lock();
        //存货
        Integer stock;
        List<Map<String,Object>> result = jdbcTemplate.queryForList("select stock from shop_order where id = 10");
        //判断是否还有库存
        if(result == null || result.isEmpty() || (stock=(Integer)result.get(0).get("stock"))<=0){
            //没有库存了
            logger.error("没有库存了");
            lock.unlock();
            return "out of stock";
        }
        //减库存
        stock --;
        jdbcTemplate.update("update shop_order set stock = ? where id = 10",stock);
        logger.info("下单成功，扣减库存，当前剩余："+stock);

        //解锁
        lock.unlock();
        return "下单成功，扣减库存，当前剩余："+stock;
    }
}
