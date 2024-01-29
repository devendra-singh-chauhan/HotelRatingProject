package com.dev.rating.serviceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.rating.entities.Rating;
import com.dev.rating.exceptions.ResourceNotFoundException;
import com.dev.rating.repositiories.RatingRepository;
import com.dev.rating.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService{

	@Autowired
	private RatingRepository ratingRepository;
	
	@Override
	public Rating create(Rating rating) {
		//generate unique hotelId
		String ratingId= UUID.randomUUID().toString();
		rating.setRatingId(ratingId);
		return ratingRepository.save(rating);
	}
	
	@Override
	public List<Rating> getRatings() {
		// TODO Auto-generated method stub
		return ratingRepository.findAll();
	}


	@Override
	public List<Rating> getRatingByUserId(String userId) {
		// TODO Auto-generated method stub
		return ratingRepository.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
		// TODO Auto-generated method stub
		return ratingRepository.findByHotelId(hotelId);
	}

}
