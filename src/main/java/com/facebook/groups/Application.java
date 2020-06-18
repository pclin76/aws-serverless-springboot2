package com.facebook.groups;

import com.facebook.groups.controller.PingController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
// We use direct @Import instead of @ComponentScan to speed up cold starts
// @ComponentScan(basePackages = "com.facebook.groups.controller")
@Import({PingController.class})
public class Application {

	/**
	 * Silence console logging.
	 */
	@Value("${logging.level.root:OFF}")
	String message = "";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
