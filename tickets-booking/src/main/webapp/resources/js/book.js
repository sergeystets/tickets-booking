function book(event) {
	var butt = $(event.target);
	var id = butt.attr('id');
	var ticketId = id.substring(5, id.length);

	$.ajax({
		type : "GET",
		url : "book?ticketId=" + ticketId,
		success : function(data) {
			$('#tr' + ticketId).delay(500).fadeOut(400);
		},
		error : function(response, xhr) {
			alert("Unexpected error happend. Try again later");
		}
	});
}