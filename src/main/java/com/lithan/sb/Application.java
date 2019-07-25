package com.lithan.sb;

import java.text.MessageFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	
	private static final Log LOG = LogFactory.getLog(Application.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		String user;
		if(args.length>0) {
			user = args[0];
		}else {
			user = "Spring Boot";
		}
		LOG.info(MessageFormat.format("============= Hello {0} world! =============", user));
	}
	
}
