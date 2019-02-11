package com.microservice.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.application.model.User;


public interface IUserRepository extends JpaRepository<User, Long> {

}
