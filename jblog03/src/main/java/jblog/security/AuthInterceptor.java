package jblog.security;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jblog.vo.UserVo;

public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 1. Handler 종류 확인 request에 의한 건지 assets에 의한 건지 확인
		if (!(handler instanceof HandlerMethod)) { // assets에 의한 접근
			// DefaultServletRequestHandler 타입인 경우
			// DefaultServletHandler가 처리하는 경우(정적자원, /assets/**, mapping이 안되어 있는 URL)
			return true;
		}
		
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		if(auth == null) {
			return true;
		}
		
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		if(authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		String uri = request.getRequestURI();
		String[] uriParts = uri.split("/");
		
		if(uriParts.length < 3) {
			return false;
		}
		
		String blogId = uriParts[2];
		
		if(authUser.getId().equals(blogId)) {
			return true;
		}
		
		response.sendRedirect(request.getContextPath());
		return false;
		
	}

}
