package com.gcu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.gcu"})
@SpringBootApplication
public class Act1heroku {

	public static void main(String[] args) {
		SpringApplication.run(Act1heroku.class, args);
	}

}
