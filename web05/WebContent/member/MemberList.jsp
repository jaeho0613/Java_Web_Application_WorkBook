<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="spms.vo.Member" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>회원 목록</title>
</head>
<body>
    <h1>회원 목록</h1>
    <p><a href="add">신규 회원</a></p>
<% 
    ArrayList<Member> members = (ArrayList<Member>) request.getAttribute("members");
	for(Member member : members) {
%>
	<%= member.getMno() %>,
	<a href='update?no=<%=member.getMno()%>'><%= member.getMname()%></a>,
	<%= member.getEmail() %>,
	<%= member.getCre_date() %>
	<a href='delete?no=<%= member.getMno() %>'>[삭제]</a><br>
<%
	}
%>
</body>
</html>