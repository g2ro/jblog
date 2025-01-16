<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<ul>
	<c:choose>
		<c:when test="${empty authUser }">
			<li><a href="${pageContext.request.contextPath }/user/login">로그인</a></li>
		</c:when>
		<c:otherwise>
            <li><a href="${pageContext.request.contextPath }/user/logout">로그아웃</a></li>
            <c:if test="${authUser.id == blogId}">
                <li><a href="${pageContext.request.contextPath }/${authUser.id }/admin/basic">블로그 관리</a></li>
            </c:if>
        </c:otherwise>
	</c:choose>
</ul>