package com.cts.pss.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.pss.entity.Flight;
import com.cts.pss.model.SearchQuery;
import com.cts.pss.service.SearchService;

@RestController
@RequestMapping("/api/search")
@CrossOrigin
public class SearchRestController {

	@Autowired
	private SearchService searchService;

	@PostMapping
	public List<Flight> findFlights(@RequestBody SearchQuery query) {
		return searchService.findFlights(query);
	}

	@GetMapping("/{flightNumber}/{origin}/{destination}/{flightDate}")
	public Flight findFlight(@PathVariable String flightNumber, @PathVariable String origin,
			@PathVariable String destination, @PathVariable @DateTimeFormat(iso = ISO.DATE) LocalDate flightDate) {

		return searchService.findByFlightNumberAndOriginAndDestinationAndFlightDate(flightNumber, origin, destination,
				flightDate);

	}

	@GetMapping("/{id}")
	public Flight getFlight(@PathVariable int id) {
		return searchService.findById(id);
	}

}
