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
<div>
	<select name="searchType">
		<option value="n"
			<c:out value="${cri.searchType == null?'selected':''}"/>>  <!-- @ModelAttribute cri를 사용했기에.. 이렇게 사용이 가능하다. -->
			검색조건없음</option>
		<option value="t"
			<c:out value="${cri.searchType eq 't'?'selected':''}"/>>
			제목으로검색</option>
		<option value="c"
			<c:out value="${cri.searchType eq 'c'?'selected':''}"/>>
			내용으로검색</option>
		<option value="w"
			<c:out value="${cri.searchType eq 'w'?'selected':''}"/>>
			작성자로검색</option>
		<option value="tc"
			<c:out value="${cri.searchType eq 'tc'?'selected':''}"/>>
			제목 or 내용으로검색</option>
		<option value="cw"
			<c:out value="${cri.searchType eq 'cw'?'selected':''}"/>>
			내용 or 작성자로검색</option>
		<option value="tcw"
			<c:out value="${cri.searchType eq 'tcw'?'selected':''}"/>>
			제목 or 내용 or 작성자로 검색</option>
	</select> 
	<input type="text" name='keyword' id="keyword" value='${cri.keyword }'>
	<button class='btn-search'>검색</button>
	<button class="btn-write">글쓰기</button>
</div>

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
<%-- 		<td><a href="/board/read?idx=${boardVO.idx}&page=${pageMaker.cri.page}">${boardVO.title}</a></td> --%>
		<td><a href="/board/read${pageMaker.makeSearchQuery(pageMaker.cri.page)}&idx=${boardVO.idx}">${boardVO.title}</a></td>
		<td>${boardVO.writer}</td>
		<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.regdate}" /></td>
		<td>${boardVO.viewcnt}</td>
	</tr>
</c:forEach>
</table>

<ul class="pageing">

	<%-- <c:set var="queryString" value="&perPageNum=${perPageNum}&searchType=${searchType}&keyword=${keyword}"  />  el로 변수에 저장 법....--%> 

	<c:if test="${pageMaker.prev}">
<%-- 		<li><a href="listCri?page=${pageMaker.startPage - 1}&perPageNum=${pageMaker.cri.perPageNum}&searchType=${pageMaker.cri.searchType}&keyword=${pageMaker.cri.keyword}">&laquo;</a></li> --%>
			<li><a href="listCri${pageMaker.makeSearchQuery(pageMaker.startPage - 1)}">&laquo;</a></li>
	</c:if>

	<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="page">  <!-- !!!!!책에는 idx로 나왔는데 page로 수정 -->
		<%-- <li <c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>> --%>
		<li class="${pageMaker.cri.page == page ? 'active' : ''}"/>
<%-- 			<a href="listCri?page=${page}&perPageNum=${pageMaker.cri.perPageNum}&searchType=${pageMaker.cri.searchType}&keyword=${pageMaker.cri.keyword}">${page}</a> --%>
			<a href="listCri${pageMaker.makeSearchQuery(page)}">${page}</a>
		</li>
	</c:forEach>

	<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
<%-- 		<li><a href="listCri?page=${pageMaker.endPage + 1}&perPageNum=${pageMaker.cri.perPageNum}&searchType=${pageMaker.cri.searchType}&keyword=${pageMaker.cri.keyword}">&raquo;</a></li> --%>
		<li><a href="listCri${pageMaker.makeSearchQuery(pageMaker.endPage + 1)}">&raquo;</a></li>
	</c:if>
</ul>


<%-- <form id="pagingFrm">
	<input type="hidden" name="page" value="${pageMaker.cri.page}">
	<input type="hidden" name="perPageNum" value="${pageMaker.cri.perPageNum}">
</form>
<script type="text/javascript">

	$("a").click(function(e){
		e.preventDefault();
 		var href = $(this).attr("href");
		var frm = $("#pagingFrm");
		frm.attr({
			"action" : href,
			"method" : "post"
		})
		frm.submit(); 
	});

</script> --%>

<script>

	$('.btn-search').on("click",	function(event) {
		self.location = "listCri"
				+ '${pageMaker.makeQuery(1)}'
				+ "&searchType=" + $("select option:selected").val()
				+ "&keyword=" + $('#keyword').val();
	});

 	$('.btn-write').on("click", function(evt) {
		self.location = "register";
	}); 

</script>
</body>
</html>
