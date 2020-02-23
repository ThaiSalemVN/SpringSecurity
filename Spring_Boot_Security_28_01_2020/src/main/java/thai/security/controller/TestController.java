package thai.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import thai.security.service.UserService;

@Controller
public class TestController {

	@Autowired
	UserService userService;
	
	@RequestMapping("/trang-chu")
	public String trangChu() {
		return "abc";
	}
		
	@RequestMapping("/ok")
	public String OK() {
		return "loginsuccess";
	}
		
	@RequestMapping("/user")
	public String trangUser() {
		return "user";
	}
		
	@RequestMapping("/admin")
	public String trangAdmin() {
		return "admin";
	}
		
	
	@GetMapping("/trang-dang-nhap")
	public String login() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)){	
		
			return "redirect:/trang-chu";		
		}	
		return "login";
	}
	
	
	
}
