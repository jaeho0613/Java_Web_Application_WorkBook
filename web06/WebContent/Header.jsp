<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div
	style="background-color: #00008b; color: #ffff; height: 20px; padding: 5px;">
	SPMS (Simple Project Management System)
	<c:if test="${!empty sessionScope.member }">
		<span style="float: right;"> ${ sessionScope.member.mname } 
			<a style="color: white;" href="<%=request.getContextPath()%>/auth/logout.do">로그아웃</a>
		</span>
	</c:if>
</div>