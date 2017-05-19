<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="../include/header.jsp"%>
<body>
<h1>
	read 페이지  
</h1>

	<label>idx</label><input type="text" value="${board.idx}" readonly="readonly"> <br>
	<label>page</label><input type="text" value="${cri.page}" readonly="readonly"> <br>
	<label>제목</label><input type="text" name='title' value="${board.title}" readonly="readonly"><br>
	<label>컨텐츠</label><textarea name="content" rows="3" readonly="readonly">${board.content}</textarea><br>
	<label>작성자</label> <input type="text" name="writer" value="${board.writer}" readonly="readonly"><br>
	
	<form id="frm">
		<input type='hidden' name='idx' value="${board.idx}">
		<input type='hidden' name='page' value ="${cri.page}">
		<input type='hidden' name='perPageNum' value ="${cri.perPageNum}">
		<input type='hidden' name='searchType' value ="${cri.searchType}">
		<input type='hidden' name='keyword' value ="${cri.keyword}">
	</form>  	
	
	
	<%-- <button type="button" class="btn-modify"><a href="/board/modify?idx=${board.idx}&page=${cri.page}">수정</a></button>
	<button type="button" class="btn-delete"><a href="/board/remove?idx=${board.idx}&page=${cri.page}">삭제</a></button>
	<button type="button" class="btn-cancel"><a href="/board/listCri?page=${cri.page}">리스트이동</a></button> --%>
	
	
	<button type="button" class="btn-modify">수정</button>
	<button type="button" class="btn-delete">삭제</button>
	<button type="button" class="btn-cancel">리스트이동</button>
	
	<script>
		
		var frm = $("#frm");
		
 		$(".btn-modify").on("click", function(){
			frm.attr("action", "/board/modify");
			frm.attr("method", "get");		
			frm.submit();
		});
		$(".btn-cancel").on("click", function(){
			frm.attr("action", "/board/listCri");
			frm.attr("method", "get");
			frm.submit();
		}); 
		
		$(".btn-delete").on("click", function(){
			frm.attr("action", "/board/remove");
			frm.attr("method", "post");
			frm.submit();
		});
		
	</script>
</body>
</html>
