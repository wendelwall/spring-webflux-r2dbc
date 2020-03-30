package com.r2dbc.repository;

import com.r2dbc.entity.ProceedLogInfo;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ProceedLogInfoRepository extends ReactiveCrudRepository<ProceedLogInfo, String> {

    /**
     * 根据订单编号查询日志列表
     * @param orderId
     * @return
     */
    @Query("SELECT * FROM t_insured_proc_log WHERE ORDER_ID = :orderId")
    Flux<ProceedLogInfo> findByOrderId(String orderId);
}
