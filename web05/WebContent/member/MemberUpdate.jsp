<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="spms.vo.Member"%>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>회원 수정</title>
</head>

<body>
	<jsp:useBean id="member" class="spms.vo.Member" scope="request" />
	<form action="update" method="post">
		번호: <input type="text" name="no" value="<%= member.getMno() %>" readonly /><br>
		이름: <input type="text" name="name" value="<%= member.getMname()%>" /><br>
		이메일: <input type="text" name="email" value="<%= member.getEmail()%>" /><br>
		생성일: <input type="text" name="cre_date" value="<%= member.getCre_date()%>" readonly /><br>
		<input type="submit" value="변경">
		<input type="button" value="삭제" onclick="location.href='delete?no=<%= member.getMno() %>'">
		<input type="button" value="뒤로가기" onclick="histroy.back()">
	</form>
</body>

</html>