package com.trilogyed.bookservive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BookServiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookServiveApplication.class, args);
	}

}
