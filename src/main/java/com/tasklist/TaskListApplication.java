package com.tasklist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages= {
		"com.tasklist.controller",
		"com.tasklist.model",
		"com.tasklist.config",
		"com.tasklist.repository",
		"com.tasklist.services"
})
public class TaskListApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskListApplication.class, args);
	}
	

}
