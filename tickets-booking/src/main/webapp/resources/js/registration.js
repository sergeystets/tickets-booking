$(document).ready(function() {
	var registrationForm = new RegistrationForm($("#registrationForm"));
});

var RegistrationForm = function(registrationForm) {
	var form = registrationForm;
	var submitButton = form.find("#submit");
	var passwordMinLength = 3;
	var passwordMaxLength = 25;
	loginMinLength = 3;
	loginMaxLength = 30;
	var formCorrect = false;

	var fields = {
		login : form.find("#login"),
		password : form.find("#password"),
		passwordRepeat : form.find("#passwordRepeat")
	};

	var showError = function(container, message) {
		formCorrect = false;
		container.html(message);
	};

	var clearError = function(container) {
		container.html("&nbsp");
	};

	var errorContainer = new Array();
	for ( var key in fields) {
		errorContainer[key] = $("#" + key + "_error");
	}

	var validatePassword = function() {
		var pass = this.value.trim();
		if (pass.length < passwordMinLength || pass.length > passwordMaxLength) {
			showError(errorContainer["password"], "password should be from 3 to 25 symbols");
			return;
		} else {
			clearError(errorContainer["password"]);
		}
	};

	var validatePasswordRepeat = function() {
		var pass = fields["password"].val();
		var passRepeat = this.value;
		if (pass !== passRepeat) {
			showError(errorContainer["passwordRepeat"], "passwords do not match");
		} else {
			clearError(errorContainer["passwordRepeat"]);
		}
	};

	var validateLogin = function() {
		if (this.value.length < loginMinLength || this.value.length > loginMaxLength) {
			showError(errorContainer["login"], "login should be from 3 to 30 symbols");
			return;
		}

		$.ajax({
			data : "login=" + this.value,
			type : "POST",
			url : "registration/checkLogin.json",
			dataType : "json",
			success : function(data) {				
				if (data.controllerResponse.error == true) {
					showError(errorContainer["login"], data.controllerResponse.message);
				} else {
					clearError(errorContainer["login"]);
				}
			},
			error : function() {
				showError(errorContainer["login"], "Unexpected error happend. Try again later");
			}
		});
	};

	var submit = function() {
		formCorrect = true;
		for ( var key in fields) {
			fields[key].trigger("change");
		}

		return formCorrect;
	};

	var registerEventListeners = function() {
		fields["password"].on("change", validatePassword);
		fields["passwordRepeat"].on("change", validatePasswordRepeat);
		fields["login"].on("change", validateLogin);
		submitButton.on("click", submit);
	}();

};