package com.pragma.square;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;



@SpringBootApplication
@EnableFeignClients
public class SquareApplication {

	public static void main(String[] args) {
		SpringApplication.run(SquareApplication.class, args);

	}
}
