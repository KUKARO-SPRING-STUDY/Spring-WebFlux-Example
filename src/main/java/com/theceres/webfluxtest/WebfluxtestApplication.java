package com.theceres.webfluxtest;

import com.theceres.webfluxtest.reativetest.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebfluxtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxtestApplication.class, args);
		FluxTest145.run();
	}

}
