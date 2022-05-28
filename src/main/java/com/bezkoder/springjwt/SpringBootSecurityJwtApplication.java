package com.bezkoder.springjwt;

import com.bezkoder.springjwt.security.services.FileImplService;
import com.bezkoder.springjwt.security.services.RoomFileImplService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class SpringBootSecurityJwtApplication implements CommandLineRunner {

	@Resource
	FileImplService fileImplService ;
	@Resource
	RoomFileImplService roomFileImplService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityJwtApplication.class, args);
	}

	@Override
	public void run(String... arg) throws Exception {
		fileImplService.init();
		roomFileImplService.init();
	}

}
