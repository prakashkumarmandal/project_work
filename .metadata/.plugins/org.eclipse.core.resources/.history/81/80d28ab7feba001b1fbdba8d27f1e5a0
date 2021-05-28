package com.cts.pss.controller;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import com.cts.pss.service.BookingService;

@Controller
public class Receiver {

	@Autowired
	private BookingService bookingService;
	
	@Bean
	public Queue q1() {
		return new Queue("CheckinQ",false);
	}
	
	
	// get BookingID from Checkin-Service and update Booking status
	@RabbitListener(queues = {"CheckinQ"})
	public void updateBookingStatus(int bookingId) {
		
		bookingService.updateBookingStatus("CHECKED-IN", bookingId);
		
		
	}
}
