package com.laptrinhjavaweb.service.impl;

import java.util.List;

import org.aspectj.weaver.patterns.IScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.NewEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	UserConverter userConverter;
	
	@Autowired
	UserRepository userRepository;
	

	
	@Override
	@Transactional
	public UserDTO save(UserDTO userDTO) {
		UserEntity userEntity = new UserEntity();
		// check the same username
		UserEntity entities =  userRepository.findByUserName(userDTO.getUserName().trim());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		userDTO.setStatus(1);
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		
		if(entities != null)
		{
			return null;
		}
		
		userEntity = userConverter.toEntity(userDTO);
		
		return userConverter.toDTO(userRepository.save(userEntity));
	}
	
	

}
