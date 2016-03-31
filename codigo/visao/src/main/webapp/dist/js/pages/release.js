$(function() {

	var releases;
	var release = {};

	$(".sortable1, .sortable2").sortable({
		connectWith : ".connectedSortable",
		update : function() {
			var order = $(this).sortable('serialize');			
		}
	}).disableSelection();
	
	$("#mCadSprint").save(function(event) {
		 
	});

	$("#bAdRelease").click(function() {
		$.fn.abrirModal('#mCadRelease', 'Novo Release');
	});

	$("#frmRelease").validate({
		rules : {
			nome : "required",
			descricao : "required"
		},
		messages : {
			nome : "O nome do release é obrigatório",
			descricao : "A descrição é obrigatória"
		},
		submitHandler : function(form) {
			var actionurl = form.action;
			var method = form.method;

			$("input[field]").each(function() {
				release[$(this).attr("field")] = $(this).val();
			});

			$(".overlay").show();

			$.ajax({
				type : method,
				url : actionurl,
				datatype : "json",
				contentType : 'application/json; charset=utf-8',
				data : JSON.stringify(release)
			}).done(function(data) {
				alert("Release Salvo com Sucesso!");
				$(".overlay").hide();
				$("#hIdRelease").val(data.id);
			}).fail($.fn.tratarErro);
		}
	});
});