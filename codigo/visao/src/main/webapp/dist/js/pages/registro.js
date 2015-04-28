/**
 * Depente do jquery e jquery.validate
 */
$(function() {

	$("#frmRegistro").validate({
		submitHandler : function(form) {
			var actionurl = form.action;
			var method = form.method;
			
			$.ajax({
				type : method,
				url : actionurl,
				datatype : "json",
				data : $(form).serialize()
			}).done(function(data) {
				if (data) {
					window.location.href = "login.html";
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
			nome : "required",
			login: "required",
			senha : "required"
		},
		messages : {
			nome : "O nome é obrigatório",
			login: "O login é obrigatório",
			senha : "A senha é obrigatória"
		}
	});
});