package com.api.mkdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MkDataApiApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MkDataApiApplication.class, args);
	}
}
