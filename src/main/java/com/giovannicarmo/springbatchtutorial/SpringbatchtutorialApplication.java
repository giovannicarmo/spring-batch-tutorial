package com.giovannicarmo.springbatchtutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbatchtutorialApplication {

	public static void main(String[] args) throws Exception {
		System.exit(SpringApplication.exit(
				SpringApplication.run(SpringbatchtutorialApplication.class, args)));
	}
}
