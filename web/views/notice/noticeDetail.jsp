<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.kh.jsp.notice.model.vo.*"%>
<%
	Notice n = (Notice) request.getAttribute("n");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 상세보기</title>
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
	<%@ include file="../common/menubar.jsp"%>

	<div class="outer">
		<br>
		<h2 align="center">공지 사항 내용</h2>

		<div class="tableArea">
			<table>
				<tr>
					<td>제목</td>
					<td colspan="3"><input type="text" size="50" name="title"
						value="<%=n.getnTitle()%>" readonly></td>
				</tr>

				<tr>
					<td>작성자</td>
					<td><input type="text" value="<%=n.getnWriter()%>"
						name="writer" readonly></td>
					<td>작성일</td>
					<td><input type="date" name="date" value="<%=n.getnDate()%> readonly"></td>
				</tr>

				<tr>
					<td>내용</td>
				</tr>

				<tr>
					<td colspan="4"><textarea name="content" cols="60" rows="15"
							style="resize: none;" readonly><%=n.getnContent()%></textarea></td>
				</tr>

			</table>
			<br>
			<div align="center">
				<button onclick="location.href='<%=request.getContextPath()%>/selectList.no'">목록으로 다시 돌아가기</button>
				<% if(loginUser != null && loginUser.getUserId().equals("admin")){ %>
				<button onclick="location.href='<%=request.getContextPath()%>/selectNotice.no?num=<%=n.getNno()%>'">수정하기</button>
				<% } %>
			</div>
		</div>
	</div>
</body>
</html>