<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>THUMBNAIL</title>
<style>
.outer {
	width: 1000px;
	height: 700px;
	background: beige;
	color: brown;
	margin-left: auto;
	margin-right: auto;
	margin-top: auto;
}

.thumbnailArea {
	width: 760px;
	height: 550px;
	margin-left: auto;
	margin-right: auto;
}

.searchArea {
	width: 420px;
	margin-left: auto;
	margin-right: auto;
}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp"%>

	<div class="outer">
		<br>
		<h2 align="center">사진 게시판</h2>
		<div class="thumbnailArea"></div>

		<div class="searchArea">
			<select id="searchCondition" name="searchCondition">
				<option value="writer">작성자</option>
				<option value="title">제목</option>
				<option value="content">내용</option>
			</select>
			<input type="search">
			<button type="submit">검색하기</button>

			<%
				if (loginUser != null) {
			%>
			<button
				onclick="location.href='/jsp/views/thumbnail/thumbnailInsertForm.jsp'">작성하기</button>
			<%
				}
			%>

		</div>
	</div>

</body>
</html>