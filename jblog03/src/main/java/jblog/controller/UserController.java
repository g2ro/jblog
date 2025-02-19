package jblog.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.Valid;
import jblog.dto.JsonResult;
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
	public String join(@ModelAttribute("userVo") UserVo userVo) {
		return "user/join";
	}
	
	@PostMapping("/join")
    public String join(@ModelAttribute("userVo") @Valid UserVo userVo, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	Map<String, Object> map = result.getModel();
			model.addAllAttributes(map);
            return "user/join"; // 유효성 검사 실패 시 join.jsp로 이동
        }
        
        userService.join(userVo);
        return "user/joinsuccess"; 
    }
	
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
	
	@GetMapping("/checkblogId")
	@ResponseBody
	public JsonResult checkEmail(@RequestParam(value = "blogId", required=true, defaultValue="") String blogId) {
		System.out.println("hello");
		UserVo userVo = userService.getUserById(blogId);
		
		return JsonResult.success(Map.of("exist", userVo != null));
	}
	
}
