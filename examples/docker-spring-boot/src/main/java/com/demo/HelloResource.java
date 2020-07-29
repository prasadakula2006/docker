package com.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/docker/hello")
@RestController
public class HelloResource {

	@GetMapping
	public String hello() {
		return "Hello";
	}

}
