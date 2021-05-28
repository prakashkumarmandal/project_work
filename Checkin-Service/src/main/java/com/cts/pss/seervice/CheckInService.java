package com.cts.pss.seervice;

import com.cts.pss.entity.CheckIn;

public interface CheckInService {

	CheckIn checkIn(int bookingId);

	// get Check in Info by Check-in ID
	CheckIn getCheeckinData(int checkinId);

	// get Check-in data by Booking ID
	CheckIn getCheckindataByBookingId(int bookingId);

}