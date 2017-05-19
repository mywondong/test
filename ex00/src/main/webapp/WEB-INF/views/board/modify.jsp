<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="../include/header.jsp"%>
<body>
<h1>
	read 페이지  
</h1>

<form id="frm" method="post" action="/board/modify">
	<label>idx</label><input type="text" value="${board.idx}" readonly="readonly"> <br>
	<label>page</label><input type="text" value="${cri.page}" readonly="readonly"> <br>
	<label>제목</label><input type="text" name='title' value="${board.title}" ><br>
	<label>컨텐츠</label><textarea name="content" rows="3" >${board.content}</textarea><br>
	<label>작성자</label> <input type="text" name="writer" value="${board.writer}" ><br>
	
 	<input type='hidden' name='idx' value="${board.idx}">
	<input type='hidden' name='page' value ="${cri.page}">
	<input type='hidden' name='perPageNum' value ="${cri.perPageNum}">
	<input type='hidden' name='searchType' value ="${cri.searchType}">
	<input type='hidden' name='keyword' value ="${cri.keyword}">	
	<button type="submit">저장</button>
<%-- 	<button type="button"><a href="/board/read?idx=${board.idx}&page=${cri.page}">취소</a></button> --%>
	<button type="button" class="btn-cancel"><a href="">취소</a></button>
</form>

	
	
	<script type="text/javascript">

		// URL의 쿼리스트링 값 가져오기 
		var queryString = window.location.search;
		$(".btn-cancel a").attr("href", "/board/read"+queryString);
	
	</script>
	
</body>
</html>
