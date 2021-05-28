package com.cts.pss.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cts.pss.controller.Sender;
import com.cts.pss.dao.BookingDao;
import com.cts.pss.dao.PassengerDao;
import com.cts.pss.entity.BookingRecord;
import com.cts.pss.entity.Fare;
import com.cts.pss.entity.Flight;
import com.cts.pss.model.SearchQuery;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingDao bookingDao;

	@Autowired
	private Sender sender;

	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	// URL of Fare Service

	private String fareServiceUrl = "http://localhost:8081/api/fare";
	private String searchServiceurl = "http://localhost:8082/api/search";

	@Override
	public BookingRecord bookFlight(SearchQuery query) {

		BookingRecord bookingRecord = null;

		Fare fare = restTemplate.getForObject(fareServiceUrl + "/" + query.getFlightNumber() + "/" + query.getOrigin()
				+ "/" + query.getDestination() + "/" + query.getFlightDate(), Fare.class);
		Flight flight = restTemplate.getForObject(searchServiceurl + "/" + query.getFlightNumber() + "/"
				+ query.getOrigin() + "/" + query.getDestination() + "/" + query.getFlightDate(), Flight.class);

		if (flight.getInventory().getCount() < query.getTravellers()) {
			System.out.println(">>>>>> No more Seats Avaiable for Book <<<<<<");
			return null;
		}

		if (flight != null) {
			bookingRecord = new BookingRecord(LocalDateTime.now(), flight.getFlightDate(), flight.getFlightTime(),
					flight.getFlightNumber(), flight.getOrigin(), flight.getDestination(), "CONFIRMED",
					query.getPassenger(), flight.getFlightInfo());

			bookingRecord.setFare(flight.getFare().getFare() * query.getTravellers());

			if (query.getPassenger().getCoPassengers().size() == query.getTravellers() - 1) {
				bookingDao.save(bookingRecord);
			} else {
				System.out.println(">>>>> Passenger count Wrongly Provided. Booking not Done...");
			}

		}

		// Send Booking Information to Search Service

		Map<String, Object> bookingDetails = new HashMap<>();
		bookingDetails.put("ID", flight.getId());
		bookingDetails.put("BOOKED_SEATS", query.getTravellers());

		sender.sendBookingInfo(bookingDetails);

		return bookingRecord;
	}

	@Override
	public BookingRecord getBookingData(int id) {
		return bookingDao.findById(id).orElse(null);
	}

	// update booking confirmation
	@Override
	public void updateBookingStatus(String status, int bookingId) {
		System.out.println(">>>>>>> Bookiing-Service <<<<<<<<<");
		System.out.println(">>>>>>> Changingv Confirmation status from CONFIRMED to " + status + "<<<<<<<");
		BookingRecord br = bookingDao.findById(bookingId).orElse(null);
		br.setStatus(status);
		bookingDao.save(br);
		System.out.println(">>>>>>> Booking Status is Updated <<<<<<<");
	}

}
