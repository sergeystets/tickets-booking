<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error page.</title>
</head>
<body>
	<h2>Code: 400</h2>
	<h2>Message: <c:out value="${requestScope['javax.servlet.error.message']}"/></h2>
</body>
</html>