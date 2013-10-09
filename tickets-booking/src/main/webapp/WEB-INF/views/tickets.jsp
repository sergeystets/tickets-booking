<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
	<h2 align="center">Welcome to "Epam Cinema"</h2>
	<h3 align="center">Here you can book tickets</h3>
	<p>List of available tickets:</p>
	<c:choose>
		<c:when test="${not empty availableTickets}">
		
			<c:forEach var="ticket" items="${availableTickets}">
	 id: ${ticket.id},
	 title: ${ticket.title},
	 date: ${ticket.date},
	 category: ${ticket.category},
	 place: ${ticket.place}.	 
	 <br />
			</c:forEach>
			Get available tickets as JSON click <a href="availableTickets.json">here</a><br/>
			Get available tickets as PDF click <a href="availableTickets.pdf">here</a><br/><br/>
		</c:when>
		<c:otherwise>Sorry! No ticket are available.</c:otherwise>
	</c:choose>

 	<br />
	Tickets booked by: ${ticketsOwner} <br/>
	<c:forEach var="ticket" items="${bookedTickets}">
	 id: ${ticket.id},
	 title: ${ticket.title},
	 date: ${ticket.date},
	 category: ${ticket.category},
	 place: ${ticket.place}.	 
	 <br />
	</c:forEach>

</body>
</html>
