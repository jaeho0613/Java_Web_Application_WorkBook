<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>회원 목록</title>
</head>
<body>
	<jsp:include page="/Header.jsp" />
	<h1>회원 목록</h1>
	<p>
		<a href="add">신규 회원</a>
	</p>

	<c:forEach var="member" items="${ members }">
	${ member.mno },
	<a href="update?=no${ member.mno }">${ member.mname }</a>,
	${ member.email },
	${ member.cre_date }
	<a href="delete?no=${ member.mno }">[삭제]</a>
	</c:forEach>

	<jsp:include page="/Tail.jsp" />
</body>
</html>