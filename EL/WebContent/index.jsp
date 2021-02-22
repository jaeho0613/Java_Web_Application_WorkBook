<%@page import="java.util.ResourceBundle"%>
<%@page import="spms.vo.Member"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>EL (Expression Language)</title>
<link rel="stylesheet" href="./css/style.css">
</head>

<body>
	<%
	pageContext.setAttribute("scores", new int[] { 90, 80, 70, 60, 50 });

	List<String> nameList = new LinkedList<String>();
	nameList.add("홍길동");
	nameList.add("임꺽정");
	nameList.add("일지매");
	pageContext.setAttribute("nameList", nameList);

	Map<String, String> map = new HashMap<String, String>();
	map.put("s01", "홍길동");
	map.put("s02", "임꺽정");
	map.put("s03", "일지매");
	pageContext.setAttribute("map", map);

	pageContext.setAttribute("member", new Member().setMno(100).setMname("홍길동").setEmail("hong@test.com"));

	pageContext.setAttribute("myRB", ResourceBundle.getBundle("spms.resource.MyResourceBundle"));

	pageContext.setAttribute("title", "EL 연산자!");
	%>

	<p>배열에서 인덱스 값 꺼내기</p>
	\${scores[2]} = ${scores[2]}
	<br>

	<p>리스트에서 인덱스 값 꺼내기</p>
	\${nameList[1] } = ${nameList[1] }
	<br>


	<p>맵 객체에서 키 s02로 저장된 값 꺼내기</p>
	\${map.s02 } = ${map.s02 }
	<br>


	<p>자바빈에서 프로퍼티 email의 값 꺼내기</p>
	\${member.email} = ${member.email}
	<br>


	<p>리소스번들 객체에서 값 꺼내기</p>
	\${myRB.OK } = ${myRB.OK }
	<br>

	<p>산술 연산자</p>
	\${10 + 20 } = ${10 + 20}
	<br> \${10 - 20 } = ${10 - 20}
	<br> \${10 * 20 } = ${10 * 20}
	<br> \${10 / 20 } = ${10 / 20}
	<br> \${10 + 20 } = ${10 % 20}

	<p>논리 연산자</p>
	\${true && true } = ${true && true }
	<br> \${true || true } = ${true || true }
	<br> \${!true } = ${!true }
	<br>

	<p>관계 연산자</p>
	\${10 == 11 } = ${10 == 11 }
	<br> \${10 != 11 } = ${10 != 11 }
	<br> \${10 < 11 } = ${10 < 11 }
	<br> \${10 > 11 } = ${10 > 11 }
	<br> \${10 <= 11 } = ${10 <= 11 }
	<br> \${10 >= 11 } = ${10 >= 11 }
	<br>

	<p>Empty - null 조사</p>
	\${empty title } = ${empty title }
	<br> \${empty title2 } = ${empty title2 }
	<br>
	
	<p>조건</p>
	\${10 > 20 ? "크다" : "작다" } = ${10 > 20 ? "크다" : "작다" }<br>

</body>

</html>