<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../include/header.jsp"%>
<body>
<h1>
	register 페이지
</h1>
<h2>${msg}</h2>

	<!-- action이 적용되지 않았다. 액션없이 전송을 누르면 POST로 전송된다....... post로 전송하면 board/register 요청함 -->
	<form method="POST">
		<label>제목</label><input type="text" name="title" placeholder="제목"> <br>
		<label>컨텐츠</label><textarea name="content" rows="3" placeholder="컨텐츠"></textarea> <br>
		<label>작성자</label><input type="text" name="writer" placeholder="글쓴이"> <br>
		<button type="submit">전송</button>
	</form>

</body>
</html>
