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
			$("#hIdItemBacklog").val(dados[0]);
			$("#tDescricao").val(dados[1]);
			alert(dados[2]);
			$("#sStatus").val(dados[2].substring(1,1));
			$("#sPontos").val(dados[3]);
			$("#tEstoriaUsuario").val(dados[4]);
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
		               }
		            ]
		    	});
	
	$('#tItensBacklog tbody').on( 'click', 'button', function () {
        linhaSelecionada = table.row( $(this).parents('tr') );
		var data = linhaSelecionada.data();
		
		abrirModal('Alterar Item de Backlog', data);
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
			$("#caixaAlerta p").empty();
			$.each(errorList, function(i, val) {
				$("#caixaAlerta").append("<p>"+val.message+"</p>");
			});
			$("#caixaAlerta").show();
			this.defaultShowErrors();
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
			
			$(".overlay").show();
				dados = dados+"&ordem=1";
			alert(dados);
			
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
				estado = table.row.add([data.id, data.descricao, data.status, data.storyPoints,'<button id="bAltItemBacklog" type="button" class="btn btn-xs btn-default disabled">	<i class="fa fa-edit"></i></button><button id="bExcItemBacklog" type="button" class="btn btn-xs btn-default disabled">	<i class="fa fa-trash"></i></button>']);
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
					window.location.href = "../erro.html";
				}
			});
		}
	});
});