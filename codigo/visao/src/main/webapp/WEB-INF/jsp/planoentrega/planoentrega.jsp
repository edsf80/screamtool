<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>
		Plano de Entrega <small>Control panel</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
		<li class="active">Plano de Entrega</li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<div class="row">
		<div class="box box-primary">
			<div class="box-header">

				<div class="input-group">
					<button id="bAdRelease" class="btn btn-sm btn-primary">Novo
						Release</button>
					<div class="panel panel-default"></div>

				</div>
				<!-- /.box-header -->
				<div id="painelRelease" class="box-body no-padding">
					<div class="panel panel-default collapsed-panel">
						<div class="panel-heading">
							<div class="row">
								<div class="col-lg-5">Release Fuck</div>
								<div class="col-sm-3 pull-left">
									<div class="input-group">
										<button class="btn btn-sm btn-primary sprintButton">Nova
											Sprint</button>
									</div>
								</div>
							</div>
						</div>
						<div class="panel-body itensSprint">
							<!-- <table class="table table-bordered">
								<tbody>			
									<tr><td>Teste</td><td id="itensSprint" style="height:40px;"></td></tr>														
								</tbody>
							</table> -->
						</div>
					</div>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /. box -->
		</div>
		<!-- /.col -->
		<!-- /.row -->
</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->