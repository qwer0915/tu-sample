package com.framework;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HomeController {
	@RequestMapping("/hello")
	public String hello() {
		log.info("TEST HELLO");
		return "Hello";
	}
}
