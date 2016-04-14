/**
 * Depente do jquery e jquery.validate
 */
$(function() {
	
	var linhaSelecionada, table;
	var itemBacklog = {};
	
	function abrirModal(titulo, dados) {
		$("#caixaAlerta").hide();
		$('#mCadItemBacklog').find('.modal-title').text(titulo);
		$("#frmItemBacklog")[0].reset();
		$(".has-error").removeClass("has-error");
		
		if(dados !== undefined) {
			itemBacklog = dados;
			$("#hIdItemBacklog").val(itemBacklog.id);
			$("#tDescricao").val(itemBacklog.descricao);
			$("#sStatus").val(itemBacklog.status.substr(dados.status.indexOf(">")+1,1));
			$("#sPontos").val(itemBacklog.storyPoints);
			$("#tEstoriaUsuario").val(itemBacklog.estoriaUsuario);
		}
		
		$("#mCadItemBacklog").modal('toggle');		
	}
	
	function exibirCaixaAlerta(mensagens) {
		$("#caixaAlerta p").empty();
		$.each(mensagens, function(i, val) {
			$("#caixaAlerta").append("<p>"+val+"</p>");			
		});
		$("#caixaAlerta").show();
	}
	
	$.fn.dataTable.ext.errMode = 'none';
	
	table = $("#tItensBacklog").on( 'draw.dt', function () {
		//Isso é utlizado por que quando se adiciona um item na tabela dincamicamente 
		//não se tem a informação do servidor se o usuário possui ou não acesso ao botão.
		var temPermissao = $(".perm_salvar_item_backlog").text() == 'S'; 
		
		if(temPermissao) {
			$(".disabled").removeClass("disabled");
		}
	}).DataTable({
		"paging": false,
		"columnDefs": [
		               {
		                   "targets": [ 0, 4 ],
		                   "visible": false,
		                   "searchable": false
		               }
		            ],
        "columns": [
                    { "data": "id" },
                    { "data": "descricao" },
                    { "data": "status" },
                    { "data": "storyPoints" },
                    { "data": "estoriaUsuario" },
                    { "data": "opcoes" }
                ]
	});
	
	$('#tItensBacklog tbody').on( 'click', '#bAltItemBacklog', function () {
        linhaSelecionada = table.row( $(this).parents('tr') );
		var data = linhaSelecionada.data();
		
		abrirModal('Alterar Item de Backlog', data);
    } );
	
	$('#tItensBacklog tbody').on( 'click', '#bExcItemBacklog', function () {
		var linha = table.row( $(this).parents('tr') );
		var dados = linha.data();
		
        var confirmacao = confirm("Deseja realmente apagar essa item de backlog?");
        
        if(confirmacao) {
        	$.ajax({
				type : "delete",
				url : "../service/itembacklog/"+dados.id,
				datatype : "json"
			}).done(function(data) {
				linha.remove();
				table.draw(false);
				$.notify({
					title: '<strong>Sucesso!</strong>',
					message: 'Item de backlog excluído com sucesso.'
				},{
					type: 'success'
				});
			}).fail($.fn.tratarErro);
        }
    } );
	
	$("#bAdItemBacklog").click(function(){
		linhaSelecionada = undefined;
		abrirModal('Novo Item de Backlog');		
	});

	$("#frmItemBacklog").validate({
		rules : {
			descricao : "required",
			status: "required",
			estoriaUsuario: "required"
		},
		messages : {
			descricao : "A descrição é obrigatória",
			status: "O status do item é obrigatório",
			estoriaUsuario: "A estória do usuário é obrigatória"
		},
		submitHandler : function(form) {
			var actionurl = form.action;
			var method = form.method;			
			
			$(".overlay").show();
			
			$("input[field]").each(function() {
				itemBacklog[$(this).attr("field")] = $(this).val();
			});
			
			itemBacklog.estoriaUsuario = $("#tEstoriaUsuario").val();
			itemBacklog.storyPoints = $("#sPontos").val();
			itemBacklog.status = $("#sStatus").val();
			
			console.log(JSON.stringify(itemBacklog));
			
			$.ajax({
				type : method,
				url : actionurl,
				datatype : "json",
				contentType : 'application/json; charset=utf-8',
				data : JSON.stringify(itemBacklog),
				headers : {
					'Accept' : 'application/json',
					'Content-Type' : 'application/json'
				}
			}).done(function(data) {
				$.notify({
					title: '<strong>Sucesso!</strong>',
					message: 'Item de backlog salvo com sucesso.'
				},{
					type: 'success'
				});
				$(".overlay").hide();
				if(linhaSelecionada !== undefined) {
					linhaSelecionada.remove();
				}
				
				var statusExtenso;
				
				switch(data.status) {
				case 'N':
					statusExtenso = '<span class="label label-danger">NOVO</span>';
					break;
				case 'P':
					statusExtenso = '<span class="label label-warning">PREPARADO</span>';
					break;
				case 'A':
					statusExtenso = '<span class="label label-primary">EM ANDAMENTO</span>';
					break;
				case 'F':
					statusExtenso = '<span class="label label-success">FINALIZADO</span>';
					break;
				}
				
				estado = table.row.add({
					"id": data.id, 
					"descricao": data.descricao, 
					"status": statusExtenso, 
					"storyPoints": data.storyPoints,
					"estoriaUsuario": data.estoriaUsuario,
					"opcoes": '<button id="bAltItemBacklog" type="button" class="btn btn-xs btn-default disabled">	<i class="fa fa-edit"></i></button><button id="bExcItemBacklog" type="button" class="btn btn-xs btn-default disabled">	<i class="fa fa-trash"></i></button>'});
				linhaSelecionada = table.row(estado.index());
				estado.draw(false);
				$("#hIdItemBacklog").val(data.id);
			}).fail($.fn.tratarErro);
		}
	});
});