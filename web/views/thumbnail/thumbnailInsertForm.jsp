<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<title>Insert Form</title>
<style>
.outer {
	width: 1000px;
	height: 650px;
	background: beige;
	color: brown;
	margin-left: auto;
	margin-right: auto;
	margin-top: 50px;
}

.insertArea {
	width: 500px;
	height: 450px;
	margin-left: auto;
	margin-right: auto;
}

.btnArea {
	width: 150px;
	margin-left: auto;
	margin-right: auto;
}

#titleImgArea {
	width: 350px;
	height: 200px;
	border: 2px dashed brown;
	text-align: center;
	display: table-cell;
	vertical-align: middle;
}

#contentImgArea1, #contentImgArea2, #contentImgArea3 {
	width: 150px;
	height: 100px;
	border: 2px dashed brown;
	text-align: center;
	display: table-cell;
	vertical-align: middle;
}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp"%>
	<%
		if (loginUser != null) {
	%>


	<div class="outer">
		<br>
		<h2 align="center">사진 게시판 작성</h2>
		<!-- encType="multipart/form-data" : 파일이 있을 시 전송 타입 지정 -->
		<form action="<%=request.getContextPath()%>/insert.tn" method="post" encType="multipart/form-data">
			<div class="insertArea">
				<table align="center">
					<tr>
						<td>제목</td>
						<td colspan="3"><input type="text" size="45" name="title"></td>
					</tr>

					<tr>
						<td>대표 이미지</td>
						<td colspan="3">
							<div id="titleImgArea">
								<img id="titleImg" width="370" height="200">
							</div>
						</td>
					</tr>

					<tr>
						<td>사진</td>
						<td>
							<div id="contentImgArea1">
								<img id="contentImg1" width="120" height="100">
							</div>
						</td>

						<td>
							<div id="contentImgArea2">
								<img id="contentImg2" width="120" height="100">
							</div>
						</td>

						<td>
							<div id="contentImgArea3">
								<img id="contentImg3" width="120" height="100">
							</div>
						</td>
					</tr>

					<tr>
						<td width="100px">사진 메모</td>
						<td colspan="3"><textarea name="content" rows="5" cols="50" style="resize: none;"></textarea>
					</tr>
				</table>

				<div id="fileArea">
					<input type="file" id="thumbnailImg1" name="thumbnailImg1" onchange="loadImg(this, 1)">
					<input type="file" id="thumbnailImg2" name="thumbnailImg2" onchange="loadImg(this, 2)">
					<input type="file" id="thumbnailImg3" name="thumbnailImg3" onchange="loadImg(this, 3)">
					<input type="file" id="thumbnailImg4" name="thumbnailImg4" onchange="loadImg(this, 4)">
				</div>
			</div>

			<div class="btnArea">
				<button>취소</button>
				<button type="submit">작성완료</button>
			</div>
		</form>
	</div>
	
	
	<script>
		$(function(){
			$("#fileArea").hide();
			
			$("#titleImgArea").click(function(){
				$("#thumbnailImg1").click();
			});
			
			$("#contentImgArea1").click(function(){
				$("#thumbnailImg2").click();
			});
			
			$("#contentImgArea2").click(function(){
				$("#thumbnailImg3").click();
			});
			
			$("#contentImgArea3").click(function(){
				$("#thumbnailImg4").click();
			});
		});
		
	/* 	
		function loadImg1(value){
			if(value.files && value.files[0]){
				var reader = new FileReader();
				
				reader.onload = function(e){
					$("#titleImg").attr("src", e.target.result);
				}
				
				reader.readAsDataURL(value.files[0]);
				
			}
		} 
	*/
		
		
		function loadImg(value, num){
			if(value.files && value.files[0]){
				var reader = new FileReader();
				reader.onload = function(e){
					
					switch(num){
					case 1 : $("#titleImg").attr("src", e.target.result); 		 break;
					case 2 : $("#contentImg1").attr("src", e.target.result); 	 break;
					case 3 : $("#contentImg2").attr("src", e.target.result); 	 break;
					case 4 : $("#contentImg3").attr("src", e.target.result); 	 break;
					}
				}
				
				reader.readAsDataURL(value.files[0]);
			}
		}
	</script>


	<%
		} else {
			request.setAttribute("msg", "잘못된 경로로 접근하셨습니다.");
			request.getRequestDispatcher("../common/errorPage.jsp").forward(request, response);
		}
	%>
	
</body>
</html>