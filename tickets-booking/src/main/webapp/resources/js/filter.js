$(document).ready(function() {
	$("#filterForm").on("submit", function(event) {
		var category = $("#category").val();
		var date = $("#date").val();
		var title = $("#title").val();

		var tickets = new Array();
		tickets.push($("#ticketsAsJSP"));
		tickets.push($("#ticketsAsJSON"));
		tickets.push($("#ticketsAsPDF"));
		$.each(tickets, function(index, value) {
			var href = value.attr('href');
			if (href.indexOf("?") > -1) {
				href = href.substring(0, href.indexOf("?"));
			}
			href += "?category=" + category;
			if (date != undefined && date != ""){
				href+= "&date=" + date;
			}
			if (title != undefined && title != ""){
				href+= "&title=" + title;
			}			
			value.attr('href', href);
		});
		event.preventDefault();		
		return false;
	});

});
