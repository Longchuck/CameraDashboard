package com.camera.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.camera.dto.TokenDTO;
import com.camera.dto.UserDTO;
import com.camera.entity.UserEntity;
import com.camera.map.UserMapper;
import com.camera.repo.UserRepository;
import com.camera.security.jwt.JwtUtils;
import com.camera.security.service.UserDetailsImpl;

@RestController
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	private UserMapper userMapper;
	
	@PostMapping("/user-profile")
	public ResponseEntity<UserDTO> getUserByUserName(@RequestBody TokenDTO tokenDTO){
		String userName = jwtUtils.getUserNameFromJwtToken(tokenDTO.getAccessToken());

		UserEntity userEntity = userRepository.findByUserName(userName);
		UserDTO userDTO = userMapper.toDto(userEntity);
		return ResponseEntity.ok(userDTO);
	}
	

}
