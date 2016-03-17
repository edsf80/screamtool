$(function() {
	
	var releases;
	var release = {};
	
	//$("#painelRelease").empty();
	
	$("#tDataInicio").inputmask("dd/mm/yyyy", {"placeholder": "dd/mm/yyyy"});
	
	$("#tDataInicio").datepicker({
		format: 'dd/mm/yyyy'
	});
	
	$.get("../service/itembacklog/list").done(function(data){
		
		$.each(data, function(index, value){
			$(".sortable1").append("<li id='item_"+value.id+"' title='"+value.descricao+"' class='external-event bg-yellow' style='margin: 3px 3px 3px 0; padding: 1px; float: left; width: 100px; height: 90px; text-align: center;'>"+
					value.descricao.substring(0, 20) + "..."+"<br><br><select><option>0</option><option>1</option><option>2</option><option>3</option><option>5</option></select></li>");
			
			
		});
	});
	
	/*$.get("../service/releases").done(function(data){
		releases = data;
		
		$.each(data, function(index, value){
			$(".panel").clone([true, true]).appendTo("#painelRelease");
		});*/
		
		/*$.each(data, function(index, value){
			$("#painelRelease").append('<div class="panel panel-default collapsed-panel"> '+
									   		'<div class="panel-heading"> <div class="row"> <div class="col-lg-5">'+value.nome+'</div>'+
									   		'<div class="col-sm-3 pull-left"> <div class="input-group"><button '+
													'class="btn btn-sm btn-primary sprintButton">Nova Sprint</button></div></div></div></div>'+							
											'<div class="panel-body"><table class="table table-bordered"><tbody>');
			$.each(value.sprints, function(index, value){
				$("#painelRelease").append('<tr><td>'+value.descricao+'</td><td id="itensSprint" style="height:40px;"></td></tr>');
			});									
														
			$("#painelRelease").append('</tbody></table></div></div>');
			
		})*/
	//});
	
	$( ".sortable1, .sortable2" ).sortable({
	      connectWith: ".connectedSortable",
	      update : function () { 
              var order = $(this).sortable('serialize');
              console.log(order);
              $.ajax({
            	  url: '../service/itembacklog/update',
            	  type: 'POST',
            	  data: order,
            	  success: function(data) {
            	    alert('Load was performed.');
            	  }
            	});
          } 
	    }).disableSelection();
	
	/*$('#external-events div.external-event').draggable({
		containment : '.content',
		stack : '#external-events div',
		cursor : 'move',
		helper : "clone",
		drag : function() {
			$(this).draggable('option', 'revert', true);
		}
	});

	$('.itensSprint').droppable({
		accept : '#external-events div.external-event',
		hoverClass : 'hovered',
		drop : handleDrop
	});*/

	/*function handleDrop(event, ui) {
		$(this).height($(this).height() + 33);
		$(this).append($(ui.draggable));
		ui.draggable.draggable('option', 'revert', false);
	}*/
	
	$("#bAdRelease").click(function(){
		$.fn.abrirModal('#mCadRelease', 'Novo Release');
	});
	
	$(document.body).on('click', '.sprintButton', function(){
		$.fn.abrirModal('#mCadSprint', 'Novo Sprint');
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
			
			$("input[field]").each(function(){
				release[$(this).attr("field")] = $(this).val();
			});
			
			$(".overlay").show();
			
			$.ajax({
				type : method,
				url : actionurl,
				datatype : "json",
				contentType: 'application/json; charset=utf-8',
				data : JSON.stringify(release)
			}).done(function(data) {
				alert("Release Salvo com Sucesso!");
				$(".overlay").hide();				
				$("#hIdRelease").val(data.id);
			}).fail($.fn.tratarErro);
		}
	});
});