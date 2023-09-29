package com.teste.backend_attornatus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.teste.backend_attornatus.service")
@ComponentScan(basePackages = "com.teste.backend_attornatus.controller")
@ComponentScan(basePackages = "com.teste.backend_attornatus.model.repository")
@ComponentScan(basePackages = "com.teste.backend_attornatus.model.dao")
@ComponentScan(basePackages = "com.teste.backend_attornatus.model")
@ComponentScan(basePackages = "com.teste.backend_attornatus")
public class BackEndAttornatusApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackEndAttornatusApplication.class, args);
	}

}
