package com.cts.pss.service;

import java.time.LocalDate;

import com.cts.pss.entity.Fare;

public interface FareService {

	Fare getFare(int id);
	public Fare findByFlightNumberAndOriginAndDestinationAndFlightDate(String flightNumber,String origin,String destination,LocalDate flightDate);

}