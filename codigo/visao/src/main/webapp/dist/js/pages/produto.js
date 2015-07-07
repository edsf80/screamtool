/**
 * Depente do jquery e jquery.validate
 */
$(function() {
	
	var table = $("#tProdutos").DataTable({
		"ajax" : "../service/buscarTodosProdutos.rest"
	});

	$('#tProdutos tbody').on('click', 'tr', function() {
		
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		} else {
			table.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
	});
	
	$("#bAdProduto").click(function(){
		$('#mCadProduto').find('.modal-title').text('Novo Produto');
	});
	
	$("#bAltProduto").click(function(){
		
		$('#mCadProduto').find('.modal-title').text('Alterar Produto');
		
		var linhaSelecionada = table.rows('.selected').data();
		
		if(linhaSelecionada.length == 0) {
			alert("Selecione o produto a ser alterado!");
			return false;
		}
		
		$("#hIdProduto").val(linhaSelecionada[0][0]);
		$("#tDescricao").val(linhaSelecionada[0][1]);
		$("#mCadProduto").modal('toggle');
		
	});

	$.ajax({
		type : "get",
		url : "../service/buscarUsuarioSessao.rest",
		datatype : "json"
	}).done(
			function(data) {
				$("#sNomeUsuario").append(data.nome);
				$("#pNomeUsuarioPainel").append(data.nome);
				$("#pNomeUsuarioMenu").append(data.nome);

				var lista = data.projetos == null ? []
						: (data.projetos instanceof Array ? data.projetos
								: [ data.projetos ]);

				$("#mProjetos li").remove();
				$.each(lista, function(index, projeto) {
					$("#mProjetos").append(
							'<li><a href="dashboard.html?projeto=' + projeto.id
									+ '"><i class="fa fa-circle-o"></i> '
									+ projeto.nome + '</a></li>');
				});
			}).fail(function(data) {
		alert("Pau de selfie");
	});

	$("#frmProduto")
			.validate(
					{
						errorPlacement : function(error, element) {
							$(element).parent().addClass("has-error");
						},
						onkeyup : false,
						onsubmit : false,
						showErrors : function(errorMap, errorList) {
							$.each(errorList, function(i, val) {
								$("#caixaAlerta").append(val.message + "<br>");
							});
							this.defaultShowErrors();
						},
						submitHandler : function(form) {
							var actionurl = form.action;
							var method = form.method;

							$
									.ajax({
										type : method,
										url : actionurl,
										datatype : "json",
										data : $(form).serialize()
									})
									.done(function(data) {
										alert("Salvo com Sucesso!");
									})
									.fail(
											function(data) {
												if (data.status == 404) {
													$("#caixaAlerta")
															.append(
																	data.responseJSON.objeto.errorMessages);
													$("#caixaAlerta").show();
												} else {
													alert("Pau de selfie");
												}
											});
						},
						rules : {
							descricao : "required"
						},
						messages : {
							descricao : "A descrição é obrigatória"
						},
						invalidHandler : function(event, validator) {
							// 'this' refers to the form
							var errors = validator.numberOfInvalids();
							if (errors) {
								$("#caixaAlerta").show();
							} else {
								$("#caixaAlerta").hide();
							}
						}
					});

	$("#bSalvar")
			.click(
					function() {
						if ($("#frmProduto").valid()) {
							$
									.ajax({
										type : "POST",
										url : "../service/salvarProduto.rest",
										datatype : "json",
										data : $("#frmProduto").serialize()
									})
									.done(function(data) {
										alert("Salvo com Sucesso!");
										table.row('.selected').remove().draw(false);
										table.row.add([data.id, data.descricao]).draw();	
									})
									.fail(
											function(data) {
												if (data.status == 404) {
													$("#caixaAlerta")
															.append(
																	data.responseJSON.objeto.errorMessages);
													$("#caixaAlerta").show();
												} else {
													alert("Pau de selfie");
												}
											});
						}
					});

});