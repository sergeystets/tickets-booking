<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" href="style/style.css" />

<script type='text/javascript' src='js/jquery/jquery-1.9.1.js'></script>
<script type='text/javascript' src='js/jquery/jquery-ui-1.10.3.js'></script>
<script type='text/javascript' src="js/registration.js"></script>


<body>	
<div class="link">
		<a href="login">Login</a>
	</div>
	<div id="registrationFormWrapper">
	<h2 align="center">Registration</h2>
		<form id="registrationForm" method="POST" action="register">
			<div id="regLogin">
				<input type="text" name="login" id="login" placeholder="login" />
				<div id="login_error" class="errorMessage">&nbsp</div>
			</div>
			<div id="regPassword">
				<input type="password" name=password id="password"
					placeholder="password" />
				<div id="password_error" class="errorMessage">&nbsp</div>
			</div>
			<div id="regPasswordRepeat">
				<input type="password" name="passwordRepeat" id="passwordRepeat"
					placeholder="repeat password" />
				<div id="passwordRepeat_error" class="errorMessage">&nbsp</div>
			</div>
			<input type="submit" id="submit" value="register">
			<div class="errorMessage">
				<c:if test="${not empty errors}">
					<c:forEach var="error" items="${errors}">
		${error} <br />
					</c:forEach>
				</c:if>
			</div>
		</form>
	</div>
</body>
</html>
