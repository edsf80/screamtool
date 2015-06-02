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
				alert("Tntou buscar as porra dos produto!");
				var lista = data == null ? [] : (data instanceof Array ? data
						: [ data ]);

				$("#tProdutos tbody tr").remove();
				$.each(lista, function(index, produto) {
					$("#tProdutos tbody").append(
							'<tr><th>' + produto.id + '</th><th>'
									+ produto.descricao + '</th></tr>');
				});
			}).fail(function(data) {
		alert("Pau de selfie");

	});

});