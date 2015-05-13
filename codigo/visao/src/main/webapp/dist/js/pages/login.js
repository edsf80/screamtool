/**
 * Depente do jquery e jquery.validate
 */
$(function() {

	$("#frmLogin").validate({
		errorPlacement : function(error, element) {
			$(element).parent().addClass("has-error");
		},
		onkeyup: false,
		showErrors: function(errorMap, errorList) {
			$.each( errorList, function( i, val ) {
				$("#caixaAlerta").append(val.message + "<br>");
			});
			this.defaultShowErrors();
		},
		submitHandler : function(form) {
			var actionurl = form.action;
			var method = form.method;

			$.ajax({
				type : method,
				url : actionurl,
				datatype : "json",
				data : $(form).serialize()
			}).done(function(data) {
				if (data.objeto) {
					window.location.href = "private/main.html";
				} else {
					alert("Login ou senha invalida!");
				}
			}).fail(function(data) {
				alert("Erro na bagaca!");
			});
		},
		rules : {
			login : "required",
			senha : "required"
		},
		messages : {
			login : "O login é obrigatório",
			senha : "A senha é obrigatória"
		},
		invalidHandler : function(event, validator) {
			// 'this' refers to the form
			var errors = validator.numberOfInvalids();
			if (errors) {
				$("#caixaAlerta").show();
			} else {
				$("#caixaAlerta").hide();
			}
		}
	});
});