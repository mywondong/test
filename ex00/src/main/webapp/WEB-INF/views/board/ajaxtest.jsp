<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="../include/header.jsp"%>
<body>
<h2>Ajax Test</h2>
<ul id="replies">

</ul>
<div>
	<input type="text" name="bno" id="bno" value="28">
	<input type="text" name="replyer" id="replyer">
	<input type="text" name="replytext" id="replytext">
	<button type="button" class="btn-sumbit">전송</button>
</div>
<ul class="pageing">

</ul>




<script type="text/javascript">

	
	$(".btn-sumbit").click(function() {
		var bno = $("#bno").val();
		console.log(bno)
		var replyer = $("#replyer").val();
		var replytext = $("#replytext").val();
		/* console.log($("#bno, #replyer, #replytext").serialize())
		console.log($("#bno, #replyer, #replytext").serializeArray())
		console.log(JSON.stringify({
			'bno' : '28',
			'replyer' : replyer,
			'replytext' : replytext
		})) */
		$.ajax({
			url: '/replies/insert',
			type: 'POST',
			dataType: 'text',
//			data: $("#bno, #replyer, #replytext").serializeArray(),
  			data: JSON.stringify({
				'bno' : bno,
				'replyer' : replyer,
				'replytext' : replytext
			}), 
			
			headers : {
				"Content-Type" : "application/json", // 이상하다 이걸 안쓰니까.. 여기서는 왜러가난다...
				//"X-HTTP-Method-Override" : "POST"
			}
		})
		.done(function(data) {
			//console.log(data);
			if(data === 'SUCCESS') {
				getList(bno);
				getPageList(bno, 1); // 최초 1페이지로 
			}
		})
		.fail(function() {
			console.log("error");
		})
		.always(function() {
			//console.log("complete");
		});
	});


function getList(bno) {
	
	//var bno = 291;
	var str = "";
	
	$.ajax({
		url: '/replies/list/'+bno,
		type: 'GET',
		dataType: 'json',
		//data: {param1: 'value1'},
	})
	.done(function(data) {
		//console.log(data.length);
/* 		$(data).each(function(idx, el) {
			str += "<li>"
			    + this.rno + " : " + this.replytext
			    //+ '<button>수정</button>'
			    + "</li>"
		});
		
		//$("#replies").empty().html(str);
		$("#replies").html(str); */
		
	})
	.fail(function() {
		console.log("error");
	})
	.always(function() {
		//console.log("complete");
	});
}



function getPageList(bno, page){
	
	  $.getJSON("/replies/"+bno+"/"+page , function(data) {  //get방식으로 요청
		  
		  console.log(data.list);
		  console.log(data.pageMaker);
		  
 		  var str ="";
		  
		  $(data.list).each(function(idx, el) {
			  console.log(el.rno)
			  console.log(el.replytext)
			  str += "<li>" 
			  + el.rno + ":" + el.replytext
			  + "</li>";
		  });
		  
		  $("#replies").html(str);
		  
		  printPaging(data.pageMaker);

	  });
}	


function printPaging(pageMaker){
	
	var str = "";
	
	if(pageMaker.prev){
		str += "<li><a href='"+(pageMaker.startPage-1)+"'> << </a></li>";
	}
	
	for(var i=pageMaker.startPage, len = pageMaker.endPage; i <= len; i++) {				
		  var strClass= pageMaker.cri.page == i?'class=active':'';
		  str += "<li "+strClass+"><a href='"+i+"'>"+i+"</a></li>";
	}
	
	if(pageMaker.next){
		str += "<li><a href='"+(pageMaker.endPage + 1)+"'> >> </a></li>";
	}
	$('.pageing').html(str);				
}



 $(".pageing").on("click", "li a", function(event){
	
	event.preventDefault();
	var page = $(this).attr("href") || 1;
	var bno = $("#bno").val();
	getPageList(bno, page);
	
});


</script>

</body>
</html>
