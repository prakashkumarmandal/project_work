package com.cts.pss.seervice;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cts.pss.controller.Sender;
import com.cts.pss.dao.CheckinDao;
import com.cts.pss.entity.BookingRecord;
import com.cts.pss.entity.CheckIn;

@Service
public class CheckInServiceImpl implements CheckInService {
	
	@Autowired
	private CheckinDao checkinDao;
	@Autowired
	private RestTemplate rt;
	
	@Autowired
	private Sender sender;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	
	private String bookingUrl="http://localhost:8083/api/book";
	
	
	@Override
	public CheckIn checkIn(int bookingId) {
		CheckIn checkin=null;
		
		BookingRecord bookingRecord = rt.getForObject(bookingUrl+"/"+bookingId, BookingRecord.class);
		
		if(bookingRecord!=null) {
			checkin=new CheckIn();
			checkin.setCheckinTime(LocalDateTime.now());
			checkin.setSeatNumber("B1");
			checkin.setBookingRecord(bookingRecord);
			
			checkinDao.save(checkin);
			// Send Check-in COnfirmation to N=Booking Service
			sender.sendStatus(bookingId);
		}

		return checkin;
	}
	
	
	// get Check in Info by Check-in ID
	@Override
	public CheckIn getCheeckinData(int checkinId) {
		return checkinDao.findById(checkinId).orElse(null);
	}
	
	// get Check-in data by Booking ID
	@Override
	public CheckIn getCheckindataByBookingId(int bookingId) {
		return checkinDao.findByBookingId(bookingId);
	}
	
	
	
	
	
	
}
