package com.cts.pss.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

@Controller
public class Sender {
	
	@Autowired
	private RabbitMessagingTemplate rt;
	
	@Bean
	public Queue q1() {
		return new Queue("TestQ1",false);
	}
	
	@Bean
	public Queue q2() {
		return new Queue("TestQ2",false);
	}
	
	
	
	
	@Bean
	public void sendData() { // custom method name
		
		Map<String, Object> messages=new HashMap<>();
		
		messages.put("bookingId", "Air20200954");
		messages.put("bookingFlight", "AirAsia");
		messages.put("bookedDate", LocalDate.now());
		messages.put("flightId", 556677);
		messages.put("amount", 865534.4565);
		
		
		rt.convertAndSend("TestQ2",messages);
		
		//rt.convertAndSend("TestQ1","My First JMS Application - V8");
		//rt.convertAndSend("TestQ1","My First JMS Application - V9");
		//rt.convertAndSend("TestQ1","My First JMS Application - V10");
		//rt.convertAndSend("TestQ1","My First JMS Application - V11");



	}
	
	

}
