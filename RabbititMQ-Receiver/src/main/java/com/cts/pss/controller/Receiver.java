package com.cts.pss.controller;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

@Controller
public class Receiver {

	@Bean
	public Queue q1() {
		return new Queue("TestQ1",false);
	}
	
	@Bean
	public Queue q2() {
		return new Queue("TestQ2",false);
	}
	
	@RabbitListener(queues = "TestQ1")
	public void readMessage(String msg) {
		System.out.println(">>>>>>>>>>> <<<<<<<<<<<<<<");
		System.out.println(">>>> RECEIVER APP::: "+msg);
		System.out.println(">>>>>>>>>>> <<<<<<<<<<<<<<");

	}

	
	
}
