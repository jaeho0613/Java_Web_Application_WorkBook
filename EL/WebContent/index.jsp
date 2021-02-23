<%@page import="java.util.ResourceBundle"%>
<%@page import="spms.vo.Member"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.LinkedList"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
	<br> \${10 < 11 }=${10 < 11 }
	<br> \${10 > 11 } = ${10 > 11 }
	<br> \${10 <= 11 }=${10 <=11 }
	<br> \${10 >= 11 } = ${10 >= 11 }
	<br>

	<p>Empty - null 조사</p>
	\${empty title } = ${empty title }
	<br> \${empty title2 } = ${empty title2 }
	<br>

	<p>조건</p>
	\${10 > 20 ? "크다" : "작다" } = ${10 > 20 ? "크다" : "작다" }
	<br>

	<p>JSTL - EL 확장 라이브러리</p>
	<c:out value="출력할 값">기본값</c:out>
	<br>
	<c:out value="${null}">반갑습니다.</c:out>
	<br>
	<c:out value="안녕하세요!">반갑습니다.</c:out>
	<br>
	<c:out value="${null}"></c:out>
	<br>

	<p>JSTL - 값 설정 방식</p>
	<c:set var="username1" value="홍길동"></c:set>
	<c:set var="username2">임꺽정</c:set>
	${username1 }
	<br> ${username2 }
	<br>

	<p>JSTL - 기본 보관소 page</p>
	${pageScope.username1 }
	<br> ${requestScope.username1 }
	<br>

	<p>JSTL - 보관소 지정 scope 속성</p>
	<c:set var="username3" scope="request">일지매</c:set>
	${pageScope.username3 }
	<br> ${requestScope.username3 }
	<br>

	<p>JSTL - 기존의 값 덮어씀</p>
	<%
	pageContext.setAttribute("username4", "똘이장군");
	%>
	기존 값 = ${ username4 }
	<br>
	<c:set var="username4">주먹대장</c:set>
	덮어쓴 값 = ${ username4 }
	<br>

	<p>JSTL - 객체의 프로퍼티 값 변경</p>
	<%!public static class MyMember {
		int no;
		String name;

		public int getNo() {
			return no;
		}

		public void setNo(int no) {
			this.no = no;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}%>
	<%
	MyMember member = new MyMember();
	member.setNo(100);
	member.setName("홍길동");
	pageContext.setAttribute("member", member);
	%>
	${ member.name }
	<br>
	<c:set target="${member }" property="name" value="임꺽정"></c:set>
	${ member.name }
	<br>

	<p>보관소에 저장된 값 제거</p>
	<%
	pageContext.setAttribute("username1", "홍길동");
	%>
	${ username1 }
	<br>
	<c:remove var="username1" />
	${ username1 }
	<br>

	<p>if문 - 1</p>
	<c:if test="${10 > 20 }" var="result1">
	10은 20보다 크다.<br>
	</c:if>
	${result1 }
	<br>

	<p>if문 - 2</p>
	<c:if test="${10 < 20 }" var="result2">
	10은 20보다 크다.<br>
	</c:if>
	${result2 }
	<br>

	<p>switch문</p>
	<c:set var="userid" value="admin" />
	<c:choose>
		<c:when test="${ userid == 'hong' }">
			홍길동님 반갑습니다.
		</c:when>
		<c:when test="${ userid == 'leem' }">
			임꺽정님 반갑습니다.
		</c:when>
		<c:when test="${ userid == 'admin' }">
			관리자 전용 페이지입니다.
		</c:when>
		<c:otherwise>
			등록되지 않은 사용자입니다.
		</c:otherwise>
	</c:choose>

	<p>forEach문 - 1</p>
	<%
	pageContext.setAttribute("nameList", new String[] { "홍길동", "임꺽정", "일지매" });
	%>
	<ul>
		<c:forEach var="name" items="${ nameList }">
			<li>${name }</li>
		</c:forEach>
	</ul>

	<p>forEach문 - 2</p>
	<%
	pageContext.setAttribute("nameList2", new String[] { "홍길동", "임꺽정", "일지매", "주먹대장", "똘이장군" });
	%>
	<ul>
		<c:forEach var="name" items="${ nameList2 }" begin="2" end="3">
			<li>${name }</li>
		</c:forEach>
	</ul>

	<p>토큰 얻기</p>
	<%
	pageContext.setAttribute("tokens", "v1=20&v2=30&op=+");
	%>
	<c:forTokens var="item" items="${ tokens }" delims="&">
		<li>${ item }</li>
	</c:forTokens>

	<p>URL 만들기</p>
	<c:url var="calcUrl" value="http://localhost:9999/calc">
		<c:param name="v1" value="20"></c:param>
		<c:param name="v2" value="30"></c:param>
		<c:param name="op" value="+"></c:param>
	</c:url>

	<p>
		<a href="${ calcUrl }">계산하기</a>
	<p>
	<p>주소 결과값 받기</p>
	<textarea rows="10" cols="80">
		<c:import
			url="http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson?serviceKey=1ARTCvv9nZP%2FkfDX%2BGRbdnl0yTlgxTvjM7Y6eklVWUqS1t2HEXZX0YdTg8SdAe1dvhfjO4VuUF9TBHxH3I%2FMpg%3D%3D&pageNo=1&numOfRows=10&startCreateDt=20200310&endCreateDt=20200315" />	
	</textarea>

	<p>리다이렉트</p>
	<%-- <c:redirect url="https://www.naver.com"/> --%>
	c:redirect url="https://www.naver.com"

	<p>날짜 형식</p>
	<fmt:parseDate var="date1" value="2013-11-16" pattern="yyyy-MM-dd" />
	<fmt:formatDate value="${date1}" pattern="MM/dd/yy"/>
</body>
</html>