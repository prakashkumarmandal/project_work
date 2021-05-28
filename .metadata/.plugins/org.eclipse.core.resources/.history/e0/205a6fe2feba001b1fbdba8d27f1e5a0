package com.cts.pss.controller;

import java.util.Map;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import com.cts.pss.service.SearchService;

@Controller
public class Receiver {
	
	@Autowired
	private SearchService searchService;
	
	@Bean
	public Queue q1() {
		return new Queue("InventoryQ",false);
	}
	
	
	@RabbitListener(queues = {"InventoryQ"})
	public void getInventoryDetails(Map<String, Object> bookingInfo) {
		
		System.out.println(">>>>>>>>>>>>>>> Upodating New Inventory <<<<<<<<<<<<<<");
		System.out.println(">>>>>> SEARCH-SERVICE <<<<<<<<<");
		System.out.println(">>>>>>> Flight ID: "+bookingInfo.get("ID"));
		System.out.println(">>>>>>> Number of Seats Booked: "+bookingInfo.get("BOOKED_SEATS"));
		System.out.println("--------------------------------------------------------------------------------");
	
		searchService.updateInventory((int)bookingInfo.get("ID"),(int) bookingInfo.get("BOOKED_SEATS"));
		
		
	}
	
	
	

}
