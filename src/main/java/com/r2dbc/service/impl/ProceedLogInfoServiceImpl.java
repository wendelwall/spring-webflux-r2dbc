package com.r2dbc.service.impl;

import com.r2dbc.entity.ProceedLogInfo;
import com.r2dbc.model.vo.LogProcessdVo;
import com.r2dbc.model.vo.UuidUtils;
import com.r2dbc.repository.ProceedLogInfoRepository;
import com.r2dbc.service.ProceedLogInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author ：sunrise
 * @description ：基于响应式入库
 * @copyright ：	Copyright 2020 yowits Corporation. All rights reserved.
 * @create ：2020/3/24 21:30
 */
@Service
public class ProceedLogInfoServiceImpl implements ProceedLogInfoService {

    @Autowired
    @Lazy
    ProceedLogInfoRepository proceedLogInfoRepository;
    @Autowired
    DatabaseClient databaseClient;

    /**
     * 保存日志
     * @param logProcessdVo
     * @return
     */
    @Override
    public Mono<Void> save(LogProcessdVo logProcessdVo) {
        ProceedLogInfo proceedLogInfo = new ProceedLogInfo();
        proceedLogInfo.setId(UuidUtils.base58Uuid());
        BeanUtils.copyProperties(logProcessdVo, proceedLogInfo);
        proceedLogInfo.setOpTime(logProcessdVo.getCurrTime());
        return databaseClient.insert().into(ProceedLogInfo.class).using(proceedLogInfo).then();
    }

    /**
     * 根据订单号查询日志
     * @param orderId
     * @return
     */
    @Override
    public Flux<ProceedLogInfo> findByOrderId(String orderId) {
        return proceedLogInfoRepository.findByOrderId(orderId);
    }
}
