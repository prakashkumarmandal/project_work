package com.cts.pss.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.pss.entity.Fare;
import com.cts.pss.service.FareService;

@RestController
@RequestMapping("/api/fare")
@CrossOrigin
public class FareRestController {

	@Autowired
	private FareService fareService;

	@GetMapping("/{flightNumber}/{origin}/{destination}/{flightDate}")
	public ResponseEntity<Object> findFare(@PathVariable String flightNumber, @PathVariable String origin,
			@PathVariable String destination, @PathVariable @DateTimeFormat(iso = ISO.DATE) LocalDate flightDate) {

		Fare fare = fareService.findByFlightNumberAndOriginAndDestinationAndFlightDate(flightNumber,origin, destination, flightDate);
		if (fare == null) {
			return new ResponseEntity<>(HttpStatus.PARTIAL_CONTENT);
		}
		return new ResponseEntity<>(fare, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getFare(@PathVariable int id) {

		Fare fare = fareService.getFare(id);
		if (fare == null) {
			return new ResponseEntity<>(HttpStatus.PARTIAL_CONTENT);
		}
		return new ResponseEntity<>(fare, HttpStatus.OK);
	}

}
