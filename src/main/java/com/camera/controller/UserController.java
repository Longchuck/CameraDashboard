package com.camera.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.camera.dto.UserDTO;
import com.camera.entity.UserEntity;
import com.camera.map.UserMapper;
import com.camera.repo.UserRepository;

@RestController
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@GetMapping("/user-profile/{userName}")
	public ResponseEntity<UserDTO> getUserByUserName(@PathVariable String userName){
		UserEntity userEntity = userRepository.findOneByUserName(userName);
		if (userEntity == null) {
			return ResponseEntity.notFound().build();
		}
		UserDTO userDTO = userMapper.toDto(userEntity);
		return ResponseEntity.ok(userDTO);
	}
	

}
