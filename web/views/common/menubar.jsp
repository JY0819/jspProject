<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.kh.jsp.member.model.vo.Member"%>
<%
	Member loginUser = (Member) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<title>MENU BAR</title>
<style>
body {
	background: url("/jsp/image/city1.PNG") no-repeat;
	background-size: cover;
}

.wrap {
	background: beige;
	width: 100%;
	height: 50px;
}

.menu {
	background: beige;
	color: #2A1B0A;
	text-align: center;
	display: table-cell;
	vertical-align: middle;
	width: 150px;
	height: 50px;
}

.nav {
	width: 600px;
	margin-left: auto;
	margin-right: auto;
}

.menu:hover {
	background: #2A1B0A;
	color: beige;
	font-weight: bold;
	cursor: pointer;
}

.btns {
	align: center;
}

#loginBtn, #memberJoinBtn, #logoutBtn, #changeInfo {
	display: inline-block;
	text-align: center;
	background: beige;
	color: #2A1B0A;
	height: 25px;
	width: 100px;
	border-radius: 5px;
}

#memberJoinBtn, #changeInfo {
	background: lightblue;
}

#loginBtn:hover, #changeInfo:hover, #logoutBtn:hover, #memberJoinBtn:hover
	{
	cursor: pointer;
}

.loginArea>form, #userInfo {
	float: right;
}
</style>
</head>
<body>
	<h1 align="center" style="color: #3B240B">Welcome JSP World!</h1>


	<div class="loginArea">

		<%
			if (loginUser == null) {
		%>

		<form id="loginForm" action="<%=request.getContextPath()%>/login.me"
			method="post">
			<table>
				<tr>
					<td><label class="text">ID : </label></td>
					<td><input type="text" name="userId"></td>
				</tr>

				<tr>
					<td><label class="text">PWD : </label></td>
					<td><input type="password" name="userPwd"></td>
				</tr>
			</table>
			<div class="btns" align="center">
				<div id="memberJoinBtn" onclick="memberJoin();">JOIN</div>
				<div id="loginBtn" onclick="login();">LOGIN</div>
			</div>
		</form>

		<%
			} else {
		%>

		<div id="userInfo">
			<label><%=loginUser.getNickName()%>님 환영합니다.</label>
			<div class="btns" align="right">
				<div id="changeInfo" onclick="changeInfo();">CHANGE INFO</div>
				<div id="logoutBtn" onclick="logout();">LOGOUT</div>
			</div>
		</div>

		<%
			}
		%>

	</div>

	<script>
		function login() {
			$("#loginForm").submit();
		}

		function logout() {
			location.href="<%=request.getContextPath()%>/logout.me";
		}

		function memberJoin() {
			location.href = "/jsp/views/member/memberJoinForm.jsp";
		}

		function changeInfo() {

		}
	</script>

	<br clear="both">
	<br>

	<div class="wrap">
		<div class="nav">
			<div class="menu" onclick="goHome()">HOME</div>
			<div class="menu" onclick="goNotice();">NOTICE</div>
			<div class="menu" onclick="goBoard();">BOARD</div>
			<div class="menu" onclick="goThumbnail();">GALLERY</div>
		</div>
	</div>

	<script>
		function goHome() {
			location.href="/jsp/index.jsp";
		}

		function goNotice() {
			location.href="/jsp/selectList.no";
		}

		function goBoard() {

		}

		function goThumbnail() {

		}
	</script>
</body>
</html>
