package com.github.iamhi.hizone.blog.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication(scanBasePackages = {"com.github.iamhi.hizone.blog"})
@EnableReactiveMongoRepositories("com.github.iamhi.hizone.blog.data")
@ConfigurationPropertiesScan("com.github.iamhi.hizone.blog.config")
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

}
