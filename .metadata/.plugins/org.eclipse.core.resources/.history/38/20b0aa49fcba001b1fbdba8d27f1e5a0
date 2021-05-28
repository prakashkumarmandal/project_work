package com.cts.pss.controller;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Sender {
	
	@Autowired
	private RabbitTemplate rt;
	
	
	public Queue q1() {
		return new Queue("InventoryQ",false);
	}
	
	
	public void sendBookingInfo(Object map) {
		System.out.println(">>> BOOKING-SEERVICE <<<");
		System.out.println(">>>> Sending Booking Details to Search Service <<<<<");
		rt.convertAndSend("InventoryQ",map);
		System.out.println(">>> Booking Details Sent to Search Service <<<");
	}

}
