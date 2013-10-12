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
<script type='text/javascript' src='js/myTicketsFilter.js'></script>
<script type='text/javascript' src="js/book.js"></script>

<script>
	$(function() {
		$("#date").datepicker();
	});
</script>
<body>
	<div class="link">
		<a href="tickets">All tickets</a>
	</div>

	<h3 align="center">Your tickets</h3>


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

			<div id="ticketsFormatLogo">
				<a href="bookedTickets.json" id="ticketsAsJSON"><img
					src="img/json-logo.jpg"></a> <a href="bookedTickets.pdf"
					id="ticketsAsPDF"><img src="img/pdf-logo.jpg"></a>
			</div>
			<div id="bookedTickets">
				<%@ include file="/WEB-INF/views/bookedTickets.jsp"%>
			</div>
		</form>
	</div>
</body>
</html>