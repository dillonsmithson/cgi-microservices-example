package com.cgi.ectp.orch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = "com.cgi")
@EnableFeignClients("com.cgi.glk.ectp.common.client")
@PropertySource(value = {"application.properties", "ectpcommon.properties"})
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
