package com.r2dbc.controller;

import com.r2dbc.entity.ProceedLogInfo;
import com.r2dbc.model.vo.LogProcessdVo;
import com.r2dbc.service.ProceedLogInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author ：sunrise
 * @description ：
 * @copyright ：	Copyright 2020 yowits Corporation. All rights reserved.
 * @create ：2020/3/28 14:29
 */
@RestController
@RequestMapping(value = "/channel/log")
public class ProceedLogInfoController {

    @Autowired
    ProceedLogInfoService proceedLogInfoService;

    /**
     * 根据订单号查询日志
     * @param orderId
     * @return
     */
    @GetMapping(value = "/{orderId}")
    public Flux<ProceedLogInfo> list(@PathVariable("orderId") String orderId){
        return proceedLogInfoService.findByOrderId(orderId);
    }

    /**
     * 根据订单号查询日志
     * @param vo
     * @return
     */
    @PostMapping
    public Mono<Void> create(@RequestBody LogProcessdVo vo){
        return proceedLogInfoService.save(vo);
    }
}
