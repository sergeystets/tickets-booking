<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>

<html>
<body>
	<h2>Welcome to "Epam Cinema"</h2>
	<h3>Login here</h3>
	<form method="POST" action="login">
		<input type="text" name="login" required="required"/> <input type="password"
			name=password  required="required"/> <input type="submit" name="submit" value="login">
	</form>
	<p>
		Don not have account? <a href="registration">Register</a>
	</p>
</body>
</html>
