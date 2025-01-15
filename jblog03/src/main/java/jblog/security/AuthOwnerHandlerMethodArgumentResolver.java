package jblog.security;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jblog.vo.UserVo;

public class AuthOwnerHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
    public boolean supportsParameter(MethodParameter parameter) {
        // resolverArgument처리 할 조건
		// httpsession에 있는 authUser의 id와 /{userid}가 일치할 경우
		AuthOwner authOwner = parameter.getParameterAnnotation(AuthOwner.class);
		
		if(authOwner == null) {
			return false;
		}
		return true;
    }

	 @Override
	    public Object resolveArgument(MethodParameter parameter, 
	                                  ModelAndViewContainer mavContainer, 
	                                  NativeWebRequest webRequest, 
	                                  WebDataBinderFactory binderFactory) throws Exception {

	        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
	        // URL Path에서 값 추출
	        String userIdInPath = request.getRequestURI().split("/")[2]; // 예: /domain/{userId}의 2번째 요소

	        // 세션에서 authUser 가져오기
	        HttpSession session = request.getSession();
	        UserVo authUser = (UserVo) session.getAttribute("authUser");

	        // authUser와 URL Path의 userId 비교
	        if (authUser != null && authUser.getId().equals(userIdInPath)) {
	            return true; // 조건 만족
	        }

	        return false; // 조건 불만족
	    }
}