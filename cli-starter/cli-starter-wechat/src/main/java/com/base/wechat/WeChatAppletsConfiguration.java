package com.base.wechat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 微信小程序自动配置类
 * @author hzh 2018/8/6 下午10:31
 */
@Configuration
@EnableConfigurationProperties(WeChatAppletsProperties.class)
public class WeChatAppletsConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(WeChatAppletsConfiguration.class);

	@Autowired
	private WeChatAppletsProperties weChatAppletsProperties;
}
