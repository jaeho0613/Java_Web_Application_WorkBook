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
	<form action="update" method="post">
		번호: <input type="text" name="no" value="${member.mno}" readonly /><br>
		이름: <input type="text" name="name" value="${member.mname }" /><br>
		이메일: <input type="text" name="email" value="${member.email}" /><br>
		생성일: <input type="text" name="cre_date" value="${member.cre_date}" readonly /><br>
		<input type="submit" value="변경">
		<input type="button" value="삭제" onclick='location.href="delete?no=${member.mno}"'/>
		<input type="button" value="뒤로가기" onclick="histroy.back()">
	</form>
</body>

</html>