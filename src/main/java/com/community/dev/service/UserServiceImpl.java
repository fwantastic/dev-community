package com.community.dev.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.community.dev.persistence.User;
import com.community.dev.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User create(User user) {
		user.setActivityPoint(0L);
		user.setIsActive(true);
		// store email as uppercase
		user.setUserEmail(user.getUserEmail().toUpperCase());
		return save(user);
	}

	@Override
	public User save(User user) {
		// store email as uppercase
		user.setUserEmail(user.getUserEmail().toUpperCase());
		return userRepository.save(user);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findByUserName(String userEmail) {
		return userRepository.findByUserEmail(userEmail);
	}

	@Override
	public User findByUserId(Long userId) {
		// TODO Auto-generated method stub
		return userRepository.findByUserId(userId);
	}

	@Override
	public User findByUserEmail(String userEmail) {
		// TODO Auto-generated method stub
		return null;
	}
}
