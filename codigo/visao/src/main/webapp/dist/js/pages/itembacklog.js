/**
 * Depente do jquery e jquery.validate
 */
$(function() {
	
	var linhaSelecionada, table, permissoes;
	
	function abrirModal(titulo, dados) {
		$("#caixaAlerta").hide();
		$('#mCadItemBacklog').find('.modal-title').text(titulo);
		$("#frmItemBacklog")[0].reset();
		$(".has-error").removeClass("has-error");
		
		if(dados !== undefined) {
			$("#hIdItemBacklog").val(dados.id);
			$("#tDescricao").val(dados.descricao);
			$("#sStatus").val(dados.status.substr(dados.status.indexOf(">")+1,1));
			$("#sPontos").val(dados.storyPoints);
			$("#tEstoriaUsuario").val(dados.estoriaUsuario);
		}
		
		$("#mCadItemBacklog").modal('toggle');		
	}
	
	$.fn.dataTable.ext.errMode = 'none';
	
	table = $("#tItensBacklog").on( 'draw.dt', function () {
		var semPermissao = $("#bAdItemBacklog").hasClass("disabled"); 
		
		if(!semPermissao) {
			$(".disabled").removeClass("disabled");
		}
	}).DataTable({
		"paging": false,
		"columnDefs": [
		               {
		                   "targets": [ 0 ],
		                   "visible": false,
		                   "searchable": false
		               },
		               {
		                   "targets": [ 4 ],
		                   "visible": false,
		                   "searchable": false
		               },
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
				type : "post",
				url : "../service/itembacklog/excluirItemBacklog.rest",
				datatype : "json",
				data : "id="+dados.id
			}).done(function(data) {
				linha.remove();
				table.draw(false);
				alert("Item de Backlog Excluído com Sucesso!");
			}).fail(function(data) {
				if(data.status == 403) {
					exibirCaixaAlerta(["Usuário não possui permissão para executar operação!"]);
				}else if (data.status == 404) {
					exibirCaixaAlerta(data.responseJSON.objeto.errorMessages);
				} else {
					window.location.href = "erro.htm";
				}
			});
        }
    } );
	
	function exibirCaixaAlerta(mensagens) {
		$("#caixaAlerta p").empty();
		$.each(mensagens, function(i, val) {
			$("#caixaAlerta").append("<p>"+val+"</p>");			
		});
		$("#caixaAlerta").show();
	}
	
	$("#bAdItemBacklog").click(function(){
		linhaSelecionada = undefined;
		abrirModal('Novo Item de Backlog');		
	});

	$("#frmItemBacklog").validate({
		errorPlacement : function(error, element) {
			$(element).parent().addClass("has-error");
		},
		unhighlight: function(element, errorClass, validClass) {
			$(element).parent().removeClass("has-error");
			$("#caixaAlerta").hide();
		},
		onkeyup : false,
		onfocusout: false,
		showErrors : function(errorMap, errorList) {
			this.defaultShowErrors();
			$("#caixaAlerta p").empty();
			$.each(errorList, function(i, val) {
				$("#caixaAlerta").append("<p>"+val.message+"</p>");
			});
			$("#caixaAlerta").show();
		},
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
			var dados = $("#frmItemBacklog").serialize();
			var ordem;
			
			$(".overlay").show();
			if(linhaSelecionada === undefined) {
				ordem = table.column(0).data().length;
				console.log(ordem);
				dados = dados+"&ordem="+ordem;
			} else {
				ordem = linhaSelecionada.index();
				console.log(ordem);
				dados = dados+"&ordem="+ordem;
			}
			
			$.ajax({
				type : method,
				url : actionurl,
				datatype : "json",
				data : dados
			}).done(function(data) {
				alert("Item de Backlog Salvo com Sucesso!");
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
			}).fail(function(data) {
				$(".overlay").hide();
				if(data.status == 403) {
					exibirCaixaAlerta(["Usuário não possui permissão para executar operação!"]);
				}else if (data.status == 404) {
					exibirCaixaAlerta(data.responseJSON.objeto.errorMessages);
				} else {
					window.location.href = "erro.htm";
				}
			});
		}
	});
});