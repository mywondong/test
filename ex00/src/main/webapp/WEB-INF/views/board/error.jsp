<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../include/header.jsp"%>
<body>
<h1>
	error 페이지  (익셉션처리공통)
</h1>

<h4>${exception.getMessage()}</h4>

<ul>
<c:forEach items="${exception.getStackTrace()}" var="stack">
	<li>${stack.toString()}</li>
</c:forEach>
</ul>

</body>
</html>
