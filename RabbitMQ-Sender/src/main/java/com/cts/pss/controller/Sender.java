package com.cts.pss.controller;

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
		return new Queue("Email",false);
	}
	

	public void sendUserData() { // custom method name
		
		Map<String, Object> messages=new HashMap<>();
		
		messages.put("MAIL", "prakashkalu2012@gmail.com");
		messages.put("BOOKING_ID", "1234565");
		messages.put("FLIGHT_NUMBER", "Abcd1235");
		messages.put("DATE", "20/20/32");
		messages.put("NAME", "Prakash");
		messages.put("TIME", "10:50");
		
		System.out.println(messages);
		try {
			rt.convertAndSend("Email",messages);
		} catch (Exception e) {
			System.out.println();
		}
		
		
	}
	
	

}

