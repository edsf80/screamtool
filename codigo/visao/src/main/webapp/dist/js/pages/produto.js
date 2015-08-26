/**
 * Depente do jquery e jquery.validate
 */
$(function() {
	
	var linhaSelecionada, table;
	
	$.fn.dataTable.ext.errMode = 'none';
	
	table = $("#tProdutos").on( 'draw.dt', function () {
		var temPermissao = $(".perm_salvar_produto").text() == 'S'; 
		
		if(temPermissao) {
			$(".disabled").removeClass("disabled");
		}
	}).DataTable({
		"columns": [
                    { "data": "id" },
                    { "data": "descricao" },
                    { "data": "opcoes"}
                ]
	});
	
	$('#tProdutos tbody').on( 'click', 'button', function () {
        linhaSelecionada = table.row( $(this).parents('tr') );
		var data = linhaSelecionada.data();
		
		$.fn.abrirModal('#mCadProduto', 'Alterar Produto', data);
    } );

	$("#bAdProduto").click(function(){
		linhaSelecionada = undefined;
		$.fn.abrirModal('#mCadProduto', 'Novo Produto');		
	});

	$("#frmProduto").validate({
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
				estado = table.row.add({
						id: data.id, 
						descricao: data.descricao,
						opcoes: '<button id="bAltProduto" type="button" class="btn btn-xs btn-default disabled">	<i class="fa fa-edit"></i></button>'});
				linhaSelecionada = table.row(estado.index());
				estado.draw(false);
				$("#hIdProduto").val(data.id);
			}).fail($.fn.tratarErro);
		}
	});
});