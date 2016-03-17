$(function() {
	jQuery.validator.setDefaults({
		errorPlacement : function(error, element) {
			$(element).closest(".form-group").addClass("has-error");
		},
		unhighlight : function(element, errorClass, validClass) {
			$(element).closest(".form-group").removeClass("has-error");
			$("#caixaAlerta").hide();
		},
		onkeyup : false,
		onfocusout : false,
		showErrors : function(errorMap, errorList) {
			var temErro = false;
			this.defaultShowErrors();
			$("#caixaAlerta p").empty();
			$.each(errorList, function(i, val) {
				$("#caixaAlerta").append("<p>" + val.message + "</p>");
				temErro = true;
			});
			if (temErro) {
				$("#caixaAlerta").show();
			}
		}
	});

	$.fn.abrirModal = function(tela, titulo, dados) {
		$("#caixaAlerta").hide();
		$(tela).find('.modal-title').text(titulo);
		$("form[role='form']")[0].reset();
		$(".has-error").removeClass("has-error");

		if (dados !== undefined) {
			for ( var name in dados) {
				$("input[name='" + name + "']").val(dados[name]);
			}
		}

		$('#hIdTeste').focus();

		$(tela).modal('toggle');
	}

	$.fn.exibirCaixaAlerta = function(mensagens) {
		$("#caixaAlerta p").empty();
		$.each(mensagens, function(i, val) {
			$("#caixaAlerta").append("<p>" + val + "</p>");
		});
		$("#caixaAlerta").show();
	}

	$.fn.tratarErro = function(data) {
		$(".overlay").hide();
		if (data.status == 403) {
			$.fn.exibirCaixaAlerta([ "Usuário não possui permissão para executar operação!" ]);
		} else if (data.status == 404) {
			$.fn.exibirCaixaAlerta(data.responseJSON.objeto.errorMessages);
		} else {
			//window.location.href = "../erro.htm";
		}
	}
});
