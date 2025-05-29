package com.framework;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	@GetMapping("/home")
	public String home(HttpServletRequest request, Model model) {
		log.info("|| Main !! Page ||");
		Cookie[] cookies = request.getCookies();
		List<String> cookieList = new ArrayList<>();

		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				String info = "쿠키 이름: " + cookie.getName() + ", 값: " + cookie.getValue();
				cookieList.add(info);
				log.info(info);
			}
		} else {
			cookieList.add("쿠키 없음");
			log.info("쿠키 없음");
		}

		model.addAttribute("cookieMessages", cookieList);
		return "home"; // home.html
		// thymeleaf로 home.html resolving
	}

	@RequestMapping("/hello")
	public String hello() {
		log.info("TEST HELLO");
		return "Hello";
	}
}
