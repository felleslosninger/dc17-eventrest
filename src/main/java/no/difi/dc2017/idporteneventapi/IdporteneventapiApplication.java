package no.difi.dc2017.idporteneventapi;
import log.EventRestLogger;
/*
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@SpringBootApplication
@EnableResourceServer*/

public class IdporteneventapiApplication {
	public static void main(String[] args){
		EventRestLogger erl = new EventRestLogger();
		erl.loggToConsole('i', "This is information");
		/*erl.loggToConsole('d', "This is debug info");
		erl.loggToConsole('e', "This is an error");
		erl.loggToConsole('w', "This is a warning");
		erl.loggToConsole('a', "lala");*/
		//SpringApplication.run(IdporteneventapiApplication.class, args);
	}
}
