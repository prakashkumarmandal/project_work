package com.cts.pss.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class FlightInfo {
	@Id
	@GeneratedValue
	private int flightInfoid;
	private int seat_capacity;
	private String flightNumber;
	private String flightType;

	@ManyToOne
	@JoinColumn(name = "airlineId")
	private AirlineInfo airlineInfo;

	public int getFlightInfoid() {
		return flightInfoid;
	}

	public void setFlightInfoid(int flightInfoid) {
		this.flightInfoid = flightInfoid;
	}

	public int getSeat_capacity() {
		return seat_capacity;
	}

	public void setSeat_capacity(int seat_capacity) {
		this.seat_capacity = seat_capacity;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getFlightType() {
		return flightType;
	}

	public void setFlightType(String flightType) {
		this.flightType = flightType;
	}

	public AirlineInfo getAirlineInfo() {
		return airlineInfo;
	}

	public void setAirlineInfo(AirlineInfo airlineInfo) {
		this.airlineInfo = airlineInfo;
	}

}
