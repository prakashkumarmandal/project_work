package com.cts.pss.controller;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

@Controller
public class Sender {

	@Autowired
	private RabbitMessagingTemplate rmt;

	@Bean
	public Queue q1() {
		return new Queue("CheckinQ", false);
	}

	
	public void sendStatus(Object bookingId) {
		System.out.println(">>>>> CHECKIN-SERVICE <<<<");
		System.out.println(">>>>>>Sending Checkin Status to Booking Service<<<<<");
		rmt.convertAndSend("CheckinQ", bookingId);
		System.out.println(">>>>> Status Sent <<<<");
	}

}
