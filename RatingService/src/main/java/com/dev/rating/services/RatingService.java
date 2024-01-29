package com.dev.rating.services;

import java.util.List;

import com.dev.rating.entities.Rating;

public interface RatingService {

	//create user
	Rating create(Rating rating);
	
	//get all ratings
	List<Rating> getRatings();
	
	List<Rating> getRatingByUserId(String userId);
	
	List<Rating> getRatingByHotelId(String hotelId);
}
