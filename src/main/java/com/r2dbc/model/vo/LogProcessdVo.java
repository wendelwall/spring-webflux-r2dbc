package com.r2dbc.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * @author ：wendel
 * @description ：
 * @copyright ：	Copyright 2020 riskeys Corporation. All rights reserved.
 * @create ：2020/3/24 14:27
 */
@Data
public class LogProcessdVo {
    /**
     * 业务订单号
     */
    private String orderId;

    /**
     * 请求编码
     */
    private String code;

    /**
     * 编码名称
     */
    private String codeName;

    /**
     * 请求报文
     */
    private String req;
    /**
     * 响应报文
     */
    private String resp;

    /**
     * 执行目标方法所需时间（单位：毫秒）
     */
    private long elapsedTime;

    /**
     * 操作时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime currTime;

    /**
     * 执行结果 0-失败，1-成功
     */
    private String result;

}
