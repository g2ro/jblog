<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>
<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<body>
	<div class="center-content">
		<h1 class="logo">JBlog</h1>
			<c:import url="/WEB-INF/views/includes/menu.jsp"/>
		<form class="login-form" method="post" action="${pageContext.request.contextPath }/user/auth">
      		<label>아이디</label> <input type="text" name="id" value="${Optional.ofNullable(id).orElse('') }">
      		<label>패스워드</label> <input type="password" name="password" value="">
      		<c:if test="${result == 'fail' }">
      			<p>
      				로그인에 실패하셨습니다.
      			</p>
      		</c:if>
      		<input type="submit" value="로그인">
      		
		</form>
	</div>
</body>
</html>
