package com.vitalx.apizoo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Optional;

@SpringBootApplication
@EnableSwagger2
public class ApiZooApplication {

	private static final Logger LOG = LoggerFactory.getLogger(ApiZooApplication.class);
	private static final String HTTP_DEFAULT_PORT = "8080";


	public static void main(String[] args) {
		final Environment env = SpringApplication.run(ApiZooApplication.class, args).getEnvironment();
		logApplicationStartup(env);
	}

	private static void logApplicationStartup(final Environment env) {
		String protocol = "http";
		if (env.getProperty("server.ssl.key-store") != null) {
			protocol = "https";
		}
		final String serverPort = Optional.ofNullable(env.getProperty("server.port")).orElse(HTTP_DEFAULT_PORT);
		String contextPath = env.getProperty("application.path");

		LOG.info("\n----------------------------------------------------------\n\t" //
						+ "Application '{}' is running!\n\tAccess URLs:\n\t" //
						+ "Local: \t\t{}://localhost:{}{}\n\t" //
						+ "\n----------------------------------------------------------", //
				env.getProperty("spring.application.name"), //
				protocol, serverPort, contextPath);
	}

}
