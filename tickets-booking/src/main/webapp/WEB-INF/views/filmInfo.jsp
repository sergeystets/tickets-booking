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


<body>
	<div class="link">
		<a href="bookedTickets">My tickets</a>
	</div>
	<div class="link">
		<a href="availableTickets">All tickets</a>
	</div>
	<div id="logout">
		<a href="logout">Logout</a>
	</div>

	<h3 align="center">Film info</h3>
	<div id="filter">
		<p>Filter</p>
		<form id="filterForm">
			<label>title</label> <input type="text" id="title" name="title" /> <label>studio</label><input
				type="text" name="studio" id="studio"> <label>actors</label><input
				type="text" id="actors" name="actors"
				placeholder="use ',' to split several actors">
			<input type="submit" name="ok" value="ok" id="ok">
		</form>

		<c:choose>
			<c:when test="${not empty filmInfo}">

				<p>List of available films:</p>
				<table id="filmInfo">
					<tr id="filmInfoHeader">
						<th>Title</th>
						<th>Studio</th>
						<th>Actors</th>
						<th>Description</th>
					</tr>
					<c:forEach var="filmInfoRecord" items="${filmInfo}">
						<tr>
							<td>${filmInfoRecord.title}</td>
							<td>${filmInfoRecord.studio}</td>
							<td><c:forEach var="actor" items="${filmInfoRecord.actors}">
								${actor},	
						</c:forEach></td>
						<td id="filmDescription">${filmInfoRecord.description}</td>							
						</tr>
					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
				<p id="noTicketsMessage">Sorry! No films.</p>
			</c:otherwise>
		</c:choose>



	</div>
</body>
</html>