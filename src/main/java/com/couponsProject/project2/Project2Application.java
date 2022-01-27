package com.couponsProject.project2;

import com.couponsProject.project2.MyUtils.Art;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Project2Application {

	public static void main(String[] args) {
		SpringApplication.run(Project2Application.class, args);

		System.out.println(Art.localhost);

	}

}
