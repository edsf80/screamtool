/**
 * Depente do jquery e jquery.validate
 */
$(function() {

	$("#frmRegistro").validate({
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
				if (data.objeto) {
					window.location.href = "login.html";
				} else {
					alert("Login ou senha invalida!");
				}
			}).fail(function(data) {
				console.log('print object: ' + JSON.stringify(data));
				if(data.status == 404) {
					$("#caixaAlerta").append(data.responseJSON.objeto.errorMessages);
					$("#caixaAlerta").show();					
				} else {
					alert("Pau de selfie");
				}
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