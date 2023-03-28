package com.camera.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@RequestMapping("/")
	public String login(Model model) {
		model.addAttribute("title","Login - CameraDashBoard");
		return "login";
	}
	

}
