package com.microservice.application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.microservice.application.model.User;
import com.microservice.application.repository.IUserRepository;


@SpringBootApplication
public class SpringbootWebJpaH2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWebJpaH2Application.class, args);
	}
	
	@Bean
	public CommandLineRunner setup(IUserRepository userRepository) 
	{
		return (args) -> {
			userRepository.save(new User("Josh","Brolin","2/12/1968","ACTIVE"));
			userRepository.save(new User("Chris","Evans","6/13/1981","ACTIVE"));
			userRepository.save(new User("Mark","Ruffalo","11/2/1967","ACTIVE"));
		};
	}

}

