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
							'<li><a href="dashboard.html?projeto='+projeto.id+'"><i class="fa fa-circle-o"></i> '
									+ projeto.nome + '</a></li>');
				});
			}).fail(function(data) {
		alert("Pau de selfie");
	});

});