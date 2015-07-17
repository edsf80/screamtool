/**
 * Depente do jquery e jquery.validate
 */
$(function() {
	
	var linhaSelecionada, table, permissoes;
	
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
	
	$.fn.dataTable.ext.errMode = 'none';
	
	var dataSet;
	
	$.when(
			$.ajax({
				type : "get",
				url : "../service/produto/buscarTodosDados.rest",
				datatype : "json"
			}).done(function(data) {
				dataSet = data.produtos.data;
				
				$("#sNomeUsuario").append(data.usuario);
				$("#pNomeUsuarioPainel").append(data.usuario);
				$("#pNomeUsuarioMenu").append(data.usuario);
		
				var lista = data.projetos == null ? []
					: (data.projetos instanceof Array ? data.projetos
					: [ data.projetos ]);
		
				//$("#mProjetos li").remove();
				$.each(lista, function(index, projeto) {
					$("#mProjetos").append('<li><a href="dashboard.html?projeto=' + projeto.id
											+ '"><i class="fa fa-circle-o"></i> '
											+ projeto.nome + '</a></li>');
				});
				
				permissoes = data.permissoes == null ? []
						: (data.permissoes instanceof Array ? data.permissoes
						: [ data.permissoes ]);

				$.each(permissoes, function(index, permissao) {
					$("."+permissao.authority).removeClass("disabled");
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
			table = $("#tProdutos").DataTable({
				"data" : dataSet,
				"columns": [
		            { "data": "id" },
		            { "data": "descricao" },
		            { "data": null, 
		              "defaultContent": "<button id=\"bAltProduto\" type=\"button\" class=\"btn btn-xs btn-default disabled perm_salvar_produto\"><i class='fa fa-edit'></i></button>"
		            }
		        ]});
			
			$('#tProdutos tbody').on( 'click', 'button', function () {
		        linhaSelecionada = table.row( $(this).parents('tr') );
				var data = linhaSelecionada.data();
				
				abrirModal('Alterar Produto', data);
		    } );
		});
	
	$('#tProdutos').on( 'draw.dt', function () {
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
	
	$("#bAdProduto").click(function(){
		linhaSelecionada = undefined;
		abrirModal('Novo Produto');		
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
			descricao : "required"
		},
		messages : {
			descricao : "A descrição é obrigatória"
		},
		submitHandler : function(form) {
			var actionurl = form.action;
			var method = form.method;
			
			$(".overlay").show();
			
			$.ajax({
				type : method,
				url : actionurl,
				datatype : "json",
				data : $("#frmProduto").serialize()
			}).done(function(data) {
				alert("Produto Salvo com Sucesso!");
				$(".overlay").hide();
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