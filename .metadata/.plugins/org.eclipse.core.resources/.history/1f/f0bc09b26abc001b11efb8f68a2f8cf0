package com.cts.pss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.pss.entity.CheckIn;
import com.cts.pss.seervice.CheckInService;

@RestController
@RequestMapping("/api/checkin")
@CrossOrigin
public class CheckinRestController {
	
	@Autowired
	private CheckInService checkInService;

	@PostMapping("/{bookingId}")
	public CheckIn checkin(@PathVariable int bookingId) {
		return checkInService.checkIn(bookingId);
	}

	@GetMapping("/{bookingId}")
	public CheckIn getByBookingId(@PathVariable int bookingId) {
		return checkInService.getCheckindataByBookingId(bookingId);
	}

	@PostMapping("/byCheckinId/{checkinId}")
	public CheckIn getByCheckinId(@PathVariable int checkinId) {
		return checkInService.getCheeckinData(checkinId);
	}

}
