package jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import jblog.service.UserService;
import jblog.vo.UserVo;

@RequestMapping("user")
@Controller
public class UserController {
	private UserService userService;
	
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/join")
	public String join() {
		return "user/join";
	}
	
	@PostMapping("/join")
	public String join(@ModelAttribute("userVo") @Valid UserVo userVo) {
		//Valid 처리 추가하기
		userService.join(userVo);
		return "redirect:/";
	}
	
}
