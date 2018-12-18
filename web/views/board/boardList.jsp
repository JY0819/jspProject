<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, com.kh.jsp.board.model.vo.*"%>
<%
	ArrayList<Board> list = (ArrayList<Board>) request.getAttribute("list");
	PageInfo pi = (PageInfo) request.getAttribute("pi");
	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOARD LIST</title>
<style>
.outer {
	width: 900px;
	height: 500px;
	background: beige;
	color: brown;
	margin-left: auto;
	margin-right: auto;
	margin-top: auto;
}

table {
	border: 1px solid brown;
	text-align: center;
}

.tableArea {
	width: 650px;
	height: 350px;
	margin-left: auto;
	margin-right: auto;
}

.searchArea {
	width: 650px;
	margin-left: auto;
	margin-right: auto;
}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp"%>

	<div class="outer">
		<br>
		<h2 align="center">게시판</h2>
		<div class="tableArea">
			<table align="center" id="listArea">
				<tr>
					<th>글번호</th>
					<th>카테고리</th>
					<th>제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>작성일</th>
				</tr>
				<%
					for (Board b : list) {
				%>
				<tr>
					<input type="hidden" value="<%=b.getBid()%>">
					<td><%=b.getBno()%></td>
					<td><%=b.getCategory()%></td>
					<td><%=b.getbTitle()%></td>
					<td><%=b.getbWriter()%></td>
					<td><%=b.getbCount()%></td>
					<td><%=b.getbDate()%></td>
				</tr>
				<%
					}
				%>
			</table>
		</div>

		<div class="pagingArea" align="center">
			<button
				onclick="location.href='<%=request.getContextPath()%>/selectList.bo?currentPage=1'"><<</button>


			<%
				if (currentPage <= 1) {
			%>
			<button disabled><</button>
			<%
				} else {
			%>
			<button
				onclick="location.href='<%=request.getContextPath()%>/selectList.bo?currentPage=<%=currentPage - 1%>'"><</button>
			<%
				}
			%>


			<%
				for (int p = startPage; p <= endPage; p++) {

					if (p == currentPage) {
			%>
					<button disabled><%= p %></button>
			<%
					} else {
			%>
					<button onclick="location.href='<%=request.getContextPath()%>/selectList.bo?currentPage=<%= p %>'"><%= p %></button>
			<%
					}
			%>

			<%
				}
			%>


			<%
				if (currentPage >= maxPage) {
			%>
			<button disabled>></button>
			<%
				} else {
			%>
			<button onclick="location.href='<%=request.getContextPath()%>/selectList.bo?currentPage=<%=currentPage + 1%>'">></button>
			<%
				}
			%>

			<button onclick="location.href='<%=request.getContextPath()%>/selectList.bo?currentPage=<%=maxPage%>'">>></button>

		</div>

		<div class="searchArea" align="center">
			<select id="searchCondition" name="searchCondition">
				<option value="category">카테고리</option>
				<option value="writer">작성자</option>
				<option value="title">제목</option>
				<option value="content">내용</option>
			</select> <input type="search">
			<button type="submit">검색</button>
			<%
				if (loginUser != null) {
			%>
			<button onclick="location.href='views/board/boardInsertForm.jsp'">작성하기</button>
			<%
				}
			%>
		</div>

	</div>

</body>
</html>

