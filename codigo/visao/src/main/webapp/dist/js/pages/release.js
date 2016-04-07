$(function() {

	var releases;
	var release = {};
	var sprint = {};

	$(".sortable1, .sortable2").sortable({
		connectWith : ".connectedSortable",
		update : function() {
			var order = $(this).sortable('serialize');
		}
	}).disableSelection();

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

			var dados = JSON.stringify(sprint);

			$.ajax({
				type : method,
				url : actionurl,
				datatype : "json",
				contentType : 'application/json; charset=utf-8',
				data : dados,
				headers : {
					'Accept' : 'application/json',
					'Content-Type' : 'application/json'
				}
			}).done(function(data) {
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
				
			}).fail($.fn.tratarErro);
		}
	});
});