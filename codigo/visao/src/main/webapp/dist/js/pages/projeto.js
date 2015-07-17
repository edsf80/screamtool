/**
 * Depente do jquery e jquery.validate
 */
$(function() {
	
	var linhaSelecionada, table, permissoes;
	
	$("#tDataInicio").inputmask("dd/mm/yyyy", {"placeholder": "dd/mm/yyyy"});
	
	$("#tDataInicio").datepicker({
		format: 'dd/mm/yyyy'
	});
	
	function abrirModal(titulo, dados) {
		$("#caixaAlerta").hide();
		$('#mCadProjeto').find('.modal-title').text(titulo);
		$("#frmProjeto")[0].reset();
		$(".has-error").removeClass("has-error");
		
		if(dados !== undefined) {
			$("#hIdProjeto").val(dados.id);
			$("#tNomeProjeto").val(dados.nome);
			$("#sProdutos").val(dados.produto.id+"-"+dados.produto.descricao);
		}
		
		$("#mCadProjeto").modal('toggle');		
	}
	
	$.fn.dataTable.ext.errMode = 'none';
	
	var dataSet;
	
	$.when(
		$.ajax({
			type : "get",
			url : "../service/projeto/buscarTodosDados.rest",
			datatype : "json"
		}).done(function(data) {
			dataSet = data.projetos.data;
			
			$("#sNomeUsuario").append(data.usuario.nome);
			$("#pNomeUsuarioPainel").append(data.usuario.nome);
			$("#pNomeUsuarioMenu").append(data.usuario.nome);
	
			var lista = data.usuario.projetos == null ? []
				: (data.usuario.projetos instanceof Array ? data.usuario.projetos
				: [ data.usuario.projetos ]);
	
			//$("#mProjetos li").remove();
			$.each(lista, function(index, projeto) {
				$("#mProjetos").append('<li><a href="dashboard.html?projeto=' + projeto.id
										+ '"><i class="fa fa-circle-o"></i> '
										+ projeto.nome + '</a></li>');
			});
			
			permissoes = data.usuario.authorities == null ? []
					: (data.usuario.authorities instanceof Array ? data.usuario.authorities
					: [ data.usuario.authorities ]);

			$.each(permissoes, function(index, permissao) {
				$("."+permissao.authority).removeClass("disabled");
			});
			
			var produtos = data.produtos == null ? []
					: (data.produtos instanceof Array ? data.produtos
					: [ data.produtos ]);
			
			$.each(produtos, function(index, produto) {
				$("#sProdutos").append("<option value=\""+produto.id+"-"+produto.descricao+"\">"+produto.descricao+"</option>");
			});
		}).fail(function(data) {
			if(data.status == 403) {
				alert("Usuário não possui permissão para executar operação!");
			}else if (data.status == 404) {
				exibirCaixaAlerta(data.responseJSON.objeto.errorMessages);
			} else {
				window.location.href = "../erro.html";
			}
		})
	).done(function(){
		table = $("#tProjetos").DataTable({
			"data" : dataSet,
			"columns": [
	            { "data": "id" },
	            { "data": "nome" },
	            { "data": "produto.descricao" },
	            { "data": null, 
	              "defaultContent": "<button id=\"bAltProjeto\" type=\"button\" class=\"btn btn-xs btn-default disabled perm_salvar_projeto\"><i class='fa fa-edit'></i></button>"
	            }
	        ]});
		
		$('#tProjetos tbody').on( 'click', 'button', function () {
	        linhaSelecionada = table.row( $(this).parents('tr') );
			var data = linhaSelecionada.data();
			
			abrirModal('Alterar Projeto', data);
	    } );
	});
	
	$('#tProjetos').on( 'draw.dt', function () {
		$.each(permissoes, function(index, permissao) {
			$("."+permissao.authority).removeClass("disabled");
		});
	} );
	
	function exibirCaixaAlerta(mensagens) {
		$("#caixaAlerta p").empty();
		$.each(mensagens, function(i, val) {
			$("#caixaAlerta").append("<p>"+val+"</p>");			
		});
		$("#caixaAlerta").show();
	}

	$("#bAdProjeto").click(function(){
		linhaSelecionada = undefined;
		abrirModal('Novo Projeto');		
	});

	$("#frmProjeto").validate({
		errorPlacement : function(error, element) {
			$(element).closest(".form-group").addClass("has-error");
		},
		unhighlight: function(element, errorClass, validClass) {
			$(element).closest(".form-group").removeClass("has-error");
			$("#caixaAlerta").hide();
		},
		onkeyup : false,
		onclick : false,
		//onsubmit : false,
		onfocusout: false,
		showErrors : function(errorMap, errorList) {
			$("#caixaAlerta p").empty();
			$.each(errorList, function(i, val) {
				$("#caixaAlerta").append("<p>"+val.message+"</p>");
			});
			$("#caixaAlerta").show();
			this.defaultShowErrors();
		},
		rules : {
			nome : "required",
			dataInicio: "required",
			idProduto: "required"
		},
		messages : {
			nome : "O nome do projeto é obrigatório",
			dataInicio: "A data de início do projeto é obrigatória",
			idProduto: "O produto é obrigatório"
		},
		submitHandler : function(form) {
			var actionurl = form.action;
			var method = form.method;
			
			$(".overlay").show();
			
			$.ajax({
				type : method,
				url : actionurl,
				datatype : "json",
				data : $("#frmProjeto").serialize()
			}).done(function(data) {
				alert("Projeto Salvo com Sucesso!");
				$(".overlay").hide();
				if(linhaSelecionada !== undefined) {
					linhaSelecionada.remove().draw(false);
				}
				estado = table.row.add({
					"id": data.id, 
					"nome":data.nome,
					"produto.descricao": data.produto.descricao});
				linhaSelecionada = table.row(estado.index());
				estado.draw(false);
				$("#hIdProjeto").val(data.id);
			}).fail(function(data) {
				$(".overlay").hide();
				if(data.status == 403) {
					exibirCaixaAlerta(["Usuário não possui permissão para executar operação!"]);
				}else if (data.status == 404) {
					exibirCaixaAlerta(data.responseJSON.objeto.errorMessages);
				} else {
					window.location.href = "../erro.html";
				}
			});
		}
	});
});