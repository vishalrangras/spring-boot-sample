package com.lithan.sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {
	
	@RequestMapping("/")
	String home(@RequestParam(required = false) String name) {
		if(name!=null) {
			return "Hello "+name+" World!";
		}else {
			return "Hello World!";
		}
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);	
	}
	
}