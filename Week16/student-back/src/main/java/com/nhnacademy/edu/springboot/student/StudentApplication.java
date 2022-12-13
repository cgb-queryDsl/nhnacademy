package com.nhnacademy.edu.springboot.student;

import com.nhnacademy.edu.springboot.student.config.SystemAuthorProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@EnableConfigurationProperties(SystemAuthorProperties.class)
@ConfigurationPropertiesScan	// 좀 더 편리한 방법
public class StudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}

}
