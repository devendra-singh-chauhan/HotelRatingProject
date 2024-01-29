package com.dev.user.service.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dev.user.service.entities.Hotel;
import com.dev.user.service.entities.Rating;
import com.dev.user.service.entities.User;
import com.dev.user.service.exceptions.ResourceNotFoundException;
import com.dev.user.service.external.services.HotelService;
import com.dev.user.service.repositiories.UserRepository;
import com.dev.user.service.services.UserService;

import ch.qos.logback.classic.Logger;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	private Logger logger= (Logger) LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User saveUser(User user) {
		//generate unique userId
		String randomUserId= UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		
		// get user from database with the help of user repository
		User user=  userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given id not found :"+ userId));
		
		//fetch rating of above user from rating service
		//http://localhost:8083/ratings/users/3860255f-67fb-46b0-8b8e-a3cccba08587
		
		Rating[] ratingsOfUser= restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
		logger.info("{}", ratingsOfUser);
		
		List<Rating> ratings= Arrays.stream(ratingsOfUser).toList();
		
		
		List<Rating> ratingList= ratings.stream().map(rating->{
			
			//api call to hotel service to get hotel
			//ResponseEntity<Hotel> forEntity= restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(),Hotel.class);
			//Hotel hotel = forEntity.getBody();
			
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			
			//logger.info("response status code: {}", forEntity.getStatusCode());
			
			//set hotel rating
			rating.setHotel(hotel);
			//return rating
			return rating;
		}).collect(Collectors.toList());
		
		user.setRatings(ratingList);
		
		return user;
	}

}
