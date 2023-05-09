package com.camera.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.camera.dto.TokenDTO;
import com.camera.dto.UserDTO;
import com.camera.entity.UserEntity;
import com.camera.map.UserMapper;
import com.camera.repo.UserRepository;
import com.camera.security.jwt.JwtUtils;

import io.swagger.annotations.ApiOperation;

@RestController
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	private UserMapper userMapper;
	
	@ApiOperation(value = "Get all users", notes = "Returns a list of all users by accessToken")
	@PostMapping("/user-profile")
	public ResponseEntity<UserDTO> getUserByUserName(@RequestBody TokenDTO tokenDTO){
		String userName = jwtUtils.getUserNameFromJwtToken(tokenDTO.getAccessToken());

		UserEntity userEntity = userRepository.findByUserName(userName);
		UserDTO userDTO = userMapper.toDto(userEntity);
		return ResponseEntity.ok(userDTO);
	}
	

}
