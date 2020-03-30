package com.r2dbc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;


@Data
@Table("t_insured_proc_log")
@NoArgsConstructor
@ToString
public class ProceedLogInfo {

    /**
     * 全局唯一编号
     */
    @Id
    @Column("ID")
    private String id;

    /**
     * 订单编号
     */
    @Column("ORDER_ID")
    private String orderId;

    /**
     * 请求编码
     */
    @Column("CODE")
    private String code;

    /**
     * 编码名称
     */
    @Column("CODE_NAME")
    private String codeName;

    /**
     * 请求报文
     */
    @Column("REQ")
    private String req;

    /**
     * 响应报文
     */
    @Column("RESP")
    private String resp;

    /**
     * 操作时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column("OP_TIME")
    private LocalDateTime opTime;

    /**
     * 执行目标方法所需时间（单位：毫秒）
     */
    @Column("ELAPSED_TIME")
    private Long elapsedTime;

    /**
     * 执行结果 0-失败，1-成功
     */
    @Column("RESULT")
    private String result;

    /**
     * 创建时间
     */
    @JsonIgnore
    @Column("INSERT_TIME")
    private LocalDateTime insertTime;
    /**
     * 创建人
     */
    @JsonIgnore
    @Column("INSERT_USER")
    private String insertUser;

    /**
     * 更新时间
     */
    @JsonIgnore
    @Column("UPDATE_TIME")
    private LocalDateTime updateTime;

    /**
     * 更新人
     */
    @JsonIgnore
    @Column("UPDATE_USER")
    private String updateUser;
    /**
     * 删除状态（0-未删除，1-已删除）
     */
    @JsonIgnore
    @Column("DELETE_FLAG")
    private Boolean deleteFlag;
}