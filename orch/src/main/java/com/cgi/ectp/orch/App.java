package com.cgi.ectp.orch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class App {

	public static void main(final String[] pArgs) {
		SpringApplication.run(App.class, pArgs);
	}

}