package com.carloschacon.ApiAhorcadoIN5BM;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiAhorcadoIn5BmApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApiAhorcadoIn5BmApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Api funcionando");
    }
}
