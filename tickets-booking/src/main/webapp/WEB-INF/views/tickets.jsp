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
<script type='text/javascript' src='js/filter.js'></script>
<script>
	$(function() {
		$("#date").datepicker();
	});
</script>
<body>
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
			<input type="submit" name="ok" value="ok">

			<p>List of available tickets:</p>
			Get available tickets as JSP click <a href="availableTickets"
				id="ticketsAsJSP">here</a><br /> Get available tickets as JSON
			click <a href="availableTickets.json" id="ticketsAsJSON">here</a><br />
			Get available tickets as PDF click <a href="availableTickets.pdf"
				id="ticketsAsPDF">here</a><br /> <br />
		</form>
	</div>
</body>
</html>