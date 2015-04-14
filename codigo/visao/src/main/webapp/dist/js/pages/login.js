/**
 * Depente do jquery e jquery.validate
 */
$(function() {

	$("#frmLogin").validate({
		submitHandler : function(form) {
			var actionurl = form.action;
			var method = form.method;
			// var actionurl = "http://demo3468794.mockable.io/login.rest";
			// var method = "post";

			$.ajax({
				type : method,
				url : actionurl,
				datatype : "json",
				data : $(form).serialize()
			}).done(function(data) {
				if (data) {
					window.location.href = "private/main.html";
				} else {
					// $.showWarningMessage("Login ou senha inválida!");
					alert("Login ou senha invalida!");
				}
			}).fail(function() {
				// $.showErrorMessage();
				alert("Erro na bagaca!");
			});
		},
		rules : {
			login : {
				required : true,
				email : true
			},
			senha : "required"
		},
		messages : {
			login : {
				required : "O login é obrigatório",
				email : "O formato de e-mail não é válido"
			},
			senha : "A senha é obrigatória"
		}
	});
});