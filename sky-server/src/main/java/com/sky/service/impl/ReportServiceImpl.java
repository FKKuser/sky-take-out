package com.sky.service.impl;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import com.sky.mapper.ReportMapper;
import com.sky.service.ReportService;
import com.sky.vo.TurnoverReportVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;
    @Autowired
    private OrderMapper orderMapper;

    public ReportServiceImpl(OrderMapper orderMapper) {
    }

    @Override
    public TurnoverReportVO getTurnoverStatistics(LocalDate begin, LocalDate end) {
        // the list stores every day from begin date to end date
        List<LocalDate> dateList = new ArrayList<>();
        while(!begin.equals(end)){
            dateList.add(begin);
            begin = begin.plusDays(1);
        }
        dateList.add(end);

        List<Double> turnoverList = new ArrayList<>();

        for (LocalDate date : dateList) {
            // select sum(amount) from orders where order_time > date1 and order_time < date2 and status = 5;
            LocalDateTime beginTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date, LocalTime.MAX);

            Map map = new HashMap();
            map.put("begin", beginTime);
            map.put("end", endTime);
            map.put("status", Orders.COMPLETED);
            Double turnover = orderMapper.sumByMap(map);
            turnover = turnover == null ? 0.0 : turnover;
            turnoverList.add(turnover);
        }

        String dlist = StringUtils.join(dateList, ",");
        String tList = StringUtils.join(turnoverList, ",");
        return TurnoverReportVO.builder()
                        .dateList(dlist)
                        .turnoverList(tList)
                        .build();




    }
}
