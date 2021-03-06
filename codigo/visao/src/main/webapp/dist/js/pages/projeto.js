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
			$("#hIdProjeto").val(dados[0]);
			$("#tNomeProjeto").val(dados[1]);
			$("#sProdutos").val(dados[2]+"-"+dados[3]);
		}
		
		$("#mCadProjeto").modal('toggle');		
	}
	
	$.fn.dataTable.ext.errMode = 'none';
	
	table = $("#tProjetos").on( 'draw.dt', function () {
		var temPermissao = $(".perm_salvar_projeto").text() == 'S'; 
		
		if(temPermissao) {
			$(".disabled").removeClass("disabled");
		}
	}).DataTable({
		"columnDefs": [
           {
               "targets": [ 2 ],
               "visible": false,
               "searchable": false
           }
        ]
	});
	
	$('#tProjetos tbody').on( 'click', 'button', function () {
        linhaSelecionada = table.row( $(this).parents('tr') );
		var data = linhaSelecionada.data();
		
		abrirModal('Alterar Projeto', data);
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
					linhaSelecionada.remove();
				}
				estado = table.row.add([data.id, 
					data.nome,
					data.produto.id,
					data.produto.descricao,
					'<button id="bAltProjeto" type="button" class="btn btn-xs btn-default disabled">	<i class="fa fa-edit"></i></button>']);
				linhaSelecionada = table.row(estado.index());
				estado.draw(false);
				$("#hIdProjeto").val(data.id);
			}).fail($.fn.tratarErro);
		}
	});
});