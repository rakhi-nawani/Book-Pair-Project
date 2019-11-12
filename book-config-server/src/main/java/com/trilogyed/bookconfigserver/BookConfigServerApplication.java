package com.trilogyed.bookconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class BookConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookConfigServerApplication.class, args);
	}

}
