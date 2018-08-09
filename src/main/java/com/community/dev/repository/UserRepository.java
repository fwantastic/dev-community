package com.community.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.community.dev.persistence.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserId(Long userId);

	User findByUserEmailAndIsActiveTrue(String userEmail);

}
