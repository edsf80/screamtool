<script src="../plugins/jQuery/jQuery-2.1.4.js"></script>
<script src="../plugins/jQueryValidate/jquery.validate-1.14.0.js"></script>
<script src="../bootstrap/js/bootstrap.js"></script>
<script src="../plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script src="../plugins/fastclick/fastclick.min.js"></script>
<script src="../dist/js/app.min.js"></script>
<script src="../plugins/jQueryUI/jquery-ui-1.10.3.js"></script>
<script type="text/javascript">
	$(function() {
		var teste = 0;
		$('#external-events div.external-event').draggable({
			containment : '.content',
			stack : '#external-events div',
			cursor : 'move',
			helper: "clone",
			drag : function() {
				$(this).draggable( 'option', 'revert', true );
			}
		});
		
		$('#itensSprint').droppable({
			accept : '#external-events div.external-event',
			hoverClass : 'hovered',
			drop : handleDrop
		});
		
		function handleDrop(event, ui) {
			$(this).height($(this).height()+33);
			$(this).append($(ui.draggable));
		    ui.draggable.draggable( 'option', 'revert', false );
		}
	});
</script>