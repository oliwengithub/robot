package com.oliwen.util.mail.conf;

import com.oliwen.util.mail.MailClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class MailConfig {

	/**
	 * 邮箱服务器
	 */
	@Value("${mail.hostname:smtp.163.com}")
    private String hostName;
	/**
	 * 邮箱端口
	 */
	@Value("${mail.port:994}")
    private String smtPort;
	/**
	 * 邮箱用户名
	 */
	@Value("${mail.username:13247819980@163.com}")
    private String smtUsername;
	/**
	 * 邮箱用户名
	 */
	@Value("${mail.password:13247819980hai}")
    private String smtPassword;
	/**
	 * 邮箱昵称
	 */
	@Value("${mail.name:江西瑞臻企业征信服务有限公司}")
    private String smtName;

	@Bean(name="mailClient")
	public MailClient mailClient(){
		MailClient client = new MailClient(hostName,smtPort,smtUsername,smtPassword,smtName);
		return client;
	}
}
