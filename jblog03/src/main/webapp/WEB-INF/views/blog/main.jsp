<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<% pageContext.setAttribute("newLine", "\n"); %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>${blogVo.title }</h1>
				<c:import url="/WEB-INF/views/includes/blogMenu.jsp"/>
				
		</div>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${post.title }</h4>
					<p>
						${fn:replace(post.contents, newLine, "<br>") }
					<p>
				</div>
				<ul class="blog-list">
				<c:forEach var="postVo" items="${PostVoList }">
					<li><a href="${pageContext.request.contextPath }/${blogId}/${postVo.categoryId}/${postVo.id}">${postVo.title }</a> <span>${postVo.regDate }</span>	</li>
				</c:forEach>	
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img id="profile" src="${pageContext.request.contextPath}${blogVo.profile}">
					

			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
			<c:forEach var="categoryVo" items="${CategoryVoList }">
				<li><a href="${pageContext.request.contextPath }/${blogId}/${categoryVo.id}">${categoryVo.name }</a></li>
			</c:forEach>
			</ul>
		</div>
		
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>