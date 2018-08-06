package com.base.wechat;

import com.base.common.enumm.Env;
import com.base.wechat.payment.WeChatPublicPayClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信公众号自动配置类
 * @author hzh 2018/8/6 下午10:23
 */
@Configuration
@EnableConfigurationProperties(WeChatProperties.class)
public class WeChatConfiguration {

	private static Logger logger = LoggerFactory.getLogger(WeChatConfiguration.class);

	@Value("${spring.profiles.active:dev}")
	private String springProfilesActive;
	@Autowired
	private WeChatProperties weChatProperties;

	@Bean
	@ConditionalOnProperty(prefix = "cli.wx.public", name = {"appId", "mchId", "apiSecret"})
	public WeChatPublicPayClient weChatPublicPayClient() {
		/*******************************************************/
		logger.info("初始化微信公众号支付客户端, 支付环境: {}, appId: {}, 商户号: {}", springProfilesActive, weChatProperties.getAppId(), weChatProperties.getMchId());
		/*******************************************************/
		return new WeChatPublicPayClient(Env.valueOf(springProfilesActive.toUpperCase()), weChatProperties.getAppId(), weChatProperties.getMchId(),
				weChatProperties.getApiSecret(), weChatProperties.getPayNotifyUrl(), weChatProperties.getRefundNotifyUrl(), weChatProperties.getKeyStorePath());
	}
}
