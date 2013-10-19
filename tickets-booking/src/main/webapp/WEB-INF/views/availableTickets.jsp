<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<link rel="stylesheet" href="style/style.css" />
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script type='text/javascript' src='js/jquery/jquery-1.9.1.js'></script>
<script type='text/javascript' src='js/jquery/jquery-ui-1.10.3.js'></script>
<script type='text/javascript' src='js/ticketsFilter.js'></script>
<script type='text/javascript' src="js/book.js"></script>

<script>
	$(function() {
		$("#date").datepicker();
		var resultContainer = $("#availableTicketsContent");
		var baseUrl = "availableTicketsContent";
		var form = $("#filterForm");
		var filter = new TicketsFilter(resultContainer, baseUrl, form);
	});
</script>
<body>
	<div class="link">
		<a href="bookedTickets">My tickets</a>
	</div>
	<div  id="logout">
		<a href="logout">Logout</a>
	</div>

	<h2 align="center">Welcome to "Epam Cinema"</h2>
	<h3 align="center">Here you can book tickets</h3>


	<div id="filter">
		<p>Filter</p>
		<form id="filterForm">
			<label>category</label> <select name="category" id="category">
				<option></option>
				<option>STANDARD</option>
				<option>PREMIUM</option>
				<option>BAR</option>
			</select> <label>title</label><input type="text" name="title" id="title">
			<label>date</label><input type="text" id="date" name="date">
			<input type="submit" name="ok" value="ok" id="ok">
		</form>
		<div id="ticketsFormatLogo">
			<a href="availableTicketsContent.json" id="ticketsAsJsonLink"><img
				src="img/json-logo.jpg"></a> <a href="availableTicketsContent.pdf"
				id="ticketsAsPdfLink"><img src="img/pdf-logo.jpg"></a>
		</div>
		<div id="availableTicketsContent">
			<%@ include file="/WEB-INF/views/availableTicketsContent.jsp"%>
		</div>

	</div>
</body>
</html>