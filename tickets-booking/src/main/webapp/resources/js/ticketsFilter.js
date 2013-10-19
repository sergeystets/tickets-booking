var TicketsFilter = function(resultContainer, baseUrl) {

	var registerEventListeners = function() {
		$("#filterForm").on("submit", function(event) {
			getFilteredTickets(event);
		});
	}();

	var getFilteredTickets = function(event) {
		event.preventDefault();
		var category = $("#category").val();
		var date = $("#date").val();
		var title = $("#title").val();
		var tickets = new Array();
		tickets.push($("#ticketsAsJSON"));
		tickets.push($("#ticketsAsPDF"));

		var queryCondition = "";
		$.each(tickets, function(index, value) {
			var href = value.attr('href');
			if (href.indexOf("?") > -1) {
				href = href.substring(0, href.indexOf("?"));
			}
			var condition = "?category=" + category;
			if (date != undefined && date != "") {
				condition += "&date=" + date;
			}
			if (title != undefined && title != "") {
				condition += "&title=" + title;
			}
			href += condition;
			queryCondition = condition;
			value.attr('href', href, resultContainer);
		});

		$.ajax({
			type : "GET",
			url : baseUrl + queryCondition,
			success : function(data) {
				resultContainer.html(data);
			},
			error : function(response, xhr) {
				alert("Unexpected error happend. Try again later");
			}
		});

		return false;
	};
};
