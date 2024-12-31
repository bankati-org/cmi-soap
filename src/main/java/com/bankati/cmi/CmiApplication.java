package com.bankati.cmi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CmiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmiApplication.class, args);
	}

}
