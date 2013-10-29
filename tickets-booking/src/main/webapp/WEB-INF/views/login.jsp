<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>

<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" href="style/style.css" />
<body>
	<div id="loginPageWrapper">
		<div id="logo">
			<img src="img/epam-cinema.jpg">
		</div>
		<div id="loginForm">
			<div id="popCorn">
				<img src="img/popCorn.jpg">
			</div>
			<form method="POST" action="login">
				<div id="login">
					<input type="text" name="login" required="required"
						placeholder="login" />
				</div>
				<div id="password">
					<input type="password" name=password required="required"
						placeholder="password" />
				</div>
				<input type="submit" name="submit" value="login">
			</form>
			<p id="registrationLink">
				Do not have an account? <a href="registration">Register</a>
			</p>
			<p class="errorMessage">
				<c:if test="${not empty errorMessage}">
		${errorMessage}
		</c:if>
			</p>
		</div>
	</div>
</body>
</html>
