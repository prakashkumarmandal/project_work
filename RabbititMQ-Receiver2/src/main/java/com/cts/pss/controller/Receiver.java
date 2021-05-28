package com.cts.pss.controller;

import java.time.LocalDate;
import java.util.Map;

import org.apache.tomcat.jni.Local;
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
	
	@RabbitListener(queues = "TestQ2")
	public void getBookingInfo(Map<String,Object> bookingData) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("Booking is Confirmed. Details");
		System.out.println("---------------------------------");
		System.out.println("BookingID: "+bookingData.get("bookingId"));
		System.out.println("Flight: "+bookingData.get("bookingFlight"));
		System.out.println("Date of Booking: "+bookingData.get("bookedDate"));
		System.out.println("Flight ID: "+bookingData.get("flightId"));
		System.out.println("Total Amount: "+bookingData.get("amount"));
		
		double amount=(double) bookingData.get("amount");
		LocalDate date=(LocalDate) bookingData.get("bookedDate");
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>");

		
		

	}

	
	
}
