package jblog.security;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogoutInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = (HttpSession)request.getSession();
		session.removeAttribute("authUser");
		session.invalidate(); // 세션 무효화
		
		response.sendRedirect(request.getContextPath());
		
		return false;
	}
}
