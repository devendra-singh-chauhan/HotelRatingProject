package com.dev.hotel.services;

import java.util.List;

import com.dev.hotel.entities.Hotel;

public interface HotelService {

	//create user
	Hotel create(Hotel hotel);
	
	//get all user
	List<Hotel> getAll();
	
	//get single user
	Hotel get(String id);
	
	//delete
	
	//update
}
