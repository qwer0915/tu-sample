package com.framework.register;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/account")
@Slf4j
public class RegisterController {
	RegisterService registerService;
	public RegisterController(RegisterService registerService) {
		this.registerService=registerService;
	}
	@GetMapping("/register")
	public ModelAndView register() {
		log.info("REQUEST REGISTER");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("register"); // regiset.html Resolve
		return mv;
	}
	@PostMapping(value="/request-register")
	@ResponseBody
	public Map<String,Object> requestRegister(@RequestBody Map<String,Object> params){
		Map<String,Object> result=new HashMap<>();
		result=registerService.requestRegister(params);
		return result;
	}
	@GetMapping("/detail/{userId}")
	public ModelAndView getMember(@PathVariable("userId") String userId) {
		log.info("회원 상세 페이지 호출");
		ModelAndView mv = new ModelAndView();
		
		Map<String, Object> result = registerService.getMember(userId);
		mv.addObject("result", result);
		mv.setViewName("member-detail");
		
		return mv;
	}
	@PostMapping("request-modify")
	@ResponseBody
	public Map<String,Object> modify(@RequestBody Map<String,Object> params){
		Map<String,Object> result=registerService.requestModify(params);
		return result;
	}
	@PostMapping("request-remove")
	@ResponseBody
	public Map<String,Object> remove(@RequestBody Map<String,Object> params){
		Map<String,Object> result=registerService.requestRemove(params);
		return result;
	}
}
