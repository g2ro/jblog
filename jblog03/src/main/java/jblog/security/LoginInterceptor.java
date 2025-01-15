package jblog.security;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jblog.service.UserService;
import jblog.vo.UserVo;

public class LoginInterceptor implements HandlerInterceptor {
	private UserService userService;
	
	public LoginInterceptor(UserService userService) {
		this.userService = userService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (userService == null) {
	        System.out.println("UserService is null in LoginInterceptor");
	        throw new NullPointerException("UserService is not initialized");
	    }
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		UserVo authVo = userService.getUser(id, password);
		
		//로그인 실패 시
		if(authVo == null) {
			request.setAttribute("id", id);
			request.setAttribute("result", "fail");
			request
			.getRequestDispatcher("/WEB-INF/views/user/login.jsp")
			.forward(request, response);
			return false;
		}
		
		//로그인 성공 시 session scpoe에서 userVo객체 저장
		HttpSession session = request.getSession();
		session.setAttribute("authUser", authVo);
		
		response.sendRedirect(request.getContextPath());
		
		return false;
		
	}
	
	
}
