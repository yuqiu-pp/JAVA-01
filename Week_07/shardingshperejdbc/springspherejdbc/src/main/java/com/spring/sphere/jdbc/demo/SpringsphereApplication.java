package com.spring.sphere.jdbc.demo;

import com.spring.sphere.jdbc.demo.controller.UserController;
import com.spring.sphere.jdbc.demo.entity.User;
import com.spring.sphere.jdbc.demo.service.UserService;
import com.spring.sphere.jdbc.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringsphereApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringsphereApplication.class, args);

    }

}
