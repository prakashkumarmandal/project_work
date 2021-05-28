package com.cts.pss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cts.pss.controller.Sender;


@SpringBootApplication
public class RammitMqSenderApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(RammitMqSenderApplication.class, args);
		
		Sender s = new Sender();
		s.sendUserData();
		
		System.out.println("application started----- sender -");
		
		
	}

}
