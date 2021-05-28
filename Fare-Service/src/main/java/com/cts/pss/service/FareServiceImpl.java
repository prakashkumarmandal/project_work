package com.cts.pss.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.pss.dao.FlightDao;
import com.cts.pss.entity.Fare;
import com.cts.pss.entity.Flight;

@Service
public class FareServiceImpl implements FareService {

	@Autowired
	private FlightDao flightDao;

	@Override
	public Fare getFare(int id) {
		
		Flight flight = flightDao.findById(id).orElse(null);
			
		if(flight==null) {
			return null;
		}
		
		return flight.getFare();
	}
	
	public Fare findByFlightNumberAndOriginAndDestinationAndFlightDate(String flightNumber,String origin,String destination,LocalDate flightDate) {
		return flightDao.findByFlightNumberAndOriginAndDestinationAndFlightDate(flightNumber,origin, destination, flightDate).getFare();
	}

}
