<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>
		Releases <small>Control panel</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
		<li class="active">Calendar</li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<div class="row">
		<div class="col-md-3">
			<div class="box box-solid">
				<div class="box-header with-border">
					<h4 class="box-title">Itens de Backlog</h4>
				</div>
				<div class="box-body">
					<!-- the events -->
					<!-- <div id="external-events">
						<div class="external-event bg-yellow">Item de Backlog 1 - 3pts</div>
						<div class="external-event bg-yellow">Item de Backlog 1 - 3pts</div>
						<div class="external-event bg-yellow">Item de Backlog 1 - 3pts</div>
						<div class="external-event bg-yellow">Item de Backlog 1 - 3pts</div>
						<div class="external-event bg-yellow">Item de Backlog 1 - 3pts</div>
						<div class="external-event bg-yellow">Item de Backlog 1 - 3pts</div>
						<div class="external-event bg-yellow">Item de Backlog 1 - 3pts</div>
						<div class="external-event bg-yellow">Item de Backlog 1 - 3pts</div>
						<div class="external-event bg-yellow">Item de Backlog 1 - 3pts</div>
						<div class="external-event bg-yellow">Item de Backlog 1 - 3pts</div>
						<div class="external-event bg-yellow">Item de Backlog 1 - 3pts</div>
						<div class="external-event bg-yellow">Item de Backlog 1 - 3pts</div>
						<div class="external-event bg-yellow">Item de Backlog 1 - 3pts</div>
						<div class="checkbox">
							<label for="drop-remove"> <input type="checkbox"
								id="drop-remove" /> remove after drop
							</label>
						</div>
					</div> -->
					<ul class="connectedSortable sortable1" style="list-style-type: none; margin: 0; padding: 0;">
						
					</ul>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /. box -->
		</div>
		<!-- /.col -->
		<div class="col-md-9">
			<div class="box box-primary">
				<div class="box-header">
					
						<div class="input-group">
							<button id="bAdRelease"
								class="btn btn-sm btn-primary">Novo Release</button>
							<div class="panel panel-default">
						</div>
					
				</div>
				<!-- /.box-header -->
				<div id="painelRelease" class="box-body no-padding">					
					<div class="panel panel-default collapsed-panel">
						<div class="panel-heading"> 
							<div class="row"> 
								<div class="col-lg-5">Release Fuck</div>
								<div class="col-sm-3 pull-left"> 
									<div class="input-group">
										<button	class="btn btn-sm btn-primary sprintButton">Nova Sprint</button>
									</div>
								</div>
							</div>
						</div>							
						<div class="panel-body itensSprint" style="width: 100%; heigth: 300px;">
							<!-- <table class="table table-bordered">
								<tbody>			
									<tr><td>Teste</td><td id="itensSprint" style="height:40px;"></td></tr>														
								</tbody>
							</table> -->
							<ul class="connectedSortable sortable2" style="list-style-type: none; width: 100%">
								
							</ul>
						</div>
					</div>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /. box -->
		</div>
		<!-- /.col -->
	</div>
	<!-- /.row -->
</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->