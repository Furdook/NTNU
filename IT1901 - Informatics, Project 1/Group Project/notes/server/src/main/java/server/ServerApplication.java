package server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

	/**
	 * Start the server.
	 * @param args
	 */
	public static void main(final String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

}
