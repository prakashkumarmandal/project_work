package com.cts.pss.controller;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;

@Controller

public class Mailsender {

	@Autowired
	public JavaMailSender javaMailSender;


	@Bean
	public Queue q3() {
		return new Queue("EmailSend",false);
	}
	
	
	@RabbitListener(queues = {"EmailSend"}) 
	public void processMessage(Map<String,Object> messages) {
			
	          System.out.println(" ---> this is from mail --> " +messages);
	          try { 
	        	
	        	     SimpleMailMessage msg = new SimpleMailMessage();
	        	  
	        	     String mail=(String)messages.get("MAIL");
	  	             Integer bookingId=(Integer)messages.get("BOOKING_ID");
	  	             String flightNumber=(String)messages.get("FLIGHT_NUMBER");
	  	             LocalDate flightDate=(LocalDate)messages.get("DATE");
	  	             String passengerName=(String) messages.get("NAME");
	  	             LocalTime time=(LocalTime) messages.get("TIME");
	  	       
	                 msg.setTo(mail);
	  	             msg.setSubject("Flight Booking confirmation *******");
	  	             msg.setText(" *****  Dear "+
	  	                                         passengerName+" You Flight Number "+
	  	                                         flightNumber+" is confirmed on "+
	  	            		                     flightDate +" at "+
	  	                                         time +" and your booking ID is "+
	  	            		                     bookingId + 
	  	            		                                               "  ********");
	  	        
	  	             javaMailSender.send(msg);
	  	        
			} catch (Exception e) {
				
				SimpleMailMessage msg = new SimpleMailMessage();
				msg.setTo("prakashkalu2012@gmail.com");
	  	        msg.setSubject("Flight Booking confirmation *******");
	  	        msg.setText(" ***** *****  flight booking error  *****  *********" + e);
				
				javaMailSender.send(msg);
				
				System.out.println(e);
			}
	      
	       
	        
	        
			
	    }	
	
//	public String mailSend() {
//		
//		SimpleMailMessage massage = new SimpleMailMessage();
//		
//		massage.setFrom("prakashkalu2012@gmail.com");
//		massage.setTo("prakashkalu2012@gmail.com");
//		massage.setText("my mail going");
//		massage.setSubject("flit tiket");
//		
//		//mailSender.send(massage);
//		return "mail sending";
//		
//	}
	

}
