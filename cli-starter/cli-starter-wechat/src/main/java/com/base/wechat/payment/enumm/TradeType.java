package com.base.wechat.payment.enumm;

/**
 * 微信支付统一下单交易类型枚举
 * @author hzh 2018/8/6 下午10:44
 */
public enum TradeType {

	/**
	 * 公众号支付
	 */
	JSAPI,
	/**
	 * 原生扫描支付
	 */
	NATIVE,
	/**
	 * APP支付
	 */
	APP,
	/**
	 * 刷卡支付
	 */
	MICROPAY;
}
