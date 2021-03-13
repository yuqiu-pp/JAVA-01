package com.spring.sphere.jdbc.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

@Data
public class OrderInfo  {

    // private static final long serialVersionUID = 1L;

    private Long id;
    private Long goodsId;
    private BigDecimal goodsPrice;
    private Integer goodsNum;
    private Integer orderStatus;
    private Date createTime;
    private Date updateTime;
    private Long userId;
    private Long userAddressId;
    private Long deliverId;
    private Integer totalAmt;
    private Long itemId;

    public OrderInfo(Long goodsId) {
        this.goodsId = goodsId;
    }
}
