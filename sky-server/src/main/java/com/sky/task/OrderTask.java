package com.sky.task;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;


/**
 * 定时任务类
 */
@Component
@Slf4j
public class OrderTask {

    @Autowired
    private OrderMapper orderMapper;


    /**
     * 处理下单超过15分钟不支付的订单
     */
    @Scheduled(cron = "0 * * * * *")
    public void processTimeoutOrders(){
        log.info("Timed processing of timeout orders: {}", LocalDateTime.now());

        LocalDateTime time = LocalDateTime.now().plusMinutes(-15);
        List<Orders> ordersList = orderMapper.getByStatusAndOrderTimeLT(Orders.PENDING_PAYMENT, time);
        if (ordersList != null && ordersList.size() > 0){
            for (Orders orders : ordersList) {
                orders.setStatus(Orders.CANCELLED);
                orders.setCancelReason("Order timeout, cancelled automatically");
                orders.setCancelTime(LocalDateTime.now());
                orderMapper.update(orders);
            }
        }
    }

    /**
     * 处理一直处于派送中的订单
     */
    @Scheduled(cron = "0 0 1 * * *")
    public void processDeliveryOrder(){
        log.info("deal with on delivery order every day: {}", LocalDateTime.now());
        LocalDateTime time = LocalDateTime.now().minusHours(1);
        List<Orders> ordersList = orderMapper.getByStatusAndOrderTimeLT(Orders.DELIVERY_IN_PROGRESS, time);
        if (ordersList != null && ordersList.size() > 0){
            for (Orders orders : ordersList) {
                orders.setStatus(Orders.COMPLETED);
                orders.setCancelReason("Order timeout, cancelled automatically");
                orders.setCancelTime(LocalDateTime.now());
                orderMapper.update(orders);
            }
        }

    }
}
