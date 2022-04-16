package com.laptrinhjavaweb.api.wed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.service.IUserService;

@RestController(value="userAPIOfWeb")
public class UserAPI {

	@Autowired
	private IUserService userService;
	
	@PostMapping("api/user")
	public UserDTO createUser(@RequestBody UserDTO userDTO)
	{
		return userService.save(userDTO);
	}
	
}
