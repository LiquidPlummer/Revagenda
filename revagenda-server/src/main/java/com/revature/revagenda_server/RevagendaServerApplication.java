package com.revature.revagenda_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.revature.revagenda_server.controllers",
        "com.revature.revagenda_server.repositories",
        "com.revature.revagenda_server.services",
        "com.revature.revagenda_server.configs"
})
public class RevagendaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RevagendaServerApplication.class, args);
	}

}
