package com.community.dev.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.community.dev.persistence.Role;
import com.community.dev.persistence.User;
import com.community.dev.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUserEmail(username);

		user.setPassword(encoder.encode(user.getPassword()));

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

		// TODO: remove this
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		for (Role role : user.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}

		return new org.springframework.security.core.userdetails.User(user.getUserEmail(), user.getPassword(),
				grantedAuthorities);

	}

}
