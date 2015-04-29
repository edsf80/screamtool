/**
 * Depente do jquery e jquery.validate
 */
$(function() {

	$("#frmRegistro").validate({
		submitHandler : function(form) {
			var actionurl = form.action;
			var method = form.method;

			if (!$("#acordoTermos").is(':checked')) {
				alert("É necessário aceitar os termos para registro.");
				return false;
			}

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
			login : "required",
			senha : "required",
			confirmacaoSenha : {
				required : true,
				equalTo : "#senha"
			}
		},
		messages : {
			nome : "O nome é obrigatório",
			login : "O login é obrigatório",
			senha : "A senha é obrigatória",
			confirmacaoSenha : {
				required : "A confirmação da senha é obrigatória",
				equalTo : "A confirmação não está igual a senha"
			}
		},
		invalidHandler: function(event, validator) {
		    // 'this' refers to the form
		    var errors = validator.numberOfInvalids();
		    if (errors) {
		      var message = errors == 1
		        ? 'You missed 1 field. It has been highlighted'
		        : 'You missed ' + errors + ' fields. They have been highlighted';
		      $("#caixaAlerta").html(message);
		      $("#caixaAlerta").show();
		    } else {
		      $("#caixaAlerta").hide();
		    }
		  }
	});
});