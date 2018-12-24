<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.kh.jsp.board.model.vo.*"%>
<%
	Board b = (Board) request.getAttribute("b");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<title>Insert title here</title>
<style>
.outer {
	width: 800px;
	height: 500px;
	background: beige;
	margin-left: auto;
	margin-right: auto;
	margin-top: 50px;
}

td {
	border: 1px solid brown;
}

.tableArea {
	border: 1px solid brown;
	width: 800px;
	height: 350px;
	margin-left: auto;
	margin-right: auto;
}

#content {
	height: 230px;
}

.replyArea {
	width: 800px;
	color: white;
	background: brown;
	margin-left: auto;
	margin-right: auto;
}
</style>
</head>
<body>
	<%@include file="../common/menubar.jsp"%>
	<div class="outer">
		<br>
		<h2 align="center">게시판 상세보기</h2>
		<div class="tableArea">
			<table align="center">
				<tr>
					<td>분야</td>
					<td><span><%=b.getCategory()%></span></td>
					<td>제목</td>
					<td colspan="3"><span><%=b.getbTitle()%></span></td>
				</tr>

				<tr>
					<td>작성자</td>
					<td><span><%=b.getbWriter()%></span></td>
					<td>조회수</td>
					<td><span><%=b.getbCount()%></span></td>
					<td>작성일</td>
					<td><span><%=b.getbDate()%></span></td>
				</tr>

				<tr>
					<td colspan="6">내용</td>
				</tr>

				<tr>
					<td colspan="6">
						<p id="content"><%=b.getbContent()%></p>
					</td>
				</tr>
			</table>
			<br>
		</div>
		<div align="center">
			<button
				onclick="location.href='<%=request.getContextPath()%>/selectList.bo'">메뉴로
				돌아가기</button>
			<%
				if (loginUser != null && loginUser.getUserId().equals(b.getbWriter())) {
			%>
			<button>수정하기</button>
			<%
				}
			%>
		</div>

	</div>
	<div id="replyArea">
		<div class="replyWriterArea">
			<table align="center">
				<tr>
					<td>댓글 작성</td>
					<td><textarea row="3" cols="80" id="replyContent"></textarea></td>
					<td><button id="addReply">댓글 등록</button></td>
				</tr>
			</table>
		</div>
		<div id="replySelectArea">
			<table id="replySelectTable" border="1" align="center"></table>
		</div>
	</div>

	<script>
	$("#addReply").click(function(){
		var writer = <%=loginUser.getUno()%>;
		var bid = <%=b.getBid()%>;
		var content = $("#replyContent").val();
		
		$.ajax({
			url : "/jsp/insertReply.bo",
			data : {writer:writer, content:content, bid:bid},
			type : "post",
			success : function(data){
				console.log(data);
				
				var $replySelectTable = $("#replySelectTable");
				$replySelectTable.html('');
				
				for(var key in data){
					var $tr = $("<tr>");
					var $writerTd = $("<td>").text(data[key].bWriter).css("width", "100px");
					var $contentTd = $("<td>").text(data[key].bContent).css("width", "400px");
					var $dateTd = $("<td>").text(data[key].bDate).css("width", "200px");
					
					$tr.append($writerTd);
					$tr.append($contentTd);
					$tr.append($dateTd);
					$replySelectTable.append($tr);
				
				}
				
			},
			error : function(){
				console.log(실패);
			}
		});
	});
</script>

</body>
</html>