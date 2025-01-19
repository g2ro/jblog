<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<script>
$(function () {
    $("#btn-checkblogId").click(function () {
    var blogId = $("#blog-id").val();
    if(blogId == ""){
       return;
    }
    $.ajax({
    	url: "${pageContext.request.contextPath}/user/checkblogId?blogId=" + blogId,
    	type: 'get',
    	dataType: 'json',
        success: function(response) {
            if(response.result != "success"){
            	console.error(response.message);
            	return
            }
            
            if(response.data.exist){
            	alert("아이디가 존재합니다. 다른 아이디를 사용해 주세요.")
            	$("#blog-id").val("")
            	$("#blog-id").focus();
            	return
            }
            
            $("#img-checkblogId").show();
			$("#btn-checkblogId").hide();
			$("#btn-join").removeAttr("disabled");
        },
        error:function(xhr, status, err){
			console.error(err);
		}
    });    
    });
});
</script>

<body>
	<div class="center-content">
		<h1 class="logo">JBlog</h1>
		<c:import url="/WEB-INF/views/includes/menu.jsp"/>
		<form:form
			modelAttribute="userVo"
			id="join-form" class="join-form" 
			method="post" action="${pageContext.request.contextPath }/user/join">
			<label class="block-label" for="name">이름</label>
			<!-- <input id="name"name="name" type="text" value=""> -->
			<form:input path="name"/>
			<p style="padding: 5px 0; margin:0; color:#f00">
				<form:errors path="name"/>
			</p>
			
			<label class="block-label" for="blog-id">아이디</label>
			<form:input id="blog-id" name="id" path="id"/>
			<input id="btn-checkblogId" type="button" value="id 중복체크" style="display:">
			<img id="img-checkblogId" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png"> 
			<p style="padding: 5px 0; margin:0; color:#f00">
				<form:errors path="id"/>
			</p>
			<label class="block-label" for="password">패스워드</label>
			<form:password id="password" name="password" path="password"/>
			<p style="padding: 5px 0; margin:0; color:#f00">
				<form:errors path="password"/>
			</p>

			<fieldset>
				<legend>약관동의</legend>
				<form:checkbox path="agreeProv" value="y"/>
				<label class="l-float">서비스 약관에 동의합니다.</label>
				<p style="padding: 5px 0; margin:0; color:#f00">
					<form:errors path="agreeProv"/>
				</p>
			</fieldset>

			<input id="btn-join" type="submit" disabled value="가입하기">

		</form:form>
	</div>
</body>
</html>
