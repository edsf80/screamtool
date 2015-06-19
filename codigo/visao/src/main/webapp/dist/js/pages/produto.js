/**
 * Depente do jquery e jquery.validate
 */
$(function() {

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

	$.ajax({
		type : "get",
		url : "../service/buscarTodosProdutos.rest",
		dataType : "json"
	}).done(
			function(data) {
				var lista = data == null ? [] : (data instanceof Array ? data
						: [ data ]);

				$("#tProdutos tbody tr").remove();
				$.each(lista, function(index, produto) {
					$("#tProdutos tbody").append(
							'<tr><td>' + produto.id + '</td><td>'
									+ produto.descricao + '</td>'
									+ '<td style="width: 100px"><div class="btn-group"><button type="button" class="btn btn-default btn-sm"><i class="fa fa-edit"></i></button>'
                                    + '<button type="button" class="btn btn-default btn-sm"><i class="fa fa-trash-o"></i></button></div></td></tr>');
				});
			}).fail(function(data) {
		alert("Pau de selfie");

	});

});