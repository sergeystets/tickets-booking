$(document).ready(function() {

	$("#filterForm").on("submit", function(event) {
		onSubmit(event);
	});

});

function onSubmit(event) {
	var form = $("#filterForm");
	event.stopImmediatePropagation();

	var title = form.find("#title").val();
	var studio = form.find("#studio").val();
	var actors = form.find("#actors").val();
	title = title.trim();
	actors = actors.trim();
	studio = studio.trim();

	actors = actors.replace(/\s{2,}/g, ' ');
	var actorsAsList = new Array();

	if (actors.length > 0) {
		if (actors.indexOf(",") > 0) {
			actorsAsList = actors.split(",");
		} else {
			actorsAsList.push(actors);
		}
	}

	var url = "title=" + title + "&studio=" + studio + "&actors=" + JSON.stringify(actorsAsList);
	window.location.search = url;
	return false;
	
}


