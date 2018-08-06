package com.base.wechat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 微信公众号自动配置类
 * @author hzh 2018/8/6 下午10:23
 */
@Configuration
@EnableConfigurationProperties(WeChatProperties.class)
public class WeChatConfiguration {

	private static Logger logger = LoggerFactory.getLogger(WeChatConfiguration.class);

	@Autowired
	private WeChatProperties weChatProperties;

}
