package com.camera.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.camera.common.APIWindyResponse;
import com.camera.entity.RegionEntity;
import com.camera.modelAPI.Webcams;
import com.camera.repo.CameraRepository;
import com.camera.repo.RegionRepository;

@RestController
@ControllerAdvice
public class RegionController {
	@Autowired
	RegionRepository regionRepository;
	@Autowired
	CameraRepository cameraRepository;

	@PostMapping("/regions")
	public ResponseEntity<?> CloneRegions() {
		try {
			String url = "https://api.windy.com/api/webcams/v2/list/webcam=1481291050,1609741784,1622174931,1651324515?key=SGOCSQ1w0Rdfz0vWx08Ia6PVQOeydmYQ&show=webcams:url,location,user";
			RestTemplate restTemplate = new RestTemplate();
			APIWindyResponse apiWindyResponses = restTemplate.getForObject(url, APIWindyResponse.class);
			RegionEntity regionEntity ;
			for (Webcams webcams : apiWindyResponses.getResult().getWebcams()) {
				regionEntity = new RegionEntity();
				
				regionEntity.setName(webcams.getUser().getName());
				regionEntity.setActivate(webcams.getLocation().getTimezone());
				regionEntity.setSerial(webcams.getId());
				regionEntity = regionRepository.save(regionEntity);
			}
			return ResponseEntity.ok("clone region database successfully ");
		} catch (Exception e) {
			// Log the error
			e.printStackTrace();
			// Return an error response
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while cloning the regions");
		}
	}
	@GetMapping("/regions")
	public ResponseEntity<List<RegionEntity>> getCamera(){
		return ResponseEntity.ok(regionRepository.findAll());
	}

//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<?> handleException(Exception e) {
//		// Log the error
//		e.printStackTrace();
//		// Return an error response
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//				.body("An error occurred while processing the request");
//	}
}
