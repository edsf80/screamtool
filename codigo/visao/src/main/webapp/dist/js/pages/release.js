$(function() {

	var releases;
	var release = {};
	var sprint = {};
	
	$(".sortable1").sortable({
		connectWith : ".connectedSortable",
		receive : function(event, ui) {
			var $itemSelected = ui.item;
			var $storyPoints = $itemSelected.children('select');
			var itemBacklog = {};
			
			$storyPoints.removeAttr('disabled');
			
			itemBacklog.id = $itemSelected.data('itembacklog-id');
			itemBacklog.storyPoints = $storyPoints.val();
			
			$.fn.putJSON('../service/itembacklog', itemBacklog);		
			
		}
	}).disableSelection();
	
	$(".sortable2").sortable({
		connectWith : ".connectedSortable",
		receive : function(event, ui) {
			var $itemSelected = ui.item;
			var $storyPoints = $itemSelected.children('select');
			$storyPoints.attr('disabled', true);
			var itemBacklog = {};
			
			itemBacklog.id = $itemSelected.data('itembacklog-id');
			itemBacklog.sprint = {};
			itemBacklog.sprint.id = $itemSelected.closest('tr').data('sprint-id');
			itemBacklog.storyPoints = $storyPoints.val(); 
			
			$.fn.putJSON('../service/itembacklog', itemBacklog);
		}
	}).disableSelection();
	
	// Esse metodo nao precisa passar o sprint como parametro por que so eh executado nos itens nao alocados.
	$(".select-storypoints-ib").change(function() {
		var itemBacklog = {};
		
		var $parent = $(this).closest("li");
		
		itemBacklog.id = $parent.data("itembacklog-id");
		itemBacklog.storyPoints = $(this).val();
		
		$.fn.putJSON('../service/itembacklog', itemBacklog);
	});

	$("#mCadSprint").on('show.bs.modal', function(event) {
		$("#tNomeSprint").focus();
		var componente = $(event.relatedTarget);
		var sprintId = componente.data('sprint-id');		
		
		if(sprintId != undefined) {
			$.ajax({
				type : 'get',
				url : '../service/sprint/'+sprintId,
				datatype : "json",
				contentType : 'application/json; charset=utf-8'
			}).done(function(data) {
				sprint = data;
				$("#hIdSprint").val(sprint.id);
				$("#tNomeSprint").val(sprint.nome);
				$('#drPeriodoSprint').data('daterangepicker').setStartDate(sprint.dataInicio);
				$('#drPeriodoSprint').data('daterangepicker').setEndDate(sprint.dataTermino);
			}).fail($.fn.tratarErro);
		} else {
			sprint.release = {};
			sprint.release["id"] = componente.data('release-id');
			$("#frmSprint")[0].reset();
		}		

		
	});

	$('#drPeriodoSprint').daterangepicker({
		locale : {
			format : 'DD/MM/YYYY'
		}
	});

	$('#drPeriodoSprint').on('apply.daterangepicker', function(ev, picker) {
		sprint.dataInicio = picker.startDate.format('DD/MM/YYYY');
		sprint.dataTermino = picker.endDate.format('DD/MM/YYYY');
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

			$.fn.postJSON(actionurl, release)
				.done(function(data) {
					alert("Release Salvo com Sucesso!");
					$(".overlay").hide();
					$("#hIdRelease").val(data.id);
				});
		}
	});
	
	$("#frmSprint").validate({
		rules : {
			nome : "required",
			periodoSprint : "required"
		},
		messages : {
			nome : "O nome do sprint é obrigatório",
			periodoSprint : "O período do sprint é obrigatório"
		},
		submitHandler : function(form) {
			var actionurl = form.action;
			var method = form.method;

			$(".overlay").show();

			$("input[field]").each(function() {
				sprint[$(this).attr("field")] = $(this).val();
			});

			$.fn.postJSON(actionurl, sprint)			
				.done(function(data) {
					$.notify({
						title: '<strong>Sucesso!</strong>',
						message: 'Sprint salva com sucesso.'
					},{
						type: 'success'
					});
					$(".overlay").hide();
					var $linha = $("#sprint_"+data.id+" > td:first-child");
					$("#hIdSprint").val(data.id);
					if($linha == undefined) {
						$("#tSprints tbody").append("<tr id='sprint_'"+data.id+"'><td><a href='#' data-toggle='modal' data-target='#mCadSprint' data-sprint-id='"+
								data.id+"'>"+data.nome+" de "+data.dataInicio+" à "+data.dataTermino+
								"</a></td><td style='height:40px;'><ul class='connectedSortable sortable2' style='list-style-type: none; width: 100%'></ul></td></tr>");
					} else {
						$linha.html('<a href="#" data-toggle="modal" data-target="#mCadSprint" data-sprint-id="'+
								data.id+'">'+data.nome+' de '+data.dataInicio+' à '+data.dataTermino+'</a>');
					}				
					
				});
		}
	});
});