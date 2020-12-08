package com.southernartsoap.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class MainController {
	@GetMapping(value = "/")
	public String home() {
		return "index";
	}

	@GetMapping(value = "/about")
	public String aboutUs() {
		return "about";
	}

}
