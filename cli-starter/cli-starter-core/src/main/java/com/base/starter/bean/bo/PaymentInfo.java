package com.base.starter.bean.bo;

import lombok.Data;

/**
 * 支付信息
 * @author hzh 2018/8/6 16:12
 */
@Data
public class PaymentInfo {

    /**
     * 商品描述
     */
    private String body;
    /**
     * 商品详情
     */
    private String detail;
    /**
     * 商户订单号
     */
    private String outTradeNo;
    /**
     * 货币类型
     */
    private String feeType;
    /**
     * 支付金额
     */
    private Integer totalFee;
    /**
     * 支付回调地址
     */
    private String notifyUrl;
    /**
     * 签名方式
     */
    private String signType;
    /**
     * 签名
     */
    private String sign;
}
