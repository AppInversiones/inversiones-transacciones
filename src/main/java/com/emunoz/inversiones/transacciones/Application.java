package com.emunoz.inversiones.transacciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EntityScan("com.emunoz.inversiones.transacciones")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
