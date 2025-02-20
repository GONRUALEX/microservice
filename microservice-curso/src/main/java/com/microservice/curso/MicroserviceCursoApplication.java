package com.microservice.curso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = "com.microservice.curso")
public class MicroserviceCursoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceCursoApplication.class, args);
	}

}
