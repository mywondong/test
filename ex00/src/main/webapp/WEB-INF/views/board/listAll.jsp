<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="../include/header.jsp"%>
<body>
<h1>
	listAll 페이지  
</h1>
<h2>
	${msg}
</h2>
<table>
	<tr>
		<th>idx</th>
		<th>제목</th>
		<th>글쓴이</th>
		<th>날짜</th>
		<th>조회수</th>
	</tr>
	
<c:forEach items="${list}" var="boardVO">
	<tr>
		<td>${boardVO.idx}</td>
		<td><a href='/board/read?idx=${boardVO.idx}'>${boardVO.title}</a></td>
		<td>${boardVO.writer}</td>
		<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.regdate}" /></td>
		<td>${boardVO.viewcnt}</td>
	</tr>
</c:forEach>

</table>




</body>
</html>
