<!-- jQuery 2.1.4 -->
<script src="../plugins/jQuery/jQuery-2.1.4.js"></script>
<!-- jQuery Validate 2.1.3 -->
<script src="../plugins/jQueryValidate/jquery.validate.min.js"></script>
<!-- InputMask -->
<script src="../plugins/input-mask/jquery.inputmask.js"
	type="text/javascript"></script>
<script src="../plugins/input-mask/jquery.inputmask.date.extensions.js"
	type="text/javascript"></script>
<script src="../plugins/input-mask/jquery.inputmask.extensions.js"
	type="text/javascript"></script>
<!-- Bootstrap 3.3.2 JS -->
<script src="../bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<!-- DATA TABES SCRIPT -->
<!-- <script src="../plugins/datatables/jquery.dataTables.js" type="text/javascript"></script> -->
<script src="//cdn.datatables.net/1.10.7/js/jquery.dataTables.js"></script>
<!-- <script src="../plugins/datatables/dataTables.bootstrap.js" type="text/javascript"></script> -->
<script
	src="//cdn.datatables.net/plug-ins/1.10.7/integration/bootstrap/3/dataTables.bootstrap.js"></script>
<!-- SlimScroll -->
<script src="../plugins/slimScroll/jquery.slimscroll.min.js"
	type="text/javascript"></script>
<!-- FastClick -->
<script src='../plugins/fastclick/fastclick.min.js'></script>
<!-- Datepicker -->
<script src='../plugins/datepicker/bootstrap-datepicker.js'></script>
<sec:authorize access="hasRole('perm_salvar_projeto')">
	<script type="text/javascript">
		$(function() {
			$("#bAdProjeto").removeClass("disabled");
		});
	</script>
</sec:authorize>
<script src="../dist/js/pages/projeto.js" type="text/javascript"></script>