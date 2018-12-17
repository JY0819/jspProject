<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.jsp.notice.model.vo.*"%>
<% Notice n = (Notice)request.getAttribute("n"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.outer {
	width: 800px;
	height: 500px;
	background: beige;
	margin-left: auto;
	margin-right: auto;
	margin-top: auto;
}

table {
	border: 1px solid brown;
}

.tableArea {
	width: 450px;
	height: 350px;
	margin-left: auto;
	margin-right: auto;
}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	<% if(loginUser != null && loginUser.getUserId().equals("admin")){ %>
	<div class="outer">
		<br>
		<h2 align="center">공지 사항 수정 하기</h2>
		
		<div class="tableArea">
			<form id="updateForm" method="post">
			<table>
				<tr>
					<td>제목</td>
					<td colspan="3">
						<input type="text" size="50" 
									name="title" value="<%=n.getnTitle()%>">
						<input type="hidden" name="nno" value="<%=n.getNno()%>">			
					</td>
				</tr>
				<tr>
					<td>작성자</td>
					<td><input type="text" value="<%=n.getnWriter()%>" name="writer" readonly></td>
					<td>작성일</td>
					<td><input type="date" name="date" value="<%=n.getnDate()%>"></td>
				</tr>
				<tr>
					<td>내용</td>
				</tr>
				<tr>
					<td colspan="4">
						<textarea name="content" cols="60" rows="15" style="resize:none;"><%=n.getnContent() %></textarea>
					</td>
				</tr>
				
			</table>
			<br>
			<div align="center">
				<button onclick="complete()">작성완료</button>
				<button onclick="deleteNotice()">삭제하기</button>
			</div>
			
			<script type="text/javascript">
				function complete(){
					$("#updateForm").attr("action", "<%=request.getContextPath()%>/update.no");
				}
				function deleteNotice(){
					$("#updateForm").attr("action", "<%=request.getContextPath()%>/deleteNotice.no");
				}
			</script>
			</form>
		</div>
		
	</div>
	
	
	
	<% }else{
		request.setAttribute("msg", "잘못된 경로로 접근하셨습니다.");
		request.getRequestDispatcher("../common/errorPage.jsp").forward(request, response);
	} %>
	
	
</body>
</html>
