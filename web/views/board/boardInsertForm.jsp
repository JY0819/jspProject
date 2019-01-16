<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>INSERT FORM</title>
<style>
.outer {
	width: 900px;
	height: 500px;
	background: beige;
	color: #2A1B0A;
	margin-left: auto;
	margin-right: auto;
	margin-top: auto;
}

table {
	border: 1px solid brown;
}

.tableArea {
	width: 500px;
	height: 350px;
	margin-left: auto;
	margin-right: auto;
}
</style>
</head>
<body>
	<jsp:include page="../common/menubar.jsp"/>
	<c:if test="${loginUser != null}">
	
		<div class="outer">
			<br>
			<h2 align="center">게시판 작성</h2>
			<div class="tableArea">
				<form action="${pageContext.request.contextPath}/insert.bo" method="post">
					<table>
						<tr>
							<td>분야</td>
							<td><select name="category">
									<option value="10">공통</option>
									<option value="20">운동</option>
									<option value="30">등산</option>
									<option value="40">게임</option>
									<option value="50">낚시</option>
									<option value="60">요리</option>
									<option value="70">기타</option>
							</select></td>
						</tr>
	
						<tr>
							<td>제목</td>
							<td colspan="3"><input type="text" size="60" name="title">
							</td>
						</tr>
	
						<tr>
							<td>내용</td>
							<td><textarea name="content" cols="60" rows="" style="resize: none;"></textarea></td>
						</tr>
					</table>
					<br>
	
					<div align="center">
						<button type="reset">취소하기</button>
						<button type="submit">등록하기</button>
					</div>
				</form>
			</div>
	
		</div>
	
	</c:if>
	
	<c:if test="${loginUser == null}">
		request.setAttribute("msg", "잘못된 경로로 접근하셨습니다.");
		<jsp:forward page="../common/errorPage.jsp"/>
	</c:if>
	
</body>
</html>
