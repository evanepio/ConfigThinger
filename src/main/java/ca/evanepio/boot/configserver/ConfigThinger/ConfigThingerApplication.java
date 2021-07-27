package ca.evanepio.boot.configserver.ConfigThinger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigThingerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigThingerApplication.class, args);
	}

}
