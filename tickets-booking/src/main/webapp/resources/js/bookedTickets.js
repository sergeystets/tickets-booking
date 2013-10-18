var baseQuery = "bookedTickets";
$(document).ready(function() {
	dataContainer = $("#bookedTickets");
	$("#filterForm").on("submit", getTickets);
});
