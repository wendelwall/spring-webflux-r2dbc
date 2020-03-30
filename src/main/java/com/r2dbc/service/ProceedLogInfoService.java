package com.r2dbc.service;

import com.r2dbc.entity.ProceedLogInfo;
import com.r2dbc.model.vo.LogProcessdVo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author ：sunrise
 * @description ：
 * @copyright ：	Copyright 2020 yowits Corporation. All rights reserved.
 * @create ：2020/3/24 21:29
 */
public interface ProceedLogInfoService {
    /**
     * 保存日志
     * @param logProcessdVo
     * @return
     */
    Mono<Void> save(LogProcessdVo logProcessdVo);

    /**
     * 根据订单号查询日志
     * @param orderId
     * @return
     */
    Flux<ProceedLogInfo> findByOrderId(String orderId);
}
