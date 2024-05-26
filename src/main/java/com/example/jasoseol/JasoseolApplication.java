package com.example.jasoseol;

import com.example.jasoseol.uploadfiles.storage.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class JasoseolApplication {

	public static void main(String[] args) {
		SpringApplication.run(JasoseolApplication.class, args);
	}

}
