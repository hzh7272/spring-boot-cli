package com.base.wechat.payment;

import com.base.common.utils.string.StringUtils;
import com.base.starter.bean.bo.PaymentInfo;
import com.base.wechat.payment.bean.PayOrder;
import com.base.wechat.payment.enumm.FeeType;
import weixin.popular.bean.paymch.SecapiPayRefundResult;
import weixin.popular.util.SignatureUtil;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 微信支付相关接口
 * @author hzh 2018/8/6 下午10:33
 */
public interface IWeChatPayClient {

	/**
	 * 签名校验
	 * @param params
	 * @param signType
	 * @param secret
	 * @return
	 */
	default boolean validateSign(Map<String, String> params, String signType, String secret) {
		Function<String, String> signTypeFunction = s -> StringUtils.isEmpty(s) ? "MD5" : s;
		Predicate<Map<String, String>> validate = map -> SignatureUtil.validateSign(params, signTypeFunction.apply(signType), secret);
		return validate.test(params);
	}

	/**
	 * 支付下单
	 * @param payOrder
	 * @return
	 */
	PaymentInfo doPay(PayOrder payOrder);

	/**
	 * 申请退款
	 * @param outTradeNo
	 * @param refundFeeType
	 * @param totalFee
	 * @param refundFee
	 * @return
	 */
	SecapiPayRefundResult doRefund(String outTradeNo, FeeType refundFeeType, Integer totalFee, Integer refundFee);
}
