package com.base.wechat.payment.bean;

import com.base.wechat.payment.enumm.FeeType;
import com.base.wechat.payment.enumm.TradeType;
import lombok.Data;

/**
 * 微信支付统一下单数据
 * @author hzh 2018/8/6 下午10:38
 */
@Data
public class PayOrder {

	private String body;
	private String detail;
	private String deviceInfo;
	private String outTradeNo;
	private Integer totalFee;
	private TradeType tradeType;
	private FeeType feeType;
	private String clientIp;
	private String attach;
	private String spbillCreateIp;
	private String timeStart;
	private String timeExpired;
	private String goodsTag;
	private String productId;
	private Boolean limitPay;
}
