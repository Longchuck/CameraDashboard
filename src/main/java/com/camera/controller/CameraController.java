package com.camera.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.camera.common.APIWindyResponse;
import com.camera.entity.CameraEntity;
import com.camera.modelAPI.Webcams;
import com.camera.repo.CameraRepository;

@RestController
public class CameraController {
	@Autowired
	private CameraRepository cameraRepository;
	//save camera to dbs
	@PostMapping("/camerasOnline")
	public void pushCamera() {
		String url = "https://api.windy.com/api/webcams/v2/list/webcam=1481291050,1609741784,1622174931,1651324515?key=SGOCSQ1w0Rdfz0vWx08Ia6PVQOeydmYQ&show=webcams:url,location";
		RestTemplate restTemplate = new RestTemplate();
		APIWindyResponse apiWindyResponses =  restTemplate.getForObject(url, APIWindyResponse.class);
		CameraEntity cameraEntity;
		for(Webcams webcams : apiWindyResponses.getResult().getWebcams()) {
			cameraEntity = new CameraEntity();
			cameraEntity.setSerial(webcams.getId());
			cameraEntity.setStatus(webcams.getStatus());
			cameraEntity.setHome_id(webcams.getLocation().getRegion_code());
			String tempUrl = "https://webcams.windy.com/webcams/stream/"+webcams.getId();
			cameraEntity.setUrl(tempUrl);
			cameraEntity = cameraRepository.save(cameraEntity);
		}
	}
	
	//Respone API for frontend
	@GetMapping("/camerasOnline")
	public List<CameraEntity> getCamera(){
		return cameraRepository.findAll();
	}

}
