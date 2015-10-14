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
					<div id="external-events">
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
					</div>
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
				<div class="box-body no-padding">
					<c:if test="${not empty releases}">
						<c:forEach var="release" items="${releases}">
							<div class="panel panel-default collapsed-panel">
								<!-- Default panel contents -->
								<div class="panel-heading">							
									<div class="row">
										<div class="col-lg-5">
											${release.nome}
										</div>
										<div class="col-sm-3 pull-left">
											<div class="input-group">
												<button id="bAdSprint"
													class="btn btn-sm btn-primary disabled">Nova Sprint</button>						
											</div>
										</div>
									</div>							
								</div>
							
								<div class="panel-body">
									<table class="table table-bordered">
										<c:if test="${not empty release.sprints}">
											<tbody>
												<c:forEach var="sprint" items="${release.sprints}">
													<tr>
														<td>Sprint 1</td>
														<td id="itensSprint" style="height:40px;"></td>
													</tr>
													<tr>
														<td>Sprint 1</td>
														<td id="itensSprint" style="height:40px;"></td>
													</tr>
													<tr>
														<td>Sprint 1</td>
														<td id="itensSprint" style="height:40px;"></td>
													</tr>
												</c:forEach>
											</tbody>
										</c:if>
									</table>					  		
							  	</div>					  
							</div>	
						</c:forEach>					
					</c:if>
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