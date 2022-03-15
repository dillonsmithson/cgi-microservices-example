package com.cgi.glk.ectp.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.cgi")
@EnableFeignClients("com.cgi.glk.ectp.common.client")
@PropertySource(value = {"application.properties", "ectpcommon.properties"})
@EnableJpaRepositories
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
