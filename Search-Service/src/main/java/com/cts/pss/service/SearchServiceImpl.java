package com.cts.pss.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.pss.dao.FlightDao;
import com.cts.pss.entity.Flight;
import com.cts.pss.entity.Inventory;
import com.cts.pss.model.SearchQuery;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private FlightDao flightDao;

	@Override
	public List<Flight> findFlights(SearchQuery query) {

		List<Flight> flights = flightDao.findByOriginAndDestinationAndFlightDate(query.getOrigin(),
				query.getDestination(), query.getFlightDate());

		// filter flights by verifying travellers
		return flights.stream().filter(flight -> flight.getInventory().getCount() >= query.getTravellers())
				.collect(Collectors.toList());
	}

	// get data from booking and update inventory accordingly

	@Override
	public void updateInventory(int id, int seats_booked) {

		Flight flight = flightDao.findById(id).orElse(null);

		Inventory inv = flight.getInventory();

		inv.setCount(inv.getCount() - seats_booked);

		flight.setInventory(inv);

		flightDao.save(flight);

	}

	// find a single flight for booking
	public Flight findByFlightNumberAndOriginAndDestinationAndFlightDate(String flightNumber, String origin,
			String destination, LocalDate flightDate) {
		return flightDao.findByFlightNumberAndOriginAndDestinationAndFlightDate(flightNumber, origin, destination,
				flightDate);
	}

	@Override
	public Flight findById(int id) {
		// TODO Auto-generated method stub
		return flightDao.findById(id).orElse(null);
	}

}
