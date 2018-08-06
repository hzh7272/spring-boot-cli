package com.base.wechat;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 微信小程序相关属性
 * @author hzh 2018/8/6 15:20
 */
@Data
@ConfigurationProperties(prefix = "cli.wx.applets")
public class WeChatAppletsProperties {

    /**
     * 微信小程序appId
     */
    private String appId;
    /**
     * 微信小程序APP密钥
     */
    private String appSecret;
    /**
     * 微信小程序支付商户号
     */
    private String mchId;
    /**
     * 微信小程序支付API密钥
     */
    private String apiSecret;
    /**
     * 微信小程序支付API证书路径
     */
    private String keyStorePath;
    /**
     * 微信小程序支付通知接口地址
     */
    private String payNotifyUrl;
    /**
     * 微信小程序支付退款通知地址
     */
    private String refundNotifyUrl;
}
