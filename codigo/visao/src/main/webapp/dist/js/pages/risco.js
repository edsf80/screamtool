/**
 * Depente do jquery e jquery.validate
 */
$(function() {
	
	var linhaSelecionada, table;
	
	function abrirModal(titulo, dados) {
		$("#caixaAlerta").hide();
		$('#mCadRisco').find('.modal-title').text(titulo);
		$("#frmRisco")[0].reset();
		$(".has-error").removeClass("has-error");
		
		if(dados !== undefined) {
			$("#hId").val(dados.id);
			$("#tDescricao").val(dados.descricao);
			$("#sStatus").empty();
			$("#sStatus").append("<option value='"+dados.status.substr(dados.status.indexOf(">")+1,1)+"'>"+traduzirStatusExtenso(dados.status.substr(dados.status.indexOf(">")+1,1))+"</option>");
			$("#sStatus").val(dados.status.substr(dados.status.indexOf(">")+1,1));
			$("#sProbabilidade").val(dados.probabilidade);
			$("#sImpacto").val(dados.impacto);
			if(dados.responsavel != null) {
				$("#sResponsavel").val(dados.responsavel.id+"-"+dados.responsavel.nome);
			}
			$("#tMitigacao").val(dados.mitigacao);
			$("#tContingencia").val(dados.contin);
			
			$.each(dados.transicoes, function(i, val) {
				$("#sStatus").append("<option value='"+val+"'>"+traduzirStatusExtenso(val)+"</option>");			
			});
		} else {
			$("#sStatus").empty();
			$("#sStatus").append("<option value='N'>Novo</option>");
		}
		
		$("#mCadRisco").modal('toggle');		
	}
	
	$.fn.dataTable.ext.errMode = 'none';
	
	table = $("#tRiscos").on( 'draw.dt', function () {
		var temPermissao = $(".perm_salvar_risco").text() == 'S'; 
		
		if(temPermissao) {
			$(".disabled").removeClass("disabled");
		}
	}).DataTable({
		"paging": false,
		"columnDefs": [
		               {
		                   "targets": [ 0 ],
		                   "visible": false,
		                   "searchable": false
		               }
		            ],
        "columns": [
                    { "data": "id" },
                    { "data": "descricao" },
                    { "data": "status" },
                    { "data": "responsavel" },
                    { "data": "opcoes" }
                ]
	});
	
	$('#tRiscos tbody').on( 'click', '#bAltRisco', function () {
        linhaSelecionada = table.row( $(this).parents('tr') );
		var data = linhaSelecionada.data();
		
		$.ajax({
			type : "get",
			url : "../service/risco/"+data.id,
			datatype : "json"
		}).done(function(data) {
			abrirModal('Alterar Risco', data);			
		}).fail($.fn.tratarErro);
    } );
	
	function exibirCaixaAlerta(mensagens) {
		$("#caixaAlerta p").empty();
		$.each(mensagens, function(i, val) {
			$("#caixaAlerta").append("<p>"+val+"</p>");			
		});
		$("#caixaAlerta").show();
	}
	
	function traduzirStatusExtenso(simboloStatus) {
		var statusExtenso;
		
		switch(simboloStatus) {
		case 'N':
			statusExtenso = 'Novo';
			break;
		case 'E':
			statusExtenso = 'Em Andamento';
			break;
		case 'F':
			statusExtenso = 'Finalizado';
			break;
		}
		
		return statusExtenso;
	}
	
	$("#bAdRisco").click(function(){
		linhaSelecionada = undefined;
		abrirModal('Novo Risco');		
	});

	$("#frmRisco").validate({
		rules : {
			descricao : "required",
			status: "required"
		},
		messages : {
			descricao : "A descrição é obrigatória",
			status: "O status do item é obrigatório"
		},
		submitHandler : function(form) {
			var actionurl = form.action;
			var method = form.method;
			
			$(".overlay").show();
			
			$.ajax({
				type : method,
				url : actionurl,
				datatype : "json",
				data : $("#frmRisco").serialize()
			}).done(function(data) {
				alert("Risco Salvo com Sucesso!");
				$(".overlay").hide();
				if(linhaSelecionada !== undefined) {
					linhaSelecionada.remove();
				}
				
				var statusExtenso;
				
				switch(data.status) {
				case 'N':
					statusExtenso = '<span class="label label-danger">Novo</span>';
					break;
				case 'E':
					statusExtenso = '<span class="label label-primary">Em Andamento</span>';
					break;
				case 'F':
					statusExtenso = '<span class="label label-success">Finalizado</span>';
					break;
				}
				
				var nomeResponsavel = null;
				
				if(data.responsavel != null) {
					nomeResponsavel = data.responsavel.nome;
				}
				
				estado = table.row.add({
					"id": data.id, 
					"descricao": data.descricao, 
					"status": statusExtenso, 
					"responsavel": nomeResponsavel,
					"opcoes": '<button id="bAltRisco" type="button" class="btn btn-xs btn-default disabled">	<i class="fa fa-edit"></i></button>'});
				linhaSelecionada = table.row(estado.index());
				estado.draw(false);
				$("#hId").val(data.id);
			}).fail($.fn.tratarErro);
		}
	});
});