/**
 * Depente do jquery e jquery.validate
 */
$(function() {

	$("#frmLogin").validate({
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
					window.location.href = "private/main.htm";
				} else {
					$.fn.exibirCaixaAlerta(["Login ou senha inválida!"]);
				}
			}).fail($.fn.tratarErro);
		},
		rules : {
			login : "required",
			senha : "required"
		},
		messages : {
			login : "O login é obrigatório",
			senha : "A senha é obrigatória"
		}
	});
});