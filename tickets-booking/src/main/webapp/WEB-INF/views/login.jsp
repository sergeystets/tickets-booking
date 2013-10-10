<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>

<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" href="style/style.css" />
<body>
	<h2 align="center">Welcome to "Epam Cinema"</h2>
	<div id="login">
		<img id="logo" src="img/cinema-logo.jpg">
		<h3>Login here</h3>
		<form method="POST" action="login">
			<div id="login">
				<input type="text" name="login" required="required" />
			</div>
			<div id="password">
				<input type="password" name=password required="required" /> <input
					type="submit" name="submit" value="login">
			</div>
		</form>
		<p id="registerDialog">
			Don not have account? <a href="registration">Register</a>
		</p>
		<p class="errorMessage">
			<c:if test="${not empty errorMessage}">
		${errorMessage}
		</c:if>
		</p>
	</div>
</body>
</html>
