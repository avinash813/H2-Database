package com.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.sample.dao.Dao;

@SpringBootApplication
public class Springjdbc2Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Springjdbc2Application.class, args);
		Dao obj = context.getBean(Dao.class);
		obj.update();
		System.out.println(obj.retrieve());
		
	}

}
