package com.cts.pss.service;

import java.time.LocalDate;
import java.util.List;

import com.cts.pss.entity.Flight;
import com.cts.pss.model.SearchQuery;

public interface SearchService {

	List<Flight> findFlights(SearchQuery query);

	void updateInventory(int id, int seats_booked);
	public Flight findByFlightNumberAndOriginAndDestinationAndFlightDate(String flightNumber,String origin,String destination,LocalDate flightDate);
	public Flight findById(int id);

}