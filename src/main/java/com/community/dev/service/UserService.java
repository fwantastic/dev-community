package com.community.dev.service;

import java.util.List;

import com.community.dev.persistence.User;

public interface UserService {

	User create(User user);

	User save(User user);

	List<User> findAll();

	User findByUserId(Long userId);

	User findByUserEmail(String userEmail);

}