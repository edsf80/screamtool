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
			$("#hIdProduto").val(dados[0]);
			$("#tDescricao").val(dados[1]);
		}
		
		$("#mCadProduto").modal('toggle');		
	}
	
	$.fn.dataTable.ext.errMode = 'none';
	
	table = $("#tProdutos").on( 'draw.dt', function () {
		var temPermissao = $(".perm_salvar_produto").text() == 'S'; 
		
		if(temPermissao) {
			$(".disabled").removeClass("disabled");
		}
	}).DataTable();
	
	$('#tProdutos tbody').on( 'click', 'button', function () {
        linhaSelecionada = table.row( $(this).parents('tr') );
		var data = linhaSelecionada.data();
		
		abrirModal('Alterar Produto', data);
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
			$(element).closest(".form-group").addClass("has-error");
		},
		unhighlight: function(element, errorClass, validClass) {
			$(element).closest(".form-group").removeClass("has-error");
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
					linhaSelecionada.remove();
				}
				estado = table.row.add([data.id+'', data.descricao+'', '<button id="bAltProduto" type="button" class="btn btn-xs btn-default disabled">	<i class="fa fa-edit"></i></button>']);
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
					window.location.href = "../erro.htm";
				}
			});
		}
	});
});