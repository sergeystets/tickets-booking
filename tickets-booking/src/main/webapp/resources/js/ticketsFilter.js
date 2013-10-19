var TicketsFilter = function(resultContainer, baseUrl) {

	var registerEventListeners = function() {
		$("#filterForm").on("submit", function(event) {
			onSubmit(event);
		});
	};

	registerEventListeners();

	var onSubmit = function(event) {
		event.preventDefault();

		var category = $("#category").val();
		var date = $("#date").val();
		var title = $("#title").val();
		var links = new Array();
		var jsonLink = {
			ext : ".json",
			container : $("#ticketsAsJsonLink")
		};
		var pdfLink = {
			ext : ".pdf",
			container : $("#ticketsAsPdfLink")
		};
		links.push(jsonLink);
		links.push(pdfLink);

		var condition = buildCondition(date, title, category);
		appendConditionToLinks(links, condition);
		getTicketsFromServer(baseUrl + condition);

		return false;
	};

	var getTicketsFromServer = function getTicketsFromServer(url) {
		$.ajax({
			type : "GET",
			url : url,
			success : function(data) {
				resultContainer.html(data);
			},
			error : function(response, xhr) {
				alert("Unexpected error happend. Try again later");
			}
		});
	};

	var appendConditionToLinks = function(tickets, condition) {
		$.each(tickets, function(index, value) {
			var urlWithCondition = baseUrl;
			urlWithCondition += value.ext + condition;
			value.container.attr('href', urlWithCondition);
		});
	};

	var buildCondition = function(date, title, category) {
		var condition = "?";
		if (category != undefined && category != "") {
			condition += "&category=" + category;
		}
		if (date != undefined && date != "") {
			condition += "&date=" + date;
		}
		if (title != undefined && title != "") {
			condition += "&title=" + title;
		}
		return (condition.length == 1) ? "" : condition;
	};
};
