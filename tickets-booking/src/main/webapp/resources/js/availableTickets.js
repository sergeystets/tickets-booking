var baseQuery = "availableTickets";
$(document).ready(function() {
	dataContainer = $("#availableTickets");
	$("#filterForm").on("submit", getTickets);
});

