package no.difi.dc2017.idporteneventapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@SpringBootApplication
@EnableResourceServer

public class IdporteneventapiApplication {
	public static void main(String[] args){
		SpringApplication.run(IdporteneventapiApplication.class, args);
	}
}
