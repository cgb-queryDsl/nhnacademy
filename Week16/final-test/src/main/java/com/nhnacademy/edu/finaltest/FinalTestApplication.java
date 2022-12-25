package com.nhnacademy.edu.finaltest;

import com.nhnacademy.edu.finaltest.config.SystemProperties;
import com.nhnacademy.edu.finaltest.service.InitService;
import com.nhnacademy.edu.finaltest.service.impl.DataSaverImpl;
import com.nhnacademy.edu.finaltest.service.impl.FileParserImpl;
import com.nhnacademy.edu.finaltest.service.impl.InitServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
@ConfigurationPropertiesScan
public class FinalTestApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(FinalTestApplication.class, args);
		SystemProperties properties = context.getBean(SystemProperties.class);
		log.info("properties = {}", properties.getName());
	}

}
