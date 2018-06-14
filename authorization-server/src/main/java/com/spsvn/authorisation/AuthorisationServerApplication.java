package com.spsvn.authorisation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@SpringBootApplication
@EnableAuthorizationServer
public class AuthorisationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorisationServerApplication.class, args);
	}
}
