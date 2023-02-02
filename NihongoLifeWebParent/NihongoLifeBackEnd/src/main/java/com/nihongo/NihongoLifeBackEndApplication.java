package com.nihongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.nihongo.common.entity"})
public class NihongoLifeBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(NihongoLifeBackEndApplication.class, args);
	}

}
