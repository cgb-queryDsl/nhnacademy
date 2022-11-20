package com.nhnmart.cs.config;

import com.nhnmart.cs.Base;
import com.nhnmart.cs.repository.CustomerRepository;
import com.nhnmart.cs.repository.CustomerRepositoryImpl;
import com.nhnmart.cs.repository.PostRepository;
import com.nhnmart.cs.repository.PostRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Slf4j
@Configuration
@ComponentScan(basePackageClasses = Base.class,
    excludeFilters = {@ComponentScan.Filter(Controller.class) }
)
public class RootConfig {

    @Bean
    public PostRepository postRepository() {
        PostRepository postRepository = new PostRepositoryImpl();
        return postRepository;
    }

    @Bean
    public CustomerRepository customerRepository() {
        CustomerRepository customerRepository = new CustomerRepositoryImpl();
        customerRepository.register("admin", "12345", "CS 관리자");
        customerRepository.register("customer1", "123", "고객 1");
        customerRepository.register("customer2", "123", "고객 2");

        return customerRepository;
    }
}
