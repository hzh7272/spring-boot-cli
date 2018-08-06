package com.base.wechat;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 微信公众号属性
 * @author hzh 2018/8/6 15:07
 */
@Data
@ConfigurationProperties(prefix = "cli.wx.public")
public class WeChatProperties {

    /**
     * 微信公众号appId
     */
    private String appId;
    /**
     * 微信公众号APP密钥
     */
    private String appSecret;
    /**
     * 微信公众号支付商户号
     */
    private String mchId;
    /**
     * 微信公众号支付API密钥
     */
    private String apiSecret;
    /**
     * 微信支付API证书路径
     */
    private String keyStorePath;
    /**
     * 微信支付通知接口地址
     */
    private String payNotifyUrl;
    /**
     * 微信支付退款通知地址
     */
    private String refundNotifyUrl;
}
