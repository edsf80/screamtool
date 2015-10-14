$(function() {
	
	var releases;
	
	$.get("../service/releases").done(function(data){
		releases = data;
	});
	
	$('#external-events div.external-event').draggable({
		containment : '.content',
		stack : '#external-events div',
		cursor : 'move',
		helper : "clone",
		drag : function() {
			$(this).draggable('option', 'revert', true);
		}
	});

	$('#itensSprint').droppable({
		accept : '#external-events div.external-event',
		hoverClass : 'hovered',
		drop : handleDrop
	});

	function handleDrop(event, ui) {
		$(this).height($(this).height() + 33);
		$(this).append($(ui.draggable));
		ui.draggable.draggable('option', 'revert', false);
	}
	
	$("#bAdRelease").click(function(){
		$.fn.abrirModal('#mCadRelease', 'Novo Release');		
	});
	
	$("#frmRelease").validate({
		rules : {
			nome: "required",
			descricao : "required"
		},
		messages : {
			nome: "O nome do release é obrigatório",
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
				data : $("#frmRelease").serialize()
			}).done(function(data) {
				alert("Release Salvo com Sucesso!");
				$(".overlay").hide();				
				$("#hIdRelease").val(data.id);
			}).fail($.fn.tratarErro);
		}
	});
});