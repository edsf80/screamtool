$(function() {

	var sprint = {};

	$("#mCadSprint").on('show.bs.modal', function(event) {
		$("#tNomeSprint").focus();
		var componente = $(event.relatedTarget);
		var releaseId = componente.data('release-id');

		sprint.release = {};
		sprint.release["id"] = releaseId;
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
				alert("Sprint Salvo com Sucesso!");
				$(".overlay").hide();
				$("#hIdSprint").val(data.id);
			}).fail($.fn.tratarErro);
		}
	});

});