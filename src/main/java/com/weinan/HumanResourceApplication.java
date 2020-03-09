package com.weinan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.weinan.dao")
public class HumanResourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HumanResourceApplication.class, args);
	}

}
