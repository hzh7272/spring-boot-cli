package com.base.wechat.payment;

import com.base.common.enumm.Env;
import com.base.starter.bean.bo.PaymentInfo;
import com.base.wechat.payment.bean.PayOrder;
import com.base.wechat.payment.enumm.FeeType;
import weixin.popular.bean.paymch.SecapiPayRefundResult;

/**
 * 微信公众号支付客户端
 * @author hzh 2018/8/6 下午11:11
 */
public class WeChatPublicPayClient implements IWeChatPayClient {

	private Env env;
	private String appId;
	private String mchId;
	private String apiSecret;
	private String payNotifyUrl;
	private String refundNotifyUrl;
	private String keyStorePath;

	/**
	 * 微信公众号支付客户端构造方法
	 * @param env
	 * @param appId
	 * @param mchId
	 * @param apiSecret
	 * @param payNotifyUrl
	 * @param refundNotifyUrl
	 * @param keyStorePath
	 */
	public WeChatPublicPayClient(Env env, String appId, String mchId, String apiSecret, String payNotifyUrl, String refundNotifyUrl, String keyStorePath) {
		this.env = env;
		this.appId = appId;
		this.mchId = mchId;
		this.apiSecret = apiSecret;
		this.payNotifyUrl = payNotifyUrl;
		this.refundNotifyUrl = refundNotifyUrl;
		this.keyStorePath = keyStorePath;
	}

	/**
	 * 支付下单
	 * @param payOrder
	 * @return
	 */
	@Override
	public PaymentInfo doPay(PayOrder payOrder) {
		return null;
	}

	/**
	 * 申请退款
	 * @param outTradeNo
	 * @param refundFeeType
	 * @param totalFee
	 * @param refundFee
	 * @return
	 */
	@Override
	public SecapiPayRefundResult doRefund(String outTradeNo, FeeType refundFeeType, Integer totalFee, Integer refundFee) {
		return null;
	}
}
