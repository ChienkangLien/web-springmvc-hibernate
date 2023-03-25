package com.tibame.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class WebSpringmvcHibernateApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebSpringmvcHibernateApplication.class, args);
	}

}
