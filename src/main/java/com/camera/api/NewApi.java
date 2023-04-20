package com.camera.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.camera.entity.UserEntity;

@RestController
public class NewApi {
	
	//@RequestMapping(value = "/new", method = RequestMethod.POST)
	@PostMapping(value = "/new")
	public UserEntity createNew(@RequestBody UserEntity model) {
		return model;
	}
	
	@PutMapping(value = "/new")
	public UserEntity updateNew(@RequestBody UserEntity model) {
		return model;
	}
	@GetMapping(value = "/new")
	public UserEntity getNew(@RequestBody UserEntity model) {
		return model;
	}

	@DeleteMapping(value = "/new")
	public void deleteNew(@RequestBody long[] ids) {
		
	}
}
