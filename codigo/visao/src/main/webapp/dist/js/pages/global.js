$(function() {
	jQuery.validator.setDefaults({
		errorPlacement : function(error, element) {
			$(element).closest(".form-group").addClass("has-error");
		},
		unhighlight : function(element, errorClass, validClass) {
			$(element).closest(".form-group").removeClass("has-error");
			$(".caixa-alerta").hide();
		},
		onkeyup : false,
		onfocusout : false,
		showErrors : function(errorMap, errorList) {
			var temErro = false;
			this.defaultShowErrors();
			$(".caixa-alerta p").empty();
			$.each(errorList, function(i, val) {
				$(".caixa-alerta").append("<p>" + val.message + "</p>");
				temErro = true;
			});
			if (temErro) {
				$(".caixa-alerta").show();
			}
		}
	});
	
	$.notifyDefaults({
		allow_dismiss: false,
		delay: 2000,
		z_index: '1151'
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
	
	$.fn.putJSON = function(url,dados){
		return $.ajax({type : 'put', url : url, datatype : "json",	contentType : 'application/json; charset=utf-8',
			data : JSON.stringify(dados)}).fail($.fn.tratarErro);;
	};
	
	$.fn.postJSON = function(url,dados){
		return $.ajax({type : 'post', url : url, datatype : "json",	contentType : 'application/json; charset=utf-8',
			data : JSON.stringify(dados)}).fail($.fn.tratarErro);
	};
	
	$.fn.getJSON = function(url){
		return $.ajax({type : 'get', url : url, datatype : "json",	contentType : 'application/json; charset=utf-8'})
			.fail($.fn.tratarErro);
	};

	$.fn.exibirCaixaAlerta = function(mensagens) {		
		$(".caixa-alerta p").empty();
		$.each(mensagens, function(i, val) {
			$(".caixa-alerta").append("<p>" + val + "</p>");
		});
		$(".caixa-alerta").show();
	}

	$.fn.tratarErro = function(data) {
		$(".overlay").hide();
		if (data.status == 403) {
			$.fn.exibirCaixaAlerta([ "Usuário não possui permissão para executar operação!" ]);
		} else if (data.status == 404) {
			$.fn.exibirCaixaAlerta(data.responseJSON.objeto.errorMessages);			
		} else {
			$.notify({
				title: '<strong>Erro!</strong>',
				message: 'Falha de comunicação com o servidor. Tente novamente mais tarde'
			},{
				type: 'danger'
			});
		}
	}
});
