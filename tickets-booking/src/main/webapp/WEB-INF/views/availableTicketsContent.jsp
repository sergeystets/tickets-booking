<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:choose>
	<c:when test="${not empty availableTicketsContent}">

		<p>List of available tickets:</p>
		<table id="availableTicketsTable">

			<tr>
				<th>Title</th>
				<th>Date</th>
				<th>Category</th>
				<th>Place</th>
			</tr>

			<c:forEach var="ticket" items="${availableTicketsContent}">
				<tr id = "tr${ticket.id}">
					<td>${ticket.title}</td>
					<td>${ticket.date}</td>
					<td>${ticket.category}</td>
					<td>${ticket.place}</td>
					<td><input type="button" id="butt-${ticket.id}" value="book" onclick="book(event)"><input
						type="hidden" value="${ticket.id}"></td>
				</tr>
			</c:forEach>
		</table>
	</c:when>
	<c:otherwise>
		<p id="noTicketsMessage">Sorry! No ticket are available.</p>
	</c:otherwise>
</c:choose>


