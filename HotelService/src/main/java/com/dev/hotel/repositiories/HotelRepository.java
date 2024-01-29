package com.dev.hotel.repositiories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.hotel.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String>{

}
