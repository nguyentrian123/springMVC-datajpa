package com.laptrinhjavaweb.converter;

import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.UserEntity;

@Component
public class UserConverter {

	// chuyển lại DTO để lưu vào db
		public UserDTO toDTO(UserEntity entity)
		{
			UserDTO result = new UserDTO();
			result.setId(entity.getId());
			result.setUserName(entity.getUserName());
			result.setPassword(entity.getPassword());
			result.setStatus(entity.getStatus());
			result.setRoleid(1);
			return result;
			
		}
		
		public UserEntity toEntity(UserDTO dto)
		{
			UserEntity result = new UserEntity();
			result.setUserName(dto.getUserName());
			result.setPassword(dto.getPassword());
			result.setFullName(dto.getFullName());
			result.setStatus(dto.getStatus());
		
			
			return result;
		}
		
		
}
