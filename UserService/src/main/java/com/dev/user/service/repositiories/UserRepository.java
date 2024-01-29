package com.dev.user.service.repositiories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.user.service.entities.User;

public interface UserRepository extends JpaRepository<User, String>{

}
