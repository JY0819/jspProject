<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*, com.kh.jsp.board.model.vo.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
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
	<jsp:include page="../common/menubar.jsp"/>

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
				
				<c:forEach var="b" items="${list}">
				<tr>
					<input type="hidden" value="${b.bid}">
					<td>${b.bno}</td>
					<td>${b.category}</td>
					<td>${b.bTitle}</td>
					<td>${b.bWriter}</td>
					<td>${b.bCount}</td>
					<td>${b.bDate}</td>
				</tr>
				</c:forEach>
				
			</table>
		</div>

		<div class="pagingArea" align="center">
			<button onclick="location.href='${pageContext.request.contextPath}/selectList.bo?currentPage=1'"><<</button>


			<c:if test="${pi.currentPage <=1}">
			<button disabled><</button>
			</c:if>
			
			<c:if test="${pi.currentPage >1 }">
			<button onclick="location.href='${pageContext.request.contextPath}/selectList.bo?currentPage=${pi.currentPage-1 }'"><</button>
			</c:if>
			
			
			<c:forEach var="p" begin="${pi.startPage}" end="${pi.endPage}" step="1">
				<c:if test="${p == pi.currentPage }">
					<button disabled>${p}</button>
				</c:if>
				<c:if test="${p != pi.currentPage }">
					<button onclick="location.href='${pageContext.request.contextPath}/selectList.bo?currentPage=${p}'">${p}</button>
				</c:if>
			</c:forEach>
			
					
			<c:if test="${pi.currentPage >= pi.maxPage }">
				<button disable>></button>
			</c:if>
			<c:if test="${pi.currentPage < pi.maxPage }">
				<button onclick="location.href='${pageContext.request.contextPath}/selectList.bo?currentPage=${pi.currentPage+1 }'">></button>
			</c:if>

			<button onclick="location.href='${pageContext.request.contextPath}/selectList.bo?currentPage=${pi.maxPage}'">>></button>

		</div>

		<div class="searchArea" align="center">
			<select id="searchCondition" name="searchCondition">
				<option value="category">카테고리</option>
				<option value="writer">작성자</option>
				<option value="title">제목</option>
				<option value="content">내용</option>
			</select> <input type="search">
			<button type="submit">검색</button>
			<c:if test="${loginUser != null}">
			<button onclick="location.href='views/board/boardInsertForm.jsp'">작성하기</button>
			</c:if>
		</div>

	</div>
	
	<script>
		$(function(){
			$("#listArea td").mouseenter(function(){
				$(this).parent().css({"background":"brown", "color":"white", "cursor":"pointer"});
			}).mouseout(function(){
				$(this).parent().css({"background":"beige","color":"brown"});
			}).click(function(){
				var num = $(this).parent().children("input").val();
				
				location.href="${pageContext.request.contextPath}/selectOne.bo?num=" + num;
			});
		});
	</script>

</body>
</html>




















