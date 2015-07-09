/**
 * Depente do jquery e jquery.validate
 */
$(function() {
	
	var linhaSelecionada;
	
	function abrirModal(titulo, dados) {
		$("#caixaAlerta").hide();
		$('#mCadProduto').find('.modal-title').text(titulo);
		$("#frmProduto")[0].reset();
		$(".has-error").removeClass("has-error");
		
		if(dados !== undefined) {
			$("#hIdProduto").val(dados.id);
			$("#tDescricao").val(dados.descricao);
		}
		
		$("#mCadProduto").modal('toggle');
	}
	
	var table = $("#tProdutos").DataTable({
		"ajax" : "../service/buscarTodosProdutos.rest",
        "columns": [
            { "data": "id" },
            { "data": "descricao" },
            { "data": null, "defaultContent": "<button id=\"bAltProduto\" type=\"button\" class=\"btn btn-xs btn-default\"><i class='fa fa-edit'></i></button>"}
        ]
	});

	$('#tProdutos tbody').on( 'click', 'button', function () {
        linhaSelecionada = table.row( $(this).parents('tr') );
		var data = linhaSelecionada.data();
		
		abrirModal('Alterar Produto', data);
    } );
	
	$("#bAdProduto").click(function(){
		linhaSelecionada = undefined;
		abrirModal('Novo Produto');
	});

	$.ajax({
		type : "get",
		url : "../service/buscarUsuarioSessao.rest",
		datatype : "json"
	}).done(function(data) {
		$("#sNomeUsuario").append(data.nome);
		$("#pNomeUsuarioPainel").append(data.nome);
		$("#pNomeUsuarioMenu").append(data.nome);

		var lista = data.projetos == null ? []
			: (data.projetos instanceof Array ? data.projetos
			: [ data.projetos ]);

		//$("#mProjetos li").remove();
		$.each(lista, function(index, projeto) {
		$("#mProjetos").append('<li><a href="dashboard.html?projeto=' + projeto.id
									+ '"><i class="fa fa-circle-o"></i> '
									+ projeto.nome + '</a></li>');
		});
	}).fail(function(data) {
		alert("Pau de selfie");
	});

	$("#frmProduto").validate({
		errorPlacement : function(error, element) {
			$(element).parent().addClass("has-error");
		},
		unhighlight: function(element, errorClass, validClass) {
			$(element).parent().removeClass("has-error");
			$("#caixaAlerta").hide();
		},
		onkeyup : false,
		onsubmit : false,
		onfocusout: false,
		showErrors : function(errorMap, errorList) {
			$.each(errorList, function(i, val) {
				$("#caixaAlerta p").empty();
				$("#caixaAlerta").append("<p>"+val.message+"</p>");
			});
			this.defaultShowErrors();
		},
		rules : {
			descricao : "required"
		},
		messages : {
			descricao : "A descrição é obrigatória"
		},
		invalidHandler : function(event, validator) {
			// 'this' refers to the form
			//var errors = validator.numberOfInvalids();
			//if (errors) {
			$("#caixaAlerta").show();
			//} else {
				//$("#caixaAlerta").hide();
			//}
		}
	});

	$("#bSalvar").click(function() {
						
	    if ($("#frmProduto").valid()) {							
	    	$.ajax({
				type : "POST",
				url : "../service/salvarProduto.rest",
				datatype : "json",
				data : $("#frmProduto").serialize()
			}).done(function(data) {
				alert("Produto Salvo com Sucesso!");
				if(linhaSelecionada !== undefined) {
					linhaSelecionada.remove().draw(false);
				}
				estado = table.row.add({
					"id": data.id, 
					"descricao":data.descricao});
				linhaSelecionada = table.row(estado.index());
				estado.draw(false);
				$("#hIdProduto").val(data.id);
			}).fail(function(data) {
				if (data.status == 404) {
					$("#caixaAlerta").append(data.responseJSON.objeto.errorMessages);
					$("#caixaAlerta").show();
				} else {
					alert("Pau de selfie");
				}
			});
		}
	});

});