<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type='text/javascript' src='js/jquery/jquery-1.9.1.js'></script>
<script type='text/javascript' src='js/jquery/jquery-ui-1.10.3.js'></script>
<script type='text/javascript' src="js/registration.js"></script>
<body>
	<h2>Welcome to "Epam Cinema"</h2>
	<h3>Login here</h3>
	<form id="registrationForm" method="POST" action="register">
		<label>login</label><input type="text" name="login" id="login" />
		<div id="login_error">&nbsp</div>
		<label>password</label><input type="password" name=password id="password" />
		<div id="password_error">&nbsp</div>
		<label>repeat password</label><input type="password" name="passwordRepeat" id="passwordRepeat" />
		<div id="passwordRepeat_error">&nbsp</div>
		<input type="submit" id="submit" value="register">
		<c:if test="${not empty errors}">
		<c:forEach var="error" items="${errors}">
		${error} <br/>
		</c:forEach>
		</c:if>
	</form>
</body>
</html>
